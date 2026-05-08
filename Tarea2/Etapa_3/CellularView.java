import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.Scene;

public class CellularView extends Group {
    private final Rectangle rect;
    private final Text label;
    public ContextMenu menu;
    private Stage infoWindow;
    private Scene infoScene;
    private HBox layout;

    public CellularView(Cellular cellular, Pane pane) {
        infoWindow = new Stage();
        layout = new HBox();
        infoScene = new Scene(layout, 200, 300);
        infoWindow.setScene(infoScene);
        infoWindow.setTitle(cellular.getOwnerName());
        menu = new ContextMenu();
        MenuItem item = new MenuItem("Find My");
        item.setOnAction(e -> infoWindow.show());
        menu.getItems().add(item);
        double width = 12;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.DODGERBLUE);
        rect.setArcWidth(4);
        rect.setArcHeight(4);
        this.setOnMouseClicked(
                e -> menu.show(pane, e.getScreenX(), e.getScreenY()));
        // . ¿....?
        label = new Text(cellular.getOwnerName());
        // Centrar el rectángulo en (x, y) del modelo
        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        rect.yProperty().bind(cellular.yProperty().subtract(height / 2));
        // ¿....?

        // Ubicar la etiqueta a la derecha del rectángulo
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        label.yProperty().bind(cellular.yProperty().add(height / 2 + 4));
        // ¿...?
        this.getChildren().addAll(rect, label);
        // ¿....?
    }
}