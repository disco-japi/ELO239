#include "elotelTagView.h"
#include <QString>

EloTelTagView::EloTelTagView(EloTelTag* model, QGraphicsItem* parent)
    : QGraphicsEllipseItem(parent), tagModel(model) {

    setRect(-6, -6, 12, 12);
    setBrush(QBrush(Qt::red));

    label = new QGraphicsTextItem(this);
    label->setDefaultTextColor(Qt::darkRed);
    label->setPlainText(QString::fromStdString(model->getNombreTag()));
    label->setPos(8, -8);
    label->setScale(0.7);

    updatePosition();
    // radarTemp = new QTimer(this);
}

void EloTelTagView::updatePosition() {
    if (tagModel) {
        setPos(tagModel->getX(), tagModel->getY());
    }
}
