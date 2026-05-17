# Tarea 2 - ELO239
## Simulador Gráfico de ELOTelTags y Aplicación Find My

Este proyecto busca adeaptar y extender la Tarea 1 para simular graficamente un sistema de localización de dispositivos inspirado en airtag. 

# Integrantes grupo 6:   

- Alejandro Díaz (Rol: 202430529-8)
- Álvaro Leal (Rol: 202430548-4)
- Pablo Rodríguez (Rol: 202430541-7)
- Sebastián Saldías (Rol: 202430534-4)

# Requisitos
-  Java (JDK y JRE) 11 o superior
-  JavaFX correspondiente a la version de JDK instalada

# Puntos extra
La etapa 4 incluye lo solicitado para el puntaje extra.

# Aviso
Implementamos el "Proyecto de IDE" (en nuestro caso, VSCode) en la etapa final de la tarea. Lo cual implicó hacer un refactor total de todas las etapas, al mover los archivos fuente a las carpetas correctas, por eso si se revisa el historial de cambios de los archivos individuales, aparecerá todo como un solo commit. Eso no volverá a ocurrir para futuras tareas, ya que entendemos que esto implica la perdida del rastreo de cambios de los archivos al moverlos entre carpetas.

También, notese que trabajamos en la Tarea 1 desde este mismo repositorio, así que el registro de Commits incluye los commits de la tarea 1 también (hasta el punto que lo movimos a una branch aparte).

# Pasos para ejecutar
## Desde terminal
### Requisitos:
- Maven
Desde la terminal, en la carpeta de la etapa que desea ejecutar, ejecutar el siguiente comando desde la terminal
``` sh
mvn clean javafx:run
```
Esto actualizará las dependencias automáticamente, y ejecutará el programa.
Esto actualiza las dependencias automáticamente, y ejecuta el programa.
## Desde Visual Studio Code
### Requisitos:
- Maven
- Extensión "Language Support for Java" de VS Code
Abrir la carpeta de la etapa a ejecutar desde una instancia de Visual Studio Code. En la sección de explorador de archivos, está una sub-sección de Proyectos Java. Hacer click derecho sobre el proyecto en cuestión, y pulsar "Run", esto ejecutará el programa.