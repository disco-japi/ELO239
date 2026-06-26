#include "tabletView.h"
#include <QString>

TabletView::TabletView(Tablet* model, QGraphicsItem* parent)
    : QGraphicsRectItem(parent), View(model) {

    setRect(-8, -5, 16, 10);
    setBrush(QBrush(Qt::green));

    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::darkGreen);
    label->setPlainText(QString::fromStdString(model->getNombreDueno()) + " tablet");
    label->setPos(10, -8);
    label->setScale(0.7);

    updatePosition();
}