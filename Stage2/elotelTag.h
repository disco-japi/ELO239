#ifndef ELOTELTAG_H
#define ELOTELTAG_H

#include "equipo.h"

class EloTelTag : public Equipo {
private:
    std::string nombreTag;

public:
    EloTelTag(std::string nombre, std::string nombreTag, double x, double y, int vel, int ang, int dAng);
    ~EloTelTag() override = default;

    std::string getNombreTag() const;
};

#endif // ELOTELTAG_H
