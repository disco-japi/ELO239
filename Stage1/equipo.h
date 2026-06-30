#ifndef EQUIPO_H
#define EQUIPO_H

#include <string>

class Equipo {
protected:
    std::string nombreDueno;
    int posX;
    int posY;
    int rapidez;
    int angulo;
    int deltaAngulo;

public:
    Equipo(std::string nombre, int x, int y, int vel, int ang, int dAng);
    virtual ~Equipo() = default;

    std::string getNombreDueno() const;
    int getX() const;
    int getY() const;
    int getRapidez() const;
    int getAngulo() const;
    int getDeltaAngulo() const;
};

#endif // EQUIPO_H