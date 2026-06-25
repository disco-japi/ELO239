#include "territory.h"

Territory::Territory(std::string path) : imagePath(path) {}

std::string Territory::getImagePath() const {
    return imagePath;
}
Cellular * Territory::findNearByCellular(Equipo *equipo){
    for (Cellular * cell : celularesModels){
        if (equipo->enRango(cell)){
            return cell;
        }
    }
    return NULL;
}