#ifndef TABLET_H
#define TABLET_H

#include "equipo.h"

class Tablet : public Equipo {
public:
    Tablet(std::string nombre, double x, double y, int vel, int ang, int dAng);
    ~Tablet() override = default;
};

#endif // TABLET_H
