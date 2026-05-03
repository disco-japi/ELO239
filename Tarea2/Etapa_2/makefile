# Ruta a las librerías de JavaFX en tu máquina virtual
PATH_TO_FX = /usr/share/openjfx/lib
JFLAGS = --module-path $(PATH_TO_FX) --add-modules javafx.controls,javafx.fxml
JC = javac
JVM = java

# Archivos a compilar para la Etapa 2 de la Tarea 2
CLASSES = \
Equipo.java \
    Cellular.java \
    EloTelTag.java \
    Tablet.java \
    Territory.java \
    CellularView.java \
    EloTelTagView.java \
    TabletView.java \
    TerritoryView.java \
    Stage2.java

default: classes

classes:
	$(JC) $(JFLAGS) $(CLASSES)

run: classes
	$(JVM) $(JFLAGS) Stage2

clean:
	rm -f *.class