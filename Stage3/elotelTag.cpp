#include "elotelTag.h"

EloTelTag::EloTelTag(std::string nombre, std::string nombreTag, double x, double y, int vel, int ang, int dAng)
    : Equipo(nombre, nombreTag, x, y, vel, ang, dAng), nombreTag(nombreTag) {}