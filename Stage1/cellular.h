#ifndef CELLULAR_H
#define CELLULAR_H

#include "equipo.h"

class Cellular : public Equipo {
public:
    Cellular(std::string nombre, int x, int y, int vel, int ang, int dAng);
    ~Cellular() override = default;

    void imprimirPosicion() const;
};

#endif // CELLULAR_H