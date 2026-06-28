#include "elotelTagView.h"
#include <QString>
#include <iostream>
#include "cellular.h"
#include <QDebug>
#include <QPropertyAnimation>
#include <QPainter>

EloTelTagView::EloTelTagView(EloTelTag* model, Territory *territory, QWidget *mainWindow,QGraphicsItem* parent, QObject *parentO)
    : View(model, territory, mainWindow) {
    timerMS = 4000;
    color = Qt::red;
    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::darkRed);
    label->setPlainText(QString::fromStdString(model->getNombreDueno() + " " + model->getNombreEquipo()));
    label->setPos(8, -8);
    label->setScale(0.7);

    updatePosition();
}


QRectF EloTelTagView::boundingRect() const{
    return QRectF(-6, -6, 12, 12);;
}

void EloTelTagView::paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget) {
    Q_UNUSED(option); Q_UNUSED(widget);
    painter->setBrush(QBrush(color));
    painter->drawEllipse(boundingRect());
}
void EloTelTagView::onClick(){}
