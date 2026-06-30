#ifndef TERRITORYVIEW_H
#define TERRITORYVIEW_H

#include <QGraphicsView>
#include <QGraphicsScene>
#include "territory.h"

class TerritoryView : public QGraphicsView {
private:
    QGraphicsScene* myScene;
    Territory* territoryModel;

public:
    TerritoryView(Territory* model, QWidget* parent = nullptr);
    QGraphicsScene* getScene() const;
};

#endif // TERRITORYVIEW_H
