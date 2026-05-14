
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;

public class CellularView extends Group {
    private final Rectangle rect;
    private final Text label;
    private ContextMenu menu;
    private Stage infoWindow;
    private Scene infoScene;
    private HBox layout;
    private Label infoLabel;

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
    }
}