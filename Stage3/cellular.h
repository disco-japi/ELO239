    #ifndef CELLULAR_H
#define CELLULAR_H

#include "equipo.h"
#include "etnube.h"
#include "elotelTag.h"
#include "tablet.h"

class Cellular : public Equipo {
    Q_OBJECT
private slots:
   // void onETNube();
private:
    ETNube *nube;
public:
    Cellular(std::string nombre, double x, double y, int vel, int ang, int dAng, ETNube *nube);
    ~Cellular() override = default;
    void reportarUbicacionTag(EloTelTag * tag);
    void reportarUbicacionTablet(Tablet * tab);
};

#endif // CELLULAR_H
