package cl.utfsm.elo.eloteltag;

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

/**
 * Vista gráfica de una Tablet en el territorio.
 *
 * <p>
 * Esta clase maneja la representación visual de una tablet en la simulación.
 * Muestra un rectángulo verde para representar la tablet y una etiqueta con
 * el nombre de su dueño.
 * </p>
 *
 * <p>
 * Las tablets, al igual que los tags, no tienen GPS propio. Cada 5 segundos:
 * </p>
 * <ul>
 * <li>Buscan un celular cercano dentro del rango de detección</li>
 * <li>Si encuentran uno, reportan su posición a través de él</li>
 * <li>Generan un efecto visual de radar (círculo verde expansivo)</li>
 * </ul>
 *
 * <p>
 * La diferencia con los {@link EloTelTagView} es que las tablets
 * buscan celulares cada 5 segundos (los tags lo hacen cada 4 segundos).
 * </p>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see Tablet
 * @see Territory
 * @see EloTelTagView
 */

public class TabletView extends Group {
    private final Rectangle rect;
    private Timeline radarUpdt;
    private final Text label;

    /**
     * Constructor de la vista de la tablet.
     *
     * <p>
     * Configura el rectángulo y la etiqueta de la tablet, bindeando sus
     * posiciones a las propiedades del modelo para que se muevan automáticamente.
     * También inicia un Timeline que cada 5 segundos:
     * </p>
     * <ol>
     * <li>Busca un celular cercano a la tablet</li>
     * <li>Si existe, reporta la posición de la tablet a través del celular</li>
     * <li>Genera un efecto visual de radar</li>
     * </ol>
     *
     * @param tablet    El modelo de la tablet a visualizar
     * @param territory Referencia al territorio para buscar celulares cercanos
     */
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

    /**
     * Crea un efecto visual de radar.
     *
     * <p>
     * Genera un círculo verde semitransparente que crece desde la posición
     * de la tablet hasta alcanzar un radio de 50 píxeles, mientras se desvanece
     * gradualmente. El efecto dura aproximadamente 0.8 segundos.
     * </p>
     *
     * <p>
     * Este efecto simula la "señal" que emite la tablet para buscar
     * celulares cercanos. El color verde distingue la señal de una tablet
     * de la señal roja de un tag.
     * </p>
     *
     * @param tag Tableta (hereda de EloTelTag) que emite la señal de radar
     */
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