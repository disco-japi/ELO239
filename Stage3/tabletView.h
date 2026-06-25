#ifndef TABLETVIEW_H
#define TABLETVIEW_H

#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QObject>
#include "tablet.h"

class TabletView : public QGraphicsRectItem {
    // Q_OBJECT
private:
    Tablet* tabletModel;
    QGraphicsTextItem* label;

public:
    TabletView(Tablet* model, QGraphicsItem* parent = nullptr);
    void updatePosition();
};

#endif // TABLETVIEW_H
