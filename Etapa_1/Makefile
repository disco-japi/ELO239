# Ruta a las librerías de JavaFX en tu máquina virtual
PATH_TO_FX = /usr/share/openjfx/lib
JFLAGS = --module-path $(PATH_TO_FX) --add-modules javafx.controls,javafx.fxml
JC = javac
JVM = java

# Archivos a compilar para la Etapa 1 de la Tarea 2
CLASSES = \
	Equipo.java \
	Cellular.java \
	Territory.java \
	CellularView.java \
	TerritoryView.java \
	Stage1.java

default: classes

classes:
	$(JC) $(JFLAGS) $(CLASSES)

run: classes
	$(JVM) $(JFLAGS) Stage1

clean:
	rm -f *.class
