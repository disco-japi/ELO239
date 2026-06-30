#include "view.h"
#include "radarCircle.h"
#include "QPropertyAnimation"
#include <QCursor>
#include <iostream>
#include <QGraphicsSceneEvent>
#include <QEvent>
#include <QDebug>

View::View(Equipo *newModel, Territory *territory, QWidget *mainWindow, ETNube *nube, QGraphicsItem *parent)
    : QObject(), QGraphicsItem(parent)
{
    infowindow = nullptr;
    this->mainWindow = mainWindow;
    model = newModel;
    this->territorio = territory;
    this->nube = nube;
    temp = new QTimer(this);
    context = new QMenu(mainWindow);
    openInfo = new QAction("FindMy", this);
    context->addAction(openInfo);
    connect(openInfo, &QAction::triggered, this, &View::onMenuOpen);
    connect(temp, &QTimer::timeout, this, &View::onTimeOut);
    connect(this, &View::clicked, this, &View::onClick);
    setAcceptedMouseButtons(Qt::LeftButton);
    setFiltersChildEvents(true);
}
void View::onMenuOpen()
{
    std::cout << "Help\n";
    if (infowindow == NULL)
    {
        std::cout << "Open";
        infowindow = new InfoWindow(model, nube, mainWindow);
    }
    infowindow->show();
    infowindow->raise();
    infowindow->activateWindow();
}

void View::updatePosition()
{
    if (model)
    {
        setPos(model->getX(), model->getY());
    }
}

void View::summonRadar()
{
    radarCircle *radar = new radarCircle(color, this);
    qreal startSize = 5.0;
    qreal endSize = 50.0;
    QPropertyAnimation *anim = new QPropertyAnimation(radar, "rect", this);
    anim->setStartValue(QRectF(-startSize / 2, -startSize / 2, startSize, startSize));
    anim->setEndValue(QRectF(-endSize / 2, -endSize / 2, endSize, endSize));
    anim->setDuration(1000);
    anim->start(QAbstractAnimation::DeleteWhenStopped);
    radar->show();
    QObject::connect(anim, &QPropertyAnimation::finished, [=]()
                     { delete radar; });
}
void View::startTimer()
{
    temp->start(timerMS);
}
void View::stopTimer()
{
    temp->stop();
}
void View::mousePressEvent(QGraphicsSceneMouseEvent *event)
{
    if (event->button() == Qt::LeftButton)
    {
        event->accept();
        emit clicked();
    }
    QGraphicsItem::mousePressEvent(event);
}

bool View::sceneEventFilter(QGraphicsItem *watched, QEvent *event)
{
    if (event->type() == QEvent::GraphicsSceneMousePress)
    {
        auto mouseEvent = static_cast<QGraphicsSceneMouseEvent *>(event);
        if (mouseEvent->button() == Qt::LeftButton)
        {
            emit clicked();
            event->accept();
            return true;
        }
    }
    return QGraphicsItem::sceneEventFilter(watched, event);
}

void View::onClick()
{
    const QPoint globalPos = QCursor::pos();
    context->popup(globalPos);
}
void View::onTimeOut()
{
    summonRadar();
    Cellular *cell = territorio->findNearByCellular(model);
    if (cell != NULL)
    {
        cell->reportarUbicacionEquipo(model);
    }
}