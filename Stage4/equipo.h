#ifndef EQUIPO_H
#define EQUIPO_H

#include <string>
#include <QObject>
#include <QPointF>
#include <QVector>

class Equipo : public QObject  {
    Q_OBJECT
protected:
    std::string nombreDueno;
    std::string nombreEquipo;
    double posX;
    double posY;
    int rapidez;
    int angulo;
    int deltaAngulo;
    int rango = 50;

    // NUEVO: Para la traza
    QVector<QPointF> trazaPoints;
    bool trazaVisible = false;

public:
    Equipo(std::string nombre, std::string nombreEq, double x, double y, int vel, int ang, int dAng);
    virtual ~Equipo() = default;

    std::string getNombreEquipo() const;
    std::string getNombreDueno() const;
    double getX() const;
    double getY() const;
    int getRapidez() const;
    int getAngulo() const;
    int getDeltaAngulo() const;
    bool enRango(Equipo * equipo);

    // NUEVO: Métodos para traza
    void addTrazaPoint(double x, double y);
    void clearTraza();
    void toggleTraza();
    bool isTrazaVisible() const { return trazaVisible; }
    QVector<QPointF> getTrazaPoints() const { return trazaPoints; }

    // Movimiento: avanza dt segundos dentro de los límites [0,w]x[0,h]
    void mover(double dt, int w, int h);
};

#endif