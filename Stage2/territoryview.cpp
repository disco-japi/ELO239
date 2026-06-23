#include "territoryview.h"
#include <QPixmap>

TerritoryView::TerritoryView(Territory* model, QWidget* parent)
    : QGraphicsView(parent), territoryModel(model) {

    myScene = new QGraphicsScene(this);
    setScene(myScene);

    if (territoryModel && !territoryModel->getImagePath().empty()) {
        QPixmap background(QString::fromStdString(territoryModel->getImagePath()));
        myScene->addPixmap(background);
        myScene->setSceneRect(background.rect());
    }
}

QGraphicsScene* TerritoryView::getScene() const {
    return myScene;
}
