#include "widget.h"
#include <QVBoxLayout>
#include <QFile>
#include <QTextStream>
#include <QMessageBox>
#include <QFileInfo>
#include <QDir>
#include <QMenuBar>
#include <QMenu>
#include <QAction>
#include <string.h>

Widget::Widget(QString configFilePath, QWidget *parent)
    : QWidget(parent)
    , timer(new QTimer(this))
    , deltaTiempo(0.1)
    , territoryModel(nullptr)
    , territoryView(nullptr)
{
    nube = new ETNube;
    setWindowTitle("EloTelTag Simulation in C++ and Qt: Stage 2");
    resize(1000, 700);

    QMenuBar* menuBar = new QMenuBar(this);
    QMenu* simMenu = menuBar->addMenu("Simulation");
    connect(simMenu->addAction("Play"),  &QAction::triggered, this, &Widget::onPlay);
    connect(simMenu->addAction("Pause"), &QAction::triggered, this, &Widget::onPause);

    QVBoxLayout* mainLayout = new QVBoxLayout(this);
    mainLayout->setContentsMargins(0, 0, 0, 0);
    mainLayout->setSpacing(0);
    mainLayout->setMenuBar(menuBar);

    cargarConfiguracion(configFilePath);

    if (territoryView) {
        mainLayout->addWidget(territoryView);
    }

    connect(timer, &QTimer::timeout, this, &Widget::onTimerTick);
}

Widget::~Widget()
{
    delete territoryModel;
    delete nube;
    for (auto c : celularesModels) delete c;
    for (auto t : tagsModels)      delete t;
    for (auto tb: tabletsModels)   delete tb;
}

void Widget::onPlay()
{
    timer->start((int)(deltaTiempo * 1000));
}

void Widget::onPause()
{
    timer->stop();
}

void Widget::onTimerTick()
{
    if (!territoryView) return;

    int w = (int)territoryView->getScene()->sceneRect().width();
    int h = (int)territoryView->getScene()->sceneRect().height();

    for (size_t i = 0; i < celularesModels.size(); i++) {
        celularesModels[i]->mover(deltaTiempo, w, h);
        celularesViews[i]->updatePosition();
    }
    for (size_t i = 0; i < tagsModels.size(); i++) {
        tagsModels[i]->mover(deltaTiempo, w, h);
        tagsViews[i]->updatePosition();
    }
    for (size_t i = 0; i < tabletsModels.size(); i++) {
        tabletsModels[i]->mover(deltaTiempo, w, h);
        tabletsViews[i]->updatePosition();
    }
}

void Widget::cargarConfiguracion(const QString& filePath)
{
    QFile file(filePath);
    if (!file.open(QIODevice::ReadOnly | QIODevice::Text)) {
        QMessageBox::critical(this, "Error", "No se pudo leer el archivo.");
        return;
    }

    QFileInfo fileInfo(filePath);
    QDir directorio = fileInfo.absoluteDir();

    QTextStream in(&file);
    in.setLocale(QLocale::C);

    QString bgImage;
    in >> bgImage;

    territoryModel = new Territory(directorio.absoluteFilePath(bgImage).toStdString());
    territoryView  = new TerritoryView(territoryModel, this);

    int numPersonas;
    in >> deltaTiempo >> numPersonas;

    for (int i = 0; i < numPersonas; ++i) {
        QString nombrePersona;
        int numTags, hasTablet;
        in >> nombrePersona >> numTags >> hasTablet;

        double cx, cy; int cvel, cang, cdang;
        in >> cx >> cy >> cvel >> cang >> cdang;

        Cellular* cel = new Cellular(nombrePersona.toStdString(), cx, cy, cvel, cang, cdang, nube);
        celularesModels.push_back(cel);
        cel->imprimirPosicion();

        CellularView* cView = new CellularView(cel);
        nube->updateLocation(nombrePersona.toStdString(), "Celular", cx, cy);
        cView->setZValue(10);
        celularesViews.push_back(cView);
        territoryView->getScene()->addItem(cView);

        for (int j = 0; j < numTags; ++j) {
            QString tagName;
            double tx, ty; int tvel, tang, tdang;
            in >> tagName >> tx >> ty >> tvel >> tang >> tdang;

            EloTelTag* tag = new EloTelTag(nombrePersona.toStdString(),
                                           tagName.toStdString(), tx, ty, tvel, tang, tdang);
            tagsModels.push_back(tag);

            EloTelTagView* tView = new EloTelTagView(tag);
            tView->setZValue(8);
            tagsViews.push_back(tView);
            territoryView->getScene()->addItem(tView);
            nube->updateLocation(tag->getNombreDueno(),tag->getNombreTag(), tag->getX(), tag->getY());
        }

        if (hasTablet == 1) {
            double tbx, tby; int tbvel, tbang, tbdang;
            in >> tbx >> tby >> tbvel >> tbang >> tbdang;

            Tablet* tab = new Tablet(nombrePersona.toStdString(), tbx, tby, tbvel, tbang, tbdang, nube);
            tabletsModels.push_back(tab);

            TabletView* tabView = new TabletView(tab);
            tabView->setZValue(9);
            tabletsViews.push_back(tabView);
            territoryView->getScene()->addItem(tabView);
            nube->updateLocation(tab->getNombreDueno(),"Tablet", tab->getX(), tab->getY());
        }
    }

    file.close();
}

Cellular * Widget::findNearByCellular(Equipo equipo){
    for (Cellular * cell : celularesModels){
        if (equipo.enRango(*cell)){
            return cell;
        }
    }
    return NULL;
}

