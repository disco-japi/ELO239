#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QString>
#include <vector>
#include "territory.h"
#include "territoryview.h"
#include "cellular.h"
#include "cellularview.h"

QT_BEGIN_NAMESPACE
namespace Ui { class Widget; }
QT_END_NAMESPACE

class Widget : public QWidget
{
    Q_OBJECT

public:
    Widget(QString configFilePath, QWidget *parent = nullptr);
    ~Widget();

private:
    Ui::Widget *ui;

    Territory* territoryModel;
    std::vector<Cellular*> celularesModels;

    TerritoryView* territoryView;
    std::vector<CellularView*> celularesViews;

    void cargarConfiguracion(const QString& filePath);
};

#endif // WIDGET_H