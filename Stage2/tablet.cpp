#include "tablet.h"

Tablet::Tablet(std::string nombre, double x, double y, int vel, int ang, int dAng)
    : Equipo(nombre, x, y, vel, ang, dAng) {}
