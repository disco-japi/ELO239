#include "tabletView.h"
#include <QString>
#include <QPainter>

TabletView::TabletView(Tablet *model, Territory *territory, QWidget *mainWindow, ETNube *nube, QGraphicsItem *parent)
    : View(model, territory, mainWindow, nube)
{
    timerMS = 5000;
    color = Qt::darkGreen;
    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::darkGreen);
    label->setPlainText(QString::fromStdString(model->getNombreDueno()) + " tablet");
    label->setPos(10, -8);
    label->setScale(0.7);

    updatePosition();
}
QRectF TabletView::boundingRect() const
{
    return QRectF(-24, -24, 24, 24);
    ;
}

void TabletView::paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget)
{
    Q_UNUSED(option);
    Q_UNUSED(widget);
    painter->setBrush(color);
    painter->drawRoundedRect(boundingRect(), 3, 3);
}
