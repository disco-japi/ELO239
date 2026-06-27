#include "cellular.h"
#include <iostream>

Cellular::Cellular(std::string nombre, double x, double y, int vel, int ang, int dAng, ETNube *nube)
    : Equipo(nombre, "Celular", x, y, vel, ang, dAng) {
    this->nube = nube;
}
void Cellular::reportarUbicacionEquipo(Equipo * equipo){
    nube->updateLocation(equipo->getNombreDueno(),equipo->getNombreEquipo(),this->posX, this->posY);
}