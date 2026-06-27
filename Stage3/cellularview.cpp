#include "cellularview.h"
#include <QString>
#include <QPainter>

CellularView::CellularView(Cellular* model, Territory * territory, QGraphicsItem* parent)
    : View(model, territory){
    timerMS = 4000;
    color = Qt::blue;
    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::black);
    label->setPlainText(QString::fromStdString(model->getNombreDueno()));
    label->setPos(8, -12);
    label->setScale(0.8);

    updatePosition();
}

QRectF CellularView::boundingRect() const{
    return QRectF(-6, -6, 12, 24);;
}

void CellularView::paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget) {
    Q_UNUSED(option); Q_UNUSED(widget);
    painter->setBrush(QBrush(color));
    painter->drawRoundedRect(boundingRect(),3,3);
}
void CellularView::onTimeOut(){

}