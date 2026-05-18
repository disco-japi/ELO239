
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
import javafx.scene.control.ScrollPane;
/**
 * Vista gr�fica de un celular en el territorio. *
 * <p>Esta clase maneja la representaci�n visual de un celular en la simulaci�n.
 * Muestra un rect�ngulo azul para representar el celular y una etiqueta con
 * el nombre de su due�o.</p>
 *
 * <p>Proporciona un men� contextual con dos opciones:</p>
 * <ul>
 *   <li><b>Find My</b>: Muestra una ventana con la informaci�n de todos los
 *       equipos del due�o (actualizada cada 1 segundo)</li>
 *   <li><b>GFind My</b> (Extra-cr�dito): Muestra una ventana gr�fica con la
 *       imagen de fondo y las vistas de los equipos del due�o</li>
 * </ul>
 *
 * @author �lvaro, Sebasti�n, Alejandro y Pablo
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
     * <p>Configura el rect�ngulo y la etiqueta del celular, bindeando sus
     * posiciones a las propiedades del modelo para que se muevan autom�ticamente.
     * Tambi�n configura el men� contextual.</p>
     *
     * @param cellular El modelo del celular a visualizar
     * @param pane     Panel donde se mostrar� el men� contextual
     * @param nube     Referencia a la ETNube para obtener informaci�n de Find My
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
        // Centrar el rect�ngulo y el circulo en (x, y) del modelo
        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        rect.yProperty().bind(cellular.yProperty().subtract(height / 2));
        // Ubicar la etiqueta a la derecha del rect�ngulo
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        label.yProperty().bind(cellular.yProperty().add(height / 2 + 4));
        this.getChildren().addAll(rect, label);
    }


    /**
     * Crea el men� contextual y las ventanas de Find My y GFind My.
     *
     * <p>Configura:</p>
     * <ul>
     *   <li>Ventana Find My con actualizaci�n autom�tica cada 1 segundo</li>
     *   <li>Ventana GFind My (extra-cr�dito) con actualizaci�n autom�tica</li>
     *   <li>Opciones del men� contextual</li>
     * </ul>
     *
     * @param ownerName Nombre del due�o del celular
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
                })
        );
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
        graphicsWindow = new Stage();
        graphicsWindow.setTitle("GFind My - " + ownerName);

        Timeline graphicsUpdater = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (graphicsWindow.isShowing()) {
                        updateGraphicsWindow(ownerName, nube);
                    }
                })
        );
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
     * Actualiza la ventana gr�fica de GFind My.
     *
     * <p>Muestra los equipos de la persona sobre la imagen de fondo.
     * Este m�todo es llamado cada 1 segundo mientras la ventana est� abierta
     * para reflejar los movimientos de los equipos.</p>
     *
     * @param ownerName Nombre del due�o
     * @param nube      Referencia a la ETNube
     */
    private void updateGraphicsWindow(String ownerName, ETNube nube) {
        ScrollPane scrollPane = new ScrollPane();
        Pane graphicsPane = new Pane();
        javafx.scene.image.Image image = new javafx.scene.image.Image("file:Placeres.jpg");
        javafx.scene.image.ImageView mapView = new javafx.scene.image.ImageView(image);
        double imgWidth = image.getWidth();
        double imgHeight = image.getHeight();
        mapView.setFitWidth(imgWidth);
        mapView.setFitHeight(imgHeight);
        java.util.ArrayList<ETNube.EquipoInfo> equipos = nube.getEquiposByOwner(ownerName);
        for (ETNube.EquipoInfo equipo : equipos) {
            Group vistaEquipo = crearVistaEquipo(equipo.Equiponame, equipo.x, equipo.y);;
            if (vistaEquipo != null) {
                graphicsPane.getChildren().add(vistaEquipo);
            }
        }


        graphicsPane.setPrefSize(imgWidth, imgHeight);
        graphicsPane.setMinSize(imgWidth, imgHeight);
        graphicsPane.setMaxSize(imgWidth, imgHeight);
        Group rootGroup = new Group();
        rootGroup.getChildren().addAll(mapView, graphicsPane);
        scrollPane.setContent(rootGroup);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        double windowWidth = Math.min(imgWidth, 900);
        double windowHeight = Math.min(imgHeight, 700);
        Scene scene = new Scene(scrollPane, windowWidth, windowHeight);
        if (equipos.size() > 0) {
            double sumX = 0, sumY = 0;
            for (ETNube.EquipoInfo equipo : equipos) {
                sumX += equipo.x;
                sumY += equipo.y;
            }
            double centerX = sumX / equipos.size();
            double centerY = sumY / equipos.size();
            javafx.application.Platform.runLater(() -> {
                double viewportWidth = scrollPane.getViewportBounds().getWidth();
                double viewportHeight = scrollPane.getViewportBounds().getHeight();

                double hval = (centerX - viewportWidth / 2) / (imgWidth - viewportWidth);
                double vval = (centerY - viewportHeight / 2) / (imgHeight - viewportHeight);

                scrollPane.setHvalue(Math.max(0, Math.min(1, hval)));
                scrollPane.setVvalue(Math.max(0, Math.min(1, vval)));
            });
        }
        graphicsWindow.setScene(scene);
        graphicsWindow.setTitle("GFind My - " + ownerName);
    }

    /**
     * Crea una vista visual para un equipo espec�fico.
     *
     * <p>Seg�n el tipo de equipo, crea una representaci�n visual:</p>
     * <ul>
     *   <li><b>Celular</b>: Rect�ngulo azul (12x24) con texto "Cel"</li>
     *   <li><b>Tablet</b>: Rect�ngulo verde (20x15) con texto "Tbl"</li>
     *   <li><b>Tag</b>: C�rculo rojo (radio 6) con nombre abreviado</li>
     * </ul>
     *
     * @param equipmentName Nombre del equipo (determina el tipo y texto)
     * @param x             Posici�n X del equipo en el mapa
     * @param y             Posici�n Y del equipo en el mapa
     * @return Grupo con la representaci�n visual completa del equipo
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
