#ifndef TERRITORY_H
#define TERRITORY_H

#include <string>

class Territory {
private:
    std::string imagePath;

public:
    Territory(std::string path);
    std::string getImagePath() const;
};

#endif // TERRITORY_H