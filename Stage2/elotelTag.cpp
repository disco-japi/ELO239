#include "elotelTag.h"

EloTelTag::EloTelTag(std::string nombre, std::string nombreTag, double x, double y, int vel, int ang, int dAng)
    : Equipo(nombre, x, y, vel, ang, dAng), nombreTag(nombreTag) {}

std::string EloTelTag::getNombreTag() const { return nombreTag; }
