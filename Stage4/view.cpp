#include "view.h"
#include "radarCircle.h"
#include "ginfowindow.h"  // NUEVO: Include para GFindMy
#include <QPropertyAnimation>
#include <QCursor>
#include <iostream>
#include <QGraphicsSceneEvent>
#include <QEvent>
#include <QDebug>
#include <QPen>

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

    // Acción FindMy (existente)
    openInfo = new QAction("FindMy", this);
    context->addAction(openInfo);
    connect(openInfo, &QAction::triggered, this, &View::onMenuOpen);

    // NUEVO: Acción GFindMy
    openGInfo = new QAction("GFindMy", this);
    context->addAction(openGInfo);
    connect(openGInfo, &QAction::triggered, this, &View::onGMenuOpen);

    connect(temp, &QTimer::timeout, this, &View::onTimeOut);
    connect(this, &View::clicked, this, &View::onClick);
    setAcceptedMouseButtons(Qt::LeftButton);
    setFiltersChildEvents(true);
    trazaItem = nullptr;
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

// NUEVO: Implementación de GFindMy
void View::onGMenuOpen()
{
    GInfoWindow *gInfo = new GInfoWindow(
        QString::fromStdString(model->getNombreDueno()),
        territorio,
        nube,
        mainWindow
        );
    gInfo->show();
}

void View::updatePosition()
{
    if (model)
    {
        setPos(model->getX(), model->getY());
        updateTraza();
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

void View::updateTraza()
{
    if (!model) return;

    if (!model->isTrazaVisible()) {
        if (trazaItem) {
            trazaItem->setVisible(false);
        }
        return;
    }

    QVector<QPointF> points = model->getTrazaPoints();
    if (points.size() < 2) {
        if (trazaItem) {
            trazaItem->setVisible(false);
        }
        return;
    }

    if (!trazaItem) {
        trazaItem = new QGraphicsPathItem(this);
        QPen pen(Qt::blue);
        pen.setWidth(2);
        trazaItem->setPen(pen);
        trazaItem->setZValue(-1);
    }

    QPainterPath path;
    path.moveTo(points.first());
    for (int i = 1; i < points.size(); ++i) {
        path.lineTo(points[i]);
    }

    trazaItem->setPath(path);
    trazaItem->setVisible(true);
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