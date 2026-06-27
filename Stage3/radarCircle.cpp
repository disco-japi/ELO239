#include "radarCircle.h"
#include <QBrush>

radarCircle::radarCircle(QColor color, QGraphicsItem *parent)
    : QObject(), QGraphicsEllipseItem(parent) {
    setBrush(color);
    setScale(1);
    setOpacity(0.4);
}