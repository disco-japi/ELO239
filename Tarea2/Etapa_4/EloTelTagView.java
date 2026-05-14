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
/**
 * Vista gráfica de un EloTelTag en el territorio.
 *
 * <p>Esta clase maneja la representación visual de un tag en la simulación.
 * Muestra un círculo rojo para representar el tag y una etiqueta con su nombre.</p>
 *
 * <p>Los tags no tienen GPS propio. Cada 4 segundos:</p>
 * <ul>
 *   <li>Buscan un celular cercano dentro del rango de detección (10 píxeles)</li>
 *   <li>Si encuentran uno, reportan su posición a través de él</li>
 *   <li>Generan un efecto visual de radar (círculo rojo expansivo)</li>
 * </ul>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see EloTelTag
 * @see Territory
 * @see Cellular
 */

/**
 * Constructor de la vista del tag.
 *
 * <p>Configura el círculo y la etiqueta del tag, bindeando sus posiciones
 * a las propiedades del modelo para que se muevan automáticamente.
 * También inicia un Timeline que cada 4 segundos:</p>
 * <ol>
 *   <li>Busca un celular cercano al tag</li>
 *   <li>Si existe, reporta la posición del tag a través del celular</li>
 *   <li>Genera un efecto visual de radar</li>
 * </ol>
 *
 * @param tag       El modelo del tag a visualizar
 * @param territory Referencia al territorio para buscar celulares cercanos
 */
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
    /**
     * Crea un efecto visual de radar.
     *
     * <p>Genera un círculo rojo semitransparente que crece desde la posición
     * del tag hasta alcanzar un radio de 50 píxeles, mientras se desvanece
     * gradualmente. El efecto dura aproximadamente 0.8 segundos.</p>
     *
     * <p>Este efecto simula la "señal" que emite el tag para buscar
     * celulares cercanos.</p>
     *
     * @param tag Tag que emite la señal de radar
     */
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
