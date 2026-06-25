#include "cellular.h"
#include <iostream>

Cellular::Cellular(std::string nombre, double x, double y, int vel, int ang, int dAng, ETNube *nube)
    : Equipo(nombre, x, y, vel, ang, dAng) {
    this->nube = nube;
}
void Cellular::reportarUbicacionTag(EloTelTag * tag){
    nube->updateLocation(tag->getNombreDueno(),tag->getNombreTag(),this->posX, this->posY);
}

void Cellular::reportarUbicacionTablet(Tablet * tab){
    nube->updateLocation(tab->getNombreDueno(),"Tablet",this->posX, this->posY);
}