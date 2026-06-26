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

class EloTelTagView : public  View, public QGraphicsEllipseItem{
    Q_OBJECT
public:
    EloTelTagView(EloTelTag* model, Territory *territory, QGraphicsItem* parent = nullptr, QObject *parentO = nullptr);
};

#endif // ELOTELTAGVIEW_H
