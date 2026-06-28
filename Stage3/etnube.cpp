#include "etnube.h"
ETNube::Data::Data(std::string owner, std::string equipment, QPointF *loc)
{
    this->ownerName = owner;
    this->equipmentName = equipment;
    this->location = loc;
}

ETNube::EquipoInfo::EquipoInfo(std::string equipo_name, double x, double y)
{
    this->x = x;
    this->y = y;
    this->equipo_name = equipo_name;
};

ETNube::ETNube()
{
}
QPointF *ETNube::getLocation(std::string owner, std::string equipment)
{
    for (int i = 0; i < (int)cloudData.size(); i++)
    {
        Data &data = cloudData[i];
        if (data.ownerName == owner && data.equipmentName == equipment)
        {
            return data.location;
        }
    }
    return nullptr;
}

void ETNube::updateLocation(std::string owner, std::string equipment, double x, double y)
{
    QPointF *location = getLocation(owner, equipment);
    if (location == nullptr)
    {
        location = new QPointF(x, y);
        cloudData.push_back(Data(owner, equipment, location));
    }
    location->setX(x);
    location->setY(y);
}
std::vector<ETNube::EquipoInfo> ETNube::getEquiposByOwner(std::string owner)
{
    std::vector<EquipoInfo> equipos;
    for (int i = 0; i < (int)cloudData.size(); i++)
    {
        Data &data = cloudData[i];
        if (data.ownerName == owner)
        {
            equipos.insert(equipos.end(), EquipoInfo(data.equipmentName, data.location->x(), data.location->y()));
        }
    }
    return equipos;
}

std::string ETNube::getFindMy(std::string owner)
{
    std::string h1 = "Bienes de " + owner + "\nÍtems:\n";
    std::string items = "";
    std::string h2 = "Dispositivos:\n";
    std::string devices = "";
    for (int i = 0; i < (int)cloudData.size(); i++)
    {
        Data &data = cloudData[i];
        if (data.ownerName == owner)
        {
            std::string buffer = (data.equipmentName + ": (" + std::to_string((int)getLocation(owner, data.equipmentName)->x()) + " , " + std::to_string((int)getLocation(owner, data.equipmentName)->y()) + ")\n");
            if (data.equipmentName != "Celular" && data.equipmentName != "Tablet")
            {
                items.append(buffer);
            }
            else
            {
                devices.append(buffer);
            }
        }
    }
    return h1 + items + h2 + devices;
}
ETNube::~ETNube()
{
}