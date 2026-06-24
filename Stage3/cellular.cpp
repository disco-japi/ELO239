#include "cellular.h"
#include <iostream>

Cellular::Cellular(std::string nombre, double x, double y, int vel, int ang, int dAng)
    : Equipo(nombre, x, y, vel, ang, dAng) {}

void Cellular::imprimirPosicion() const {
    std::cout << "Celular de " << nombreDueno << " en posicion: (" << posX << ", " << posY << ")" << std::endl;
}
