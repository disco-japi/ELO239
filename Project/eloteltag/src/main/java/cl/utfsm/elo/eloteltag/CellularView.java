package cl.utfsm.elo.eloteltag;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * Vista gráfica de un celular en el territorio. *
 * <p>
 * Esta clase maneja la representación visual de un celular en la simulación.
 * Muestra un rectángulo azul para representar el celular y una etiqueta con
 * el nombre de su dueño.
 * </p>
 *
 * <p>
 * Proporciona un menú contextual con dos opciones:
 * </p>
 * <ul>
 * <li><b>Find My</b>: Muestra una ventana con la información de todos los
 * equipos del dueño (actualizada cada 1 segundo)</li>
 * <li><b>GFind My</b> (Extra-crédito): Muestra una ventana gráfica con la
 * imagen de fondo y las vistas de los equipos del dueño</li>
 * </ul>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see Cellular
 * @see ETNube
 * @see TerritoryView
 */
public class CellularView extends Group {
    private final Rectangle rect;
    private final Text label;
    private ContextMenu menu;
    private Stage infoWindow;
    private Scene infoScene;
    private HBox layout;
    private Label infoLabel;
    private Stage graphicsWindow;

    /**
     * Constructor de la vista del celular.
     *
     * <p>
     * Configura el rectángulo y la etiqueta del celular, bindeando sus
     * posiciones a las propiedades del modelo para que se muevan automáticamente.
     * También configura el menú contextual.
     * </p>
     *
     * @param cellular El modelo del celular a visualizar
     * @param pane     Panel donde se mostrará el menú contextual
     * @param nube     Referencia a la ETNube para obtener información de Find My
     */
    public CellularView(Cellular cellular, Pane pane, ETNube nube) {
        makeCMenu(cellular.getOwnerName(), nube);
        double width = 12;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.DODGERBLUE);
        rect.setArcWidth(4);
        rect.setArcHeight(4);
        this.setOnMouseClicked(
                e -> menu.show(pane, e.getScreenX(), e.getScreenY()));
        label = new Text(cellular.getOwnerName());
        // Centrar el rectángulo y el circulo en (x, y) del modelo
        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        rect.yProperty().bind(cellular.yProperty().subtract(height / 2));
        // Ubicar la etiqueta a la derecha del rectángulo
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        label.yProperty().bind(cellular.yProperty().add(height / 2 + 4));
        this.getChildren().addAll(rect, label);
    }

    /**
     * Crea el menú contextual y las ventanas de Find My y GFind My.
     *
     * <p>
     * Configura:
     * </p>
     * <ul>
     * <li>Ventana Find My con actualización automática cada 1 segundo</li>
     * <li>Ventana GFind My (extra-crédito) con actualización automática</li>
     * <li>Opciones del menú contextual</li>
     * </ul>
     *
     * @param ownerName Nombre del dueño del celular
     * @param nube      Referencia a la ETNube
     */

    private void makeCMenu(String ownerName, ETNube nube) {
        infoWindow = new Stage();
        layout = new HBox();
        infoScene = new Scene(layout, 200, 200);
        infoWindow.setScene(infoScene);
        infoWindow.setTitle(ownerName);
        infoLabel = new Label();
        infoLabel.setText(nube.getFindMy(ownerName));
        layout.getChildren().add(infoLabel);
        menu = new ContextMenu();
        MenuItem item = new MenuItem("Find My");
        item.setOnAction(e -> {
            infoLabel.setText(nube.getFindMy(ownerName));
            infoWindow.show();
        });
        menu.getItems().add(item);
        Timeline updater = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (infoWindow.isShowing()) {
                        infoLabel.setText(nube.getFindMy(ownerName));
                    }
                }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
        graphicsWindow = new Stage();
        graphicsWindow.setTitle("GFind My - " + ownerName);

        Timeline graphicsUpdater = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (graphicsWindow.isShowing()) {
                        updateGraphicsWindow(ownerName, nube);
                    }
                }));
        graphicsUpdater.setCycleCount(Timeline.INDEFINITE);
        graphicsUpdater.play();

        menu = new ContextMenu();

        MenuItem findMyItem = new MenuItem("Find My");
        findMyItem.setOnAction(e -> {
            infoLabel.setText(nube.getFindMy(ownerName));
            infoWindow.show();
        });

        MenuItem gFindMyItem = new MenuItem("GFind My");
        gFindMyItem.setOnAction(e -> {
            updateGraphicsWindow(ownerName, nube);
            graphicsWindow.show();
        });

        menu.getItems().addAll(findMyItem, gFindMyItem);
    }

    /**
     * Actualiza la ventana gráfica de GFind My.
     *
     * <p>
     * Muestra los equipos de la persona sobre la imagen de fondo.
     * Este método es llamado cada 1 segundo mientras la ventana está abierta
     * para reflejar los movimientos de los equipos.
     * </p>
     *
     * <p>
     * <b>Nota:</b> Asegúrate que la clase {@code ETNube.EquipoInfo} tenga
     * un atributo llamado {@code nombre} (no {@code Equiponame}).
     * </p>
     *
     * @param ownerName Nombre del dueño
     * @param nube      Referencia a la ETNube
     */
    private void updateGraphicsWindow(String ownerName, ETNube nube) {
        Pane graphicsPane = new Pane();
        javafx.scene.image.Image image = new javafx.scene.image.Image("file:Placeres.jpg");
        javafx.scene.image.ImageView mapView = new javafx.scene.image.ImageView(image);

        java.util.ArrayList<ETNube.EquipoInfo> equipos = nube.getEquiposByOwner(ownerName);

        for (ETNube.EquipoInfo equipo : equipos) {
            Group vistaEquipo = crearVistaEquipo(equipo.Equiponame, equipo.x, equipo.y);
            if (vistaEquipo != null) {
                graphicsPane.getChildren().add(vistaEquipo);
            }
        }

        StackPane root = new StackPane();
        root.getChildren().addAll(mapView, graphicsPane);

        Scene scene = new Scene(root, 800, 600);
        graphicsWindow.setScene(scene);
    }

    /**
     * Crea una vista visual para un equipo específico.
     *
     * <p>
     * Según el tipo de equipo, crea una representación visual:
     * </p>
     * <ul>
     * <li><b>Celular</b>: Rectángulo azul (12x24) con texto "Cel"</li>
     * <li><b>Tablet</b>: Rectángulo verde (20x15) con texto "Tbl"</li>
     * <li><b>Tag</b>: Círculo rojo (radio 6) con nombre abreviado</li>
     * </ul>
     *
     * @param equipmentName Nombre del equipo (determina el tipo y texto)
     * @param x             Posición X del equipo en el mapa
     * @param y             Posición Y del equipo en el mapa
     * @return Grupo con la representación visual completa del equipo
     */
    private Group crearVistaEquipo(String equipmentName, double x, double y) {
        Group grupo = new Group();

        if (equipmentName.equals("Celular")) {
            Rectangle rect = new Rectangle(12, 24);
            rect.setFill(Color.DODGERBLUE);
            rect.setArcWidth(4);
            rect.setArcHeight(4);
            rect.setX(x - 6);
            rect.setY(y - 12);
            grupo.getChildren().add(rect);

            Text text = new Text(equipmentName.substring(0, Math.min(3, equipmentName.length())));
            text.setX(x + 8);
            text.setY(y + 4);
            grupo.getChildren().add(text);

        } else if (equipmentName.equals("Tablet")) {
            Rectangle rect = new Rectangle(20, 15);
            rect.setFill(Color.LIMEGREEN);
            rect.setX(x - 10);
            rect.setY(y - 7.5);
            grupo.getChildren().add(rect);

            Text text = new Text("Tbl");
            text.setX(x + 12);
            text.setY(y + 4);
            grupo.getChildren().add(text);

        } else {
            Circle circle = new Circle(6);
            circle.setFill(Color.RED);
            circle.setCenterX(x);
            circle.setCenterY(y);
            grupo.getChildren().add(circle);

            Text text = new Text(equipmentName.substring(0, Math.min(4, equipmentName.length())));
            text.setX(x + 8);
            text.setY(y + 4);
            grupo.getChildren().add(text);
        }

        return grupo;
    }
}
