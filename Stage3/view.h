#ifndef VIEW_H
#define VIEW_H

#include "equipo.h"
#include <QObject>
#include <QGraphicsItem>
#include "territory.h"

class View : public QObject, QGraphicsItem
{
    Q_OBJECT
private slots:
    virtual void onTimeOut();
    virtual void onClick();
protected:
    Equipo* model;
    QGraphicsTextItem* label;
    QTimer * temp;
    Territory * territory;
    void summonRadar();
    void mousePressEvent(QGraphicsSceneMouseEvent *event) override;
    int timerMS = 1000;
public:
    explicit View(Equipo * newModel,QObject *parent = nullptr);
    void updatePosition();
    void startTimer();
    void stopTimer();

signals:
};

#endif // VIEW_H
