#include "territoryview.h"
#include <QPixmap>

TerritoryView::TerritoryView(Territory* model, QWidget* parent)
    : QGraphicsView(parent), territoryModel(model) {

    scene = new QGraphicsScene(this);
    setScene(scene);

    if (territoryModel && !territoryModel->getImagePath().empty()) {
        QPixmap background(QString::fromStdString(territoryModel->getImagePath()));
        scene->addPixmap(background);
        scene->setSceneRect(background.rect());
    }
}

QGraphicsScene* TerritoryView::getScene() const {
    return scene;
}