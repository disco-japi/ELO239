# Tarea 1 - ELO239
## Simulación de ELOTelTag y aplicación FindMy

Este proyecyo busca simular un sistema de localización de dispositivos inspirado en airtag. 

Link GitHub: [(https://github.com/disco-japi/ELO239)]
## Implementaciones:
- Dispositivos: EloTelTag, Celular,Tablet
- Nube de localización (ETNube) que registra posiciones
- Comando FindMy para visualizar equipos de una persona
- Comando Sound para localizar equipos cercanos


# Integrantes grupo 6:   

- Alejandro Díaz (Rol: 202430529-8)
- Álvaro Leal (Rol: 202430548-4)
- Pablo Rodríguez (Rol: 202430541-7)
- Sebastián Saldías (Rol: 202430534-4)

# Requisitos
-  Java (JDK y JRE) 11 o superior

# Pasos para ejecutar
En una terminal compatible con bash y GNU Make, ejecutar `make` en la carpeta del hito deseado, y ejecutar `make run` para iniciar el programa con los ajustes preestablecidos.


# Ejemplo de salida

```
maleta sonando
Pedro.celular => distancia: 3.22 m, ángulo: 299.7°
maleta sonando
Pedro.celular => distancia: 0.00 m, ángulo: 0.0°
Error: Equipo 'llaves' no encontrado para el dueño Pedro

-*-*-*-FIND MY-*-*-*-

-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
Datos de Pedro:
-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

------items------

maleta: 13.40, 10.60
mochila: 13.40, 10.60
dispsitivos:
celular: 13.40, 10.60
tablet: 13.40, 10.60
Equipo 'llaves' está demasiado lejos (44.82 m). No se puede activar sonido.
tablet sonando
Pedro.celular => distancia: 0.00 m, ángulo: 0.0°
mochila sonando
Diego.celular => distancia: 0.00 m, ángulo: 0.0°
-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
Datos de Pedro:
-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

------items------

maleta: 13.40, 10.60
mochila: 13.40, 10.60
dispsitivos:
celular: 13.40, 10.60
tablet: 13.40, 10.60
-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
Datos de Juan:
-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

------items------

llaves: 25.10, 25.80
dispsitivos:
celular: 50.10, 63.00
maleta sonando
Pedro.celular => distancia: 0.00 m, ángulo: 0.0°
Equipo 'llaves' está demasiado lejos (44.82 m). No se puede activar sonido.

```


Cellular: 
clase que representa un dispositivo con gps que envia su ubicación 
 métodos:
 reportLocation: informa la ubicación 
 reportTagLocation: informa la ubicacion de un tag cercano 
 reportTabletLocation: informa la ubicación de un tag cercano
 findMy: muestra equipos del dueño
 sound: hace que el dispositivo localice un equipo y lo haga sonar
 
EloTelTag:
clase que representa un dispositivo tipo airtag
 métodos:
 getName:  retorna el nombre del dispositivo 
 sonar: imprime "dispositivo" sonando

Tablet:
clase que representa un dispositivo sin gps, que utiliza la clase cellular
 métodos:
 findMy: muestra equipos del dueño
 
ETnube:
clase que representa el registro cebtral de todas las posiciones de los dispositivos 

Territory:
clase que gedtiona equipos y determina cercanías

viewer:
clase que permite visualizar los equipos de una persona

SimuladorTest: 
ejecuta la simulación 



