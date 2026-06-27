#include "view.h"
#include "radarCircle.h"
#include "QPropertyAnimation"
#include <iostream>
#include <QGraphicsSceneEvent>

View::View(Equipo * newModel, Territory * territory, QGraphicsItem *parent)
    : QObject(), QGraphicsItem(parent)
{
    model = newModel;
    temp = new QTimer(this);
    connect(temp, &QTimer::timeout, this,&View::onTimeOut);
}
void View::updatePosition() {
    if (model) {
        setPos(model->getX(), model->getY());
    }
}

void View::summonRadar(){
    radarCircle * radar = new radarCircle(color, this);
    qreal startSize = 5.0;
    qreal endSize = 50.0;
    QPropertyAnimation *anim = new QPropertyAnimation(radar, "rect", this);
    anim->setStartValue(QRectF(- startSize/2,- startSize/2, startSize, startSize));
    anim->setEndValue(QRectF(- endSize/2,  endSize/2, endSize, endSize));
    anim->setDuration(1000);
    anim->start(QAbstractAnimation::DeleteWhenStopped);
    radar->show();
    QObject::connect(anim, &QPropertyAnimation::finished, [=]() {
        delete radar;
    });
}
void View::startTimer(){
    temp->start(timerMS);
}
void View::stopTimer(){
    temp->stop();
}
void View::mousePressEvent(QGraphicsSceneMouseEvent *event) {
    if (event->button() == Qt::LeftButton) {
        event->accept();
        if (contains(event->pos())){
            std::cout<< "Menu";
        }
    }
    QGraphicsItem::mousePressEvent(event);
}
void View::onClick(){

}
void View::onTimeOut(){
    summonRadar();
    Cellular * cell = territorio->findNearByCellular(model);
    if (cell != NULL){
        cell->reportarUbicacionEquipo(model);
    }
}