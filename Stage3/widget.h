#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QString>
#include <QTimer>
#include <vector>
#include "territory.h"
#include "territoryview.h"
#include "cellular.h"
#include "cellularview.h"
#include "elotelTag.h"
#include "elotelTagView.h"
#include "tablet.h"
#include "tabletView.h"
#include "etnube.h"

class Widget : public QWidget
{
    Q_OBJECT

public:
    Widget(QString configFilePath, QWidget *parent = nullptr);
    ~Widget();

private slots:
    void onPlay();
    void onPause();
    void onTimerTick();

private:
    Territory*                 territoryModel;

    TerritoryView*             territoryView;
    std::vector<CellularView*> celularesViews;
    std::vector<EloTelTagView*>tagsViews;
    std::vector<TabletView*>   tabletsViews;
    ETNube * nube;

    QTimer* timer;
    double  deltaTiempo;

    void cargarConfiguracion(const QString& filePath);
};

#endif // WIDGET_H

