#ifndef TABLET_H
#define TABLET_H

#include <QTimer>
#include "equipo.h"
#include "etnube.h"

class Tablet : public Equipo {
    Q_OBJECT
private slots:
    //void onETNube();
private:
    ETNube *nube;
    QTimer * radarTemp;
public:
    Tablet(std::string nombre, double x, double y, int vel, int ang, int dAng, ETNube *nube);
    ~Tablet() override = default;
};

#endif // TABLET_H
