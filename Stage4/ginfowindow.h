#ifndef GINFOWINDOW_H
#define GINFOWINDOW_H

#include <QDialog>
#include <QGraphicsScene>
#include <QGraphicsView>
#include <QGraphicsEllipseItem>
#include <QGraphicsTextItem>
#include <QTimer>
#include <QMap>
#include <QWheelEvent>
#include "etnube.h"
#include "territory.h"

class GInfoWindow : public QDialog
{
    Q_OBJECT

public:
    explicit GInfoWindow(const QString &owner, Territory *territory, ETNube *nube, QWidget *parent = nullptr);
    ~GInfoWindow();

protected:
    void wheelEvent(QWheelEvent *event) override;
    void resizeEvent(QResizeEvent *event) override;

private slots:
    void updateInfo();
    void fitToView();

private:
    void setupUI();
    void loadGraphics();

    QString dueno;
    Territory *territory;
    ETNube *nube;
    QGraphicsView *graphicsView;
    QGraphicsScene *scene;
    QTimer *updateTimer;
    QMap<QString, QGraphicsEllipseItem*> deviceItems;
    QMap<QString, QGraphicsTextItem*> labelItems;
    QPixmap backgroundImage;
    bool isFitted = false;
};

#endif