#ifndef CELLULARVIEW_H
#define CELLULARVIEW_H

#include <QGraphicsRectItem>
#include <QBrush>
#include "cellular.h"

class CellularView : public QGraphicsRectItem {
private:
    Cellular* cellularModel;

public:
    CellularView(Cellular* model, QGraphicsItem* parent = nullptr);
    void updatePosition();
};

#endif // CELLULARVIEW_H