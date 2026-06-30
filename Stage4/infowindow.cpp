#include "infowindow.h"
#include "widget.h"
#include <QVBoxLayout>
#include <QLabel>
#include <QSizePolicy>
#include <QHeaderView>

InfoWindow::InfoWindow(Equipo *eq, ETNube *nube, QWidget *parent)
    : QDialog(parent), equipo(eq), nube(nube)
{
    setupUI();
    loadReports();
    updateTimer = new QTimer(this);
    connect(updateTimer, &QTimer::timeout, this, &InfoWindow::updateInfo);
    updateTimer->start(1000);

    setAttribute(Qt::WA_DeleteOnClose);
}

InfoWindow::~InfoWindow()
{
    if (updateTimer) {
        updateTimer->stop();
    }
}

void InfoWindow::setupUI()
{
    setWindowTitle("FindMy - " + QString::fromStdString(equipo->getNombreDueno()));
    setWindowModality(Qt::NonModal);
    setMinimumSize(400, 300);
    setAttribute(Qt::WA_DeleteOnClose);

    QVBoxLayout *layout = new QVBoxLayout(this);
    layout->setContentsMargins(20, 20, 20, 20);

    QLabel *title = new QLabel("Dispositivos de " + QString::fromStdString(equipo->getNombreDueno()), this);
    title->setAlignment(Qt::AlignCenter);
    title->setStyleSheet("font-size: 14px; font-weight: bold;");
    layout->addWidget(title);

    table = new QTableWidget(this);
    table->setColumnCount(3);
    table->setHorizontalHeaderLabels({"Dispositivo", "Posición", "Traza"});
    table->horizontalHeader()->setStretchLastSection(true);
    table->horizontalHeader()->setSectionResizeMode(0, QHeaderView::Stretch);
    table->horizontalHeader()->setSectionResizeMode(1, QHeaderView::ResizeToContents);
    table->horizontalHeader()->setSectionResizeMode(2, QHeaderView::ResizeToContents);
    layout->addWidget(table);
}

void InfoWindow::loadReports()
{
    std::vector<ETNube::EquipoInfo> equipos = nube->getEquiposByOwner(equipo->getNombreDueno());

    table->setRowCount(equipos.size());
    trazaButtons.clear();

    for (size_t i = 0; i < equipos.size(); ++i) {
        const ETNube::EquipoInfo &info = equipos[i];
        table->setItem(i, 0, new QTableWidgetItem(QString::fromStdString(info.equipo_name)));
        QString posStr = QString("(%1, %2)").arg(info.x).arg(info.y);
        table->setItem(i, 1, new QTableWidgetItem(posStr));
        QPushButton *btn = new QPushButton("Mostrar Traza", this);
        btn->setProperty("equipoName", QString::fromStdString(info.equipo_name));
        connect(btn, &QPushButton::clicked, [this, info]() {
            toggleTraza(QString::fromStdString(info.equipo_name));
        });
        table->setCellWidget(i, 2, btn);
        trazaButtons[QString::fromStdString(info.equipo_name)] = btn;
    }
}

void InfoWindow::updateInfo()
{

    loadReports();
}

void InfoWindow::toggleTraza(const QString &nombreEquipo)
{

    if (trazaButtons.contains(nombreEquipo)) {
        QPushButton *btn = trazaButtons[nombreEquipo];
        if (btn->text() == "Mostrar Traza") {
            btn->setText("Ocultar Traza");
            btn->setStyleSheet("background-color: #ffcccc;");
        } else {
            btn->setText("Mostrar Traza");
            btn->setStyleSheet("");
        }
    }

}