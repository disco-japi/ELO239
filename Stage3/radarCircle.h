#ifndef RADARCIRCLE_H
#define RADARCIRCLE_H
#include <QGraphicsEllipseItem>
#include <QObject>

class radarCircle : public QObject, public QGraphicsEllipseItem{
    Q_OBJECT
    Q_PROPERTY(QRectF rect READ rect WRITE setRect)
public:
    radarCircle(QGraphicsItem *parent = nullptr);
};
#endif // RADARCIRCLE_H
