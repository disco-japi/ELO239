# Documentación Tarea 1 - ELO329
### Grupo 6 - 2025.1 - Par. 200
- Alejandro Díaz (Rol: 202430529-8)
- Álvaro Leal (Rol: 202430548-4)
- Pablo Rodríguez (Rol: 202430541-7)
- Sebastián Saldías (Rol: 202430534-4)

## 

## Diagrama de clases
![Diagrama de clases](DiagramaDeClases.png)
## Referencia de clases
A continuación, se presenta la documentación y referencia de clases del programa "Simulador EloTelTag".

### Clase Equipo
java.lang.Object

Equipo

Direct Known Subclasses:
    Cellular, EloTelTag, Tablet

public class Equipo
extends Object
Clase de un equipo genérico


### Clase Cellular
java.lang.Object

Equipo

Cellular

public class Cellular

extends Equipo

Dispositivo localizable con GPS propio

    Field Summary Link icon
    Fields inherited from class Equipo Link icon
    ownerName, x, y
    Constructor Summary Link icon
    Constructors
    Constructor
    Description
    Cellular(String owner, float _x, float _y, ETNube nube)
    Inicializa una instancia de celular con su ubicación inicial

### Clase EloTelTag
java.lang.Object
Equipo
EloTelTag
public class EloTelTag
extends Equipo
Clase del tag, que hereda de la clase base Equipo

### Clase Tablet
java.lang.Object
Equipo
Tablet
public class Tablet
extends Equipo
Dispositivo localizable sin GPS propio

### Clase ETNube
Clase de simulación del servicio en la nube ETNube, para la localización de equipos

### Clase SimuladorTest
Clase principal del simulador

### Clase Territory
Territorio virtual donde los celulares, tags y tablets se localizan y se mueven.

### Clase Viewer
Visualiza los datos en consola