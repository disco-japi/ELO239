#ifndef INFOWINDOW_H
#define INFOWINDOW_H

#include <QWidget>
#include <QObject>
#include "equipo.h"

class InfoWindow : public QWidget
{
    Q_OBJECT
public:
    explicit InfoWindow(Equipo * equipo,QWidget *parent = nullptr);
    void updatePosition();
    void startTimer();
    void stopTimer();
signals:
};

#endif // INFOWINDOW_H
