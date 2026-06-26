#ifndef CELLULARVIEW_H
#define CELLULARVIEW_H

#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QObject>
#include "cellular.h"
#include "view.h"

class CellularView : public View , public QGraphicsRectItem {
    Q_OBJECT
private slots:
    //void onClick();
private:
    QGraphicsTextItem* label;

public:
    CellularView(Cellular* model, QGraphicsItem* parent = nullptr);
};

#endif // CELLULARVIEW_H
