import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class EloTelTagView extends Group {
    private final Circle circle;
    private final Text label;

    public EloTelTagView(EloTelTag tag) {
        circle = new Circle(6, Color.RED); // crea un circulo rojo
        label = new Text(tag.getName());

        circle.centerXProperty().bind(tag.xProperty()); // el circulo siga al tag
        circle.centerYProperty().bind(tag.yProperty());

        label.xProperty().bind(tag.xProperty().add(8)); // etiqueta al tag
        label.yProperty().bind(tag.yProperty().add(8));

        this.getChildren().addAll(circle, label); // agrega el circulo y la etiqueta al grupo
    }

}
