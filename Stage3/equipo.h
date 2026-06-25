#ifndef EQUIPO_H
#define EQUIPO_H

#include <string>
#include <QObject>

class Equipo : public QObject  {
    Q_OBJECT
protected:
    std::string nombreDueno;
    double posX;
    double posY;
    int rapidez;
    int angulo;
    int deltaAngulo;
    int rango = 50;

public:
    Equipo(std::string nombre, double x, double y, int vel, int ang, int dAng);
    virtual ~Equipo() = default;

    std::string getNombreDueno() const;
    double getX() const;
    double getY() const;
    int getRapidez() const;
    int getAngulo() const;
    int getDeltaAngulo() const;
    bool enRango(Equipo * equipo);

    // Movimiento: avanza dt segundos dentro de los límites [0,w]x[0,h]
    void mover(double dt, int w, int h);
};

#endif // EQUIPO_H
