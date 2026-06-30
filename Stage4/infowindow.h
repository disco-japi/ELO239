#ifndef INFOWINDOW_H
#define INFOWINDOW_H

#include <QDialog>
#include <QObject>
#include <QTimer>
#include <QTableWidget>
#include <QPushButton>
#include <QMap>
#include "equipo.h"
#include "etnube.h"

class InfoWindow : public QDialog
{
    Q_OBJECT
private:
    Equipo * equipo;
    ETNube * nube;
    QTimer *updateTimer;
    QTableWidget *table;
    QMap<QString, QPushButton*> trazaButtons;

    void loadReports();
    void setupUI();

public:
    explicit InfoWindow(Equipo * equipo, ETNube * nube, QWidget *parent = nullptr);
    ~InfoWindow();

private slots:
    void updateInfo();
    void toggleTraza(const QString &nombreEquipo);
};

#endif