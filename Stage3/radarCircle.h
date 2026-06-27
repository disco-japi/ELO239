#ifndef RADARCIRCLE_H
#define RADARCIRCLE_H
#include <QGraphicsEllipseItem>
#include <QObject>
#include <QColor>

class radarCircle : public QObject, public QGraphicsEllipseItem{
    Q_OBJECT
    Q_PROPERTY(QRectF rect READ rect WRITE setRect)
private:
    QColor color;
public:
    radarCircle(QColor color, QGraphicsItem *parent = nullptr);
};
#endif // RADARCIRCLE_H
