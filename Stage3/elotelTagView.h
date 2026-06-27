#ifndef ELOTELTAGVIEW_H
#define ELOTELTAGVIEW_H

#include <QGraphicsEllipseItem>
#include <QGraphicsSceneMouseEvent>
#include <QBrush>
#include <QTimer>
#include <QObject>
#include "elotelTag.h"
#include "territory.h"
#include "view.h"

class EloTelTagView : public  View{
    Q_OBJECT
private:
    QRectF boundingRect() const override;
    void paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget) override;
public:
    EloTelTagView(EloTelTag* model, Territory *territory, QGraphicsItem* parent = nullptr, QObject *parentO = nullptr);
};

#endif // ELOTELTAGVIEW_H
