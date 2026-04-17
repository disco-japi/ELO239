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

# Requisitos:  
-  Java 11 o superior
-  sistema operativo Linux

Compilación:
  Bash:
    javac *.java
    java SimuladorTest config.txt move.txt



# Ejemplo de salida:

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
