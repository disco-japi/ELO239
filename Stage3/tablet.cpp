#include "tablet.h"

Tablet::Tablet(std::string nombre, double x, double y, int vel, int ang, int dAng, ETNube *nube)
    : Equipo(nombre, x, y, vel, ang, dAng) {
    this->nube = nube;
}
