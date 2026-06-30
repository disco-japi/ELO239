#ifndef TABLETVIEW_H
#define TABLETVIEW_H

#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QObject>
#include "tablet.h"
#include "view.h"

class TabletView : public View
{
    Q_OBJECT
private:
    Tablet *tabletModel;
    QGraphicsTextItem *label;
    QRectF boundingRect() const override;
    void paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget) override;

public:
    TabletView(Tablet *model, Territory *territory, QWidget *mainWindow, ETNube *nube, QGraphicsItem *parent = nullptr);
};

#endif // TABLETVIEW_H
