#include "infowindow.h"
#include "widget.h"
#include <QVBoxLayout>
#include <QLabel>
#include <QSizePolicy>

InfoWindow::InfoWindow(Equipo *eq, ETNube * nube, QWidget *parent)
    : QDialog{parent}, equipo(eq)
{
    setWindowTitle("FindMy - Information");
    setWindowModality(Qt::NonModal);
    setFixedSize(220, 220);
    setAttribute(Qt::WA_DeleteOnClose);

    QVBoxLayout *layout = new QVBoxLayout(this);
    layout->setContentsMargins(20, 20, 20, 20);
    layout->setAlignment(Qt::AlignCenter);
    QLabel *etiqueta = new QLabel(QString::fromStdString(nube->getFindMy(eq->getNombreDueno())), this);
    etiqueta->setAlignment(Qt::AlignCenter);
    etiqueta->setWordWrap(true);
    layout->addWidget(etiqueta);
}
