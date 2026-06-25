#ifndef CELLULARVIEW_H
#define CELLULARVIEW_H

#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QObject>
#include "cellular.h"

class CellularView : public QObject , public QGraphicsRectItem {
    Q_OBJECT
private slots:
    //void onClick();
private:
    Cellular* cellularModel;
    QGraphicsTextItem* label;

public:
    CellularView(Cellular* model, QGraphicsItem* parent = nullptr);
    void updatePosition();
};

#endif // CELLULARVIEW_H
