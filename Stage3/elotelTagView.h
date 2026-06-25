#ifndef ELOTELTAGVIEW_H
#define ELOTELTAGVIEW_H

#include <QGraphicsEllipseItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QTimer>
#include <QObject>
#include "elotelTag.h"

class EloTelTagView : public QGraphicsEllipseItem {
    // Q_OBJECT
private:
    EloTelTag* tagModel;
    QGraphicsTextItem* label;
    QTimer * radarTemp;

public:
    EloTelTagView(EloTelTag* model, QGraphicsItem* parent = nullptr);
    void updatePosition();
};

#endif // ELOTELTAGVIEW_H
