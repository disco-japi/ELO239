#ifndef CELLULARVIEW_H
#define CELLULARVIEW_H

#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QObject>
#include "cellular.h"
#include "view.h"

class CellularView : public View {
    Q_OBJECT
private slots:
    void onTimeOut() override;
private:
    QGraphicsTextItem* label;
    QRectF boundingRect() const override;
    void paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget) override;

public:
    CellularView(Cellular* model, Territory * territory, QWidget *mainWindow, QGraphicsItem* parent = nullptr);
};

#endif // CELLULARVIEW_H
