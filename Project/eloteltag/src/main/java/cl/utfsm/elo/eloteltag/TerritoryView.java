package cl.utfsm.elo.eloteltag;

import java.time.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * Vista principal del territorio donde se mueven todos los equipos.
 *
 * <p>
 * Esta clase maneja la representación visual del territorio, incluyendo:
 * la imagen de fondo (mapa), el contenedor para todos los equipos (celulares,
 * tags y tablets), y las dimensiones del territorio que se utilizan para
 * los cálculos de rebote en los bordes.
 * </p>
 *
 * <p>
 * Las dimensiones del territorio ({@link #WIDTH} y {@link #HEIGHT}) se
 * obtienen automáticamente de la imagen de fondo y son utilizadas por la
 * clase {@link Equipo} para detectar y manejar los rebotes en los bordes.
 * </p>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see Territory
 * @see Equipo
 * @see Stage4
 */
public class TerritoryView extends ScrollPane {
  private Pane pane; // to place each piece of equipment
  public static double WIDTH;
  public static double HEIGHT;

  /**
   * Constructor de la vista del territorio.
   *
   * <p>
   * Carga la imagen de fondo especificada en el archivo de configuración,
   * obtiene sus dimensiones (ancho y alto) que servirán como límites del
   * territorio para el rebote de los equipos, y crea un contenedor
   * superpuesto donde se colocarán las vistas de los equipos.
   * </p>
   *
   * @param territory Referencia al modelo del territorio (no se usa directamente
   *                  en la vista, pero se mantiene para consistencia)
   * @param imageName Nombre del archivo de imagen de fondo (debe estar en la
   *                  misma carpeta que el ejecutable). Ejemplo: "Placeres.jpg"
   */
  public TerritoryView(Territory territory, String imageName) {
    Image image = new Image("file:" + imageName);
    ImageView mapView = new ImageView(image);
    WIDTH = image.getWidth();
    HEIGHT = image.getHeight();

    pane = new Pane();
    StackPane territoryPane = new StackPane();
    territoryPane.getChildren().addAll(mapView, pane);
    this.setContent(territoryPane);
    // ¿....?
  }

  /**
   * Agrega una vista de equipo al territorio.
   *
   * <p>
   * Este método se utiliza para añadir las representaciones visuales
   * de celulares, tags y tablets al panel principal del territorio.
   * Las vistas se dibujarán sobre la imagen de fondo en las posiciones
   * correspondientes según las coordenadas de cada equipo.
   * </p>
   *
   * @param equipo La vista del equipo a agregar (puede ser una instancia
   *               de {@code CellularView}, {@code EloTelTagView} o
   *               {@code TabletView})
   */
  public void add(Node equipo) {
    pane.getChildren().add(equipo);
  }
}
