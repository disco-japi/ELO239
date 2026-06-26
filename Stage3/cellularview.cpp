#include "cellularview.h"
#include <QString>

CellularView::CellularView(Cellular* model, QGraphicsItem* parent)
    : QGraphicsRectItem(parent), View(model){
    setRect(-6, -12, 12, 24);
    setBrush(QBrush(Qt::blue));

    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::black);
    label->setPlainText(QString::fromStdString(model->getNombreDueno()));
    label->setPos(8, -12);
    label->setScale(0.8);

    updatePosition();
}

