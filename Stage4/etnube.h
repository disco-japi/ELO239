#ifndef ETNUBE_H
#define ETNUBE_H
#include <string>
#include <vector>
#include <QPointF>

class ETNube
{
private:
    class Data{
    public:
        Data(std::string owner, std::string equipment, QPointF * loc);
        QPointF * location;
        std::string ownerName, equipmentName;
    };
    std::vector<Data> cloudData;
public:
    ETNube();
    ~ETNube();
    void updateLocation(std::string owner, std::string equipment, double x, double y);
    QPointF * getLocation(std::string owner, std::string equipment);
    std::string getFindMy(std::string owner);
    class EquipoInfo{
    public:
        std::string equipo_name;
        double x;
        double y;
        EquipoInfo(std::string equipo_name, double x, double y);
    };
    std::vector<EquipoInfo> getEquiposByOwner(std::string owner);
};

#endif // ETNUBE_H
