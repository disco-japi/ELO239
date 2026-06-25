#ifndef ELOTELTAGVIEW_H
#define ELOTELTAGVIEW_H

#include <QGraphicsEllipseItem>
#include <QGraphicsTextItem>
#include <QBrush>
#include <QTimer>
#include <QObject>
#include "elotelTag.h"
#include "territory.h"

class EloTelTagView : public QObject , public QGraphicsEllipseItem{
    Q_OBJECT
private slots:
    void onTimeOut();
private:
    EloTelTag* tagModel;
    QGraphicsTextItem* label;
    QTimer * radarTemp;
    Territory * territory;

public:
    EloTelTagView(EloTelTag* model, Territory *territory, QGraphicsItem* parent = nullptr);
    void updatePosition();
    void startTimer();
    void stopTimer();
};

#endif // ELOTELTAGVIEW_H
