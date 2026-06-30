#ifndef VIEW_H
#define VIEW_H

#include "equipo.h"
#include <QAction>
#include <QObject>
#include <QGraphicsItem>
#include <QGraphicsTextItem>
#include <QTimer>
#include <QMenu>
#include <QWidget>
#include "territory.h"
#include "infowindow.h"
#include "etnube.h"

class View : public QObject, public QGraphicsItem
{
    Q_OBJECT
    Q_INTERFACES(QGraphicsItem)
protected slots:
    virtual void onTimeOut();
    virtual void onClick();
    virtual void onMenuOpen();

private:
    QTimer *temp;
    QAction *openInfo;
    QMenu *context;
    InfoWindow *infowindow;

protected:
    Equipo *model;
    QGraphicsTextItem *label;
    QColor color;
    Territory *territorio;
    ETNube *nube;
    QWidget *mainWindow;
    void summonRadar();
    void mousePressEvent(QGraphicsSceneMouseEvent *event) override;
    bool sceneEventFilter(QGraphicsItem *watched, QEvent *event) override;
    int timerMS = 1000;

public:
    explicit View(Equipo *newModel, Territory *territory, QWidget *windowParent, ETNube *nube, QGraphicsItem *parent = nullptr);
    void updatePosition();
    void startTimer();
    void stopTimer();

signals:
    void clicked();
};

#endif // VIEW_H
