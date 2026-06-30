#include "equipo.h"

Equipo::Equipo(std::string nombre, int x, int y, int vel, int ang, int dAng)
    : nombreDueno(nombre), posX(x), posY(y), rapidez(vel), angulo(ang), deltaAngulo(dAng) {}

std::string Equipo::getNombreDueno() const { return nombreDueno; }
int Equipo::getX() const { return posX; }
int Equipo::getY() const { return posY; }
int Equipo::getRapidez() const { return rapidez; }
int Equipo::getAngulo() const { return angulo; }
int Equipo::getDeltaAngulo() const { return deltaAngulo; }