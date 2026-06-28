#include "infowindow.h"
#include <QVBoxLayout>
#include <QLabel>
#include <QSizePolicy>

InfoWindow::InfoWindow(Equipo *equipo, QWidget *parent)
    : QWidget{parent}
{
    setWindowTitle("InfoWindow");
    setFixedSize(220, 220);
    setSizePolicy(QSizePolicy::Fixed, QSizePolicy::Fixed);

    QVBoxLayout *layout = new QVBoxLayout(this);
    layout->setContentsMargins(20, 20, 20, 20);
    layout->setAlignment(Qt::AlignCenter);

    QLabel *etiqueta = new QLabel("Información", this);
    etiqueta->setAlignment(Qt::AlignCenter);
    layout->addWidget(etiqueta);
}
