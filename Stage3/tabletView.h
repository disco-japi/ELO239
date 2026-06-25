#ifndef TABLETVIEW_H
#define TABLETVIEW_H

#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QObject>
#include "tablet.h"

class TabletView : public QObject, public QGraphicsRectItem  {
    Q_OBJECT
public slots:
    //void onClick();
    //void onTimeOut();
private:
    Tablet* tabletModel;
    QGraphicsTextItem* label;

public:
    TabletView(Tablet* model, QGraphicsItem* parent = nullptr);
    void updatePosition();
};

#endif // TABLETVIEW_H
