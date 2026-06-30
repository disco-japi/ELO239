#include "widget.h"
#include "ui_widget.h"
#include <QVBoxLayout>
#include <QFile>
#include <QTextStream>
#include <QMessageBox>
#include <QFileInfo>
#include <QDir>
#include <iostream>

// sirve para armar la ventana principal y acomodar el mapa
Widget::Widget(QString configFilePath, QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

    QVBoxLayout* mainLayout = new QVBoxLayout(this);
    mainLayout->setContentsMargins(0, 0, 0, 0);

    territoryModel = nullptr;
    territoryView = nullptr;

    cargarConfiguracion(configFilePath);

    if (territoryView) {
        mainLayout->addWidget(territoryView);
    }
}

// sirve para limpiar la memoria al cerrar
Widget::~Widget()
{
    delete ui;
    delete territoryModel;
    for (auto c : celularesModels) delete c;
}

// sirve para leer el txt, sacar las coordenadas y dibujar los equipos
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

    QString rutaAbsolutaImagen = directorio.absoluteFilePath(bgImage);

    territoryModel = new Territory(rutaAbsolutaImagen.toStdString());
    territoryView = new TerritoryView(territoryModel, this);

    double deltaTiempo;
    int numPersonas;

    in >> deltaTiempo >> numPersonas;

    for (int i = 0; i < numPersonas; ++i) {
        QString nombrePersona;
        int numTags, hasTablet;

        in >> nombrePersona >> numTags >> hasTablet;

        int cx, cy, cvel, cang, cdang;
        in >> cx >> cy >> cvel >> cang >> cdang;

        Cellular* cel = new Cellular(nombrePersona.toStdString(), cx, cy, cvel, cang, cdang);
        celularesModels.push_back(cel);

        cel->imprimirPosicion();

        CellularView* cView = new CellularView(cel);
        cView->setZValue(10);

        celularesViews.push_back(cView);
        territoryView->getScene()->addItem(cView);

        for (int j = 0; j < numTags; ++j) {
            QString tagName;
            int tx, ty, tvel, tang, tdang;
            in >> tagName >> tx >> ty >> tvel >> tang >> tdang;
        }

        if (hasTablet == 1) {
            int tbx, tby, tbvel, tbang, tbdang;
            in >> tbx >> tby >> tbvel >> tbang >> tbdang;
        }
    }

    file.close();
}