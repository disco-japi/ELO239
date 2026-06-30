#ifndef TERRITORY_H
#define TERRITORY_H

#include <string>
#include <vector>
#include "cellular.h"
#include "elotelTag.h"
#include "tablet.h"

class Territory {
private:
    std::string imagePath;

public:
    std::vector<Cellular*>     celularesModels;
    std::vector<EloTelTag*>    tagsModels;
    std::vector<Tablet*>       tabletsModels;
    Territory(std::string path);
    std::string getImagePath() const;
    Cellular * findNearByCellular(Equipo * equipo);
};

#endif // TERRITORY_H