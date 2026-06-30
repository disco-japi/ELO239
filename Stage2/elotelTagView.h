#ifndef ELOTELTAGVIEW_H
#define ELOTELTAGVIEW_H

#include <QGraphicsEllipseItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include "elotelTag.h"

class EloTelTagView : public QGraphicsEllipseItem {
private:
    EloTelTag* tagModel;
    QGraphicsTextItem* label;

public:
    EloTelTagView(EloTelTag* model, QGraphicsItem* parent = nullptr);
    void updatePosition();
};

#endif // ELOTELTAGVIEW_H
