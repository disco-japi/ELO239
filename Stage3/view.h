#ifndef VIEW_H
#define VIEW_H

#include "equipo.h"
#include <QObject>
#include <QGraphicsItem>
#include "territory.h"

class View : public QObject, public QGraphicsItem
{
    Q_OBJECT
    Q_INTERFACES(QGraphicsItem)
protected slots:
    virtual void onTimeOut();
    virtual void onClick();
protected:
    Equipo* model;
    QGraphicsTextItem* label;
    QTimer * temp;
    QColor color;
    Territory * territorio;
    void summonRadar();
    void mousePressEvent(QGraphicsSceneMouseEvent *event) override;
    int timerMS = 1000;
public:
    explicit View(Equipo * newModel, Territory * territory, QGraphicsItem *parent = nullptr );
    void updatePosition();
    void startTimer();
    void stopTimer();

signals:
};

#endif // VIEW_H
