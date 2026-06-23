#include "territory.h"

Territory::Territory(std::string path) : imagePath(path) {}

std::string Territory::getImagePath() const {
    return imagePath;
}