#include "cellularview.h"

CellularView::CellularView(Cellular* model, QGraphicsItem* parent)
    : QGraphicsRectItem(parent), cellularModel(model) {

    setRect(0, 0, 12, 24);
    setBrush(QBrush(Qt::blue));

    updatePosition();
}

void CellularView::updatePosition() {
    if (cellularModel) {
        setPos(cellularModel->getX(), cellularModel->getY());
    }
}