#include "elotelTagView.h"
#include <QString>
#include <iostream>
#include "cellular.h"
#include <QDebug>
#include <QPropertyAnimation>
#include <QGraphicsEllipseItem>

EloTelTagView::EloTelTagView(EloTelTag* model, Territory *territory, QGraphicsItem* parent, QObject *parentO)
    : QGraphicsEllipseItem(parent), View(model) {
    setRect(-6, -6, 12, 12);
    setBrush(QBrush(Qt::red));
    this->territory = territory;
    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::darkRed);
    label->setPlainText(QString::fromStdString(model->getNombreDueno() + " " + model->getNombreTag()));
    label->setPos(8, -8);
    label->setScale(0.7);

    updatePosition();
}


