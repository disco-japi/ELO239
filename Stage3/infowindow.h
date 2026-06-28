#ifndef INFOWINDOW_H
#define INFOWINDOW_H

#include <QDialog>
#include <QObject>
#include "equipo.h"
#include "etnube.h"

class InfoWindow : public QDialog
{
    Q_OBJECT
private:
    Equipo * equipo;
public:
    explicit InfoWindow(Equipo * equipo, ETNube * nube, QWidget *parent = nullptr);
signals:
};

#endif // INFOWINDOW_H
