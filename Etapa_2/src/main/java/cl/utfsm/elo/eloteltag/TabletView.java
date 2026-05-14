package cl.utfsm.elo.eloteltag;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TabletView extends Group {
    private final Tablet tablet;
    private final Rectangle rect;
    private final Text label;

    public TabletView(Tablet tablet) {
        this.tablet = tablet;
        double w = 20, h = 15;
        rect = new Rectangle(w, h, Color.LIMEGREEN); // crea un rectangulo verde
        label = new Text(tablet.getOwnerName()); // crea un texto con el nombre del dueño de la tablet

        rect.xProperty().bind(tablet.xProperty().subtract(w / 2)); // centra el rectangulo en la posicion de la tablet
        rect.yProperty().bind(tablet.yProperty().subtract(h / 2));

        label.xProperty().bind(tablet.xProperty().add(w / 2 + 4)); // posiciona el texto al rectangulo
        label.yProperty().bind(tablet.yProperty().add(h / 2 + 4));

        this.getChildren().addAll(rect, label);
    }
}