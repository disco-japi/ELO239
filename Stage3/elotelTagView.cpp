#include "elotelTagView.h"
#include <QString>
#include <iostream>
#include "cellular.h"
#include <QDebug>

EloTelTagView::EloTelTagView(EloTelTag* model, Territory *territory, QGraphicsItem* parent)
    : QGraphicsEllipseItem(parent), tagModel(model) {

    setRect(-6, -6, 12, 12);
    setBrush(QBrush(Qt::red));
    this->territory = territory;
    radarTemp = new QTimer(this);
    connect(radarTemp, &QTimer::timeout, this,&EloTelTagView::onTimeOut);
    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::darkRed);
    label->setPlainText(QString::fromStdString(model->getNombreTag()));
    label->setPos(8, -8);
    label->setScale(0.7);

    updatePosition();
}

void EloTelTagView::updatePosition() {
    if (tagModel) {
        setPos(tagModel->getX(), tagModel->getY());
    }
}

void EloTelTagView::onTimeOut(){
    Cellular * cell = territory->findNearByCellular(tagModel);
    if (cell != NULL){
        cell->reportarUbicacionTag(tagModel);
        std::cout << "encontrado\n";
    }
}
void EloTelTagView::startTimer(){
    radarTemp->start(4000);
}
void EloTelTagView::stopTimer(){
    radarTemp->stop();
}