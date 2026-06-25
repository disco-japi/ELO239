#include "elotelTagView.h"
#include <QString>
#include <iostream>
#include "cellular.h"

EloTelTagView::EloTelTagView(EloTelTag* model, Territory *territory, QGraphicsItem* parent)
    : QGraphicsEllipseItem(parent), tagModel(model) {

    setRect(-6, -6, 12, 12);
    setBrush(QBrush(Qt::red));

    radarTemp = new QTimer(this);
    connect(radarTemp, &QTimer::timeout, this,&EloTelTagView::onTimeOut);
    radarTemp->start(4000);
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
}