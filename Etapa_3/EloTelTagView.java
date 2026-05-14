import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EloTelTagView extends Group {
    private final Circle circle;
    private final Text label;
    private Timeline radarUpdt;

    public EloTelTagView(EloTelTag tag, Territory territory) {
        circle = new Circle(6, Color.RED); // crea un circulo rojo
        label = new Text(tag.getName());

        circle.centerXProperty().bind(tag.xProperty()); // el circulo siga al tag
        circle.centerYProperty().bind(tag.yProperty());

        label.xProperty().bind(tag.xProperty().add(8)); // etiqueta al tag
        label.yProperty().bind(tag.yProperty().add(8));

        this.getChildren().addAll(circle, label); // agrega el circulo y la etiqueta al grupo

        // Ejecuta makeRadar cada 4 segundos pasando el tag actual
        radarUpdt = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            Cellular cell = territory.findNearByCellular(tag);
            if (cell != null) {
                cell.reportTagLocation(tag);
            }
            makeRadar(tag);
        }));
        radarUpdt.setCycleCount(Animation.INDEFINITE);
        radarUpdt.play();
    }

    private void makeRadar(EloTelTag tag) {
        Circle radar = new Circle();
        // radar semitransparente que crecerá y se desvanecerá
        radar.setFill(Color.rgb(255, 0, 0, 0.35));
        radar.setRadius(1);
        radar.centerXProperty().bind(tag.xProperty());
        radar.centerYProperty().bind(tag.yProperty());

        // Añadir al grupo para que sea visible
        this.getChildren().add(radar);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.8), radar);
        scale.setFromX(1);
        scale.setFromY(1);
        scale.setToX(50);
        scale.setToY(50);

        FadeTransition fade = new FadeTransition(Duration.seconds(0.8), radar);
        fade.setFromValue(0.7);
        fade.setToValue(0.0);

        ParallelTransition pt = new ParallelTransition(radar, scale, fade);
        pt.setOnFinished(e -> this.getChildren().remove(radar));
        pt.play();
    }
}
