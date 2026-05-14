import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Text;

public class TabletView extends Group {
    private final Rectangle rect;
    private Timeline radarUpdt;
    private final Text label;

    public TabletView(Tablet tablet, Territory territory) {
        double w = 20, h = 15;
        rect = new Rectangle(w, h, Color.LIMEGREEN); // crea un rectangulo verde
        label = new Text(tablet.getOwnerName()); // crea un texto con el nombre del dueño de la tablet

        rect.xProperty().bind(tablet.xProperty().subtract(w / 2)); // centra el rectangulo en la posicion de la tablet
        rect.yProperty().bind(tablet.yProperty().subtract(h / 2));

        label.xProperty().bind(tablet.xProperty().add(w / 2 + 4)); // posiciona el texto al rectangulo
        label.yProperty().bind(tablet.yProperty().add(h / 2 + 4));

        this.getChildren().addAll(rect, label);
        radarUpdt = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            Cellular cell = territory.findNearByCellular(tablet);/// busca el celular mas cercano al tag
            if (cell != null)
                cell.reportTabletLocation(tablet);
            makeRadar(tablet);
        }));
        radarUpdt.setCycleCount(Animation.INDEFINITE);
        radarUpdt.play();
    }

    private void makeRadar(EloTelTag tag) {
        Circle radar = new Circle();
        // radar semitransparente que crecerá y se desvanecerá
        radar.setFill(Color.rgb(0, 255, 0, 0.50));
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