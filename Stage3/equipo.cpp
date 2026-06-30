#include "equipo.h"
#include <cmath>
#include <cstdlib>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

Equipo::Equipo(std::string nombre, std::string nombreEq, double x, double y, int vel, int ang, int dAng)
    : nombreDueno(nombre), nombreEquipo(nombreEq), posX(x), posY(y), rapidez(vel), angulo(ang), deltaAngulo(dAng) {}

std::string Equipo::getNombreDueno() const { return nombreDueno; }
std::string Equipo::getNombreEquipo() const { return nombreEquipo; }
double Equipo::getX() const { return posX; }
double Equipo::getY() const { return posY; }
int Equipo::getRapidez() const { return rapidez; }
int Equipo::getAngulo() const { return angulo; }
int Equipo::getDeltaAngulo() const { return deltaAngulo; }

void Equipo::mover(double dt, int w, int h)
{
    // Variación aleatoria del ángulo en [-deltaAngulo, +deltaAngulo]
    if (deltaAngulo > 0)
    {
        int variacion = (rand() % (2 * deltaAngulo + 1)) - deltaAngulo;
        angulo += variacion;
    }

    double rad = angulo * M_PI / 180.0;
    double vx = rapidez * std::cos(rad);
    double vy = rapidez * std::sin(rad);

    double nx = posX + vx * dt;
    double ny = posY + vy * dt;

    // Rebote horizontal
    if (nx < 0)
    {
        nx = -nx;
        vx = -vx;
    }
    else if (nx > w)
    {
        nx = 2 * w - nx;
        vx = -vx;
    }

    // Rebote vertical
    if (ny < 0)
    {
        ny = -ny;
        vy = -vy;
    }
    else if (ny > h)
    {
        ny = 2 * h - ny;
        vy = -vy;
    }

    posX = nx;
    posY = ny;

    // Recalcular ángulo desde las componentes de velocidad resultantes
    angulo = (int)(std::atan2(vy, vx) * 180.0 / M_PI);
}
bool Equipo::enRango(Equipo *equipo)
{
    double dx = this->getX() - equipo->getX();
    double dy = this->getY() - equipo->getY();
    double distance = (double)std::sqrt(dx * dx + dy * dy);  /// pitagoras
    double round = (double)std::round(distance * 100) / 100; /// redondea a 2 decimales
    return round <= rango;
}
