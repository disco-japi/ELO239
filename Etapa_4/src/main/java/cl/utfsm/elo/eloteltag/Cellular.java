package cl.utfsm.elo.eloteltag;

/**
 * Representa un teléfono celular con capacidad GPS.
 *
 * <p>
 * Los celulares son los únicos dispositivos que conocen su propia posición
 * gracias al GPS. Pueden:
 * </p>
 * <ul>
 * <li>Reportar su propia posición a la ETNube cada vez que se mueven</li>
 * <li>Reportar la posición de tags y tablets cercanos (dentro del rango de
 * detección)</li>
 * <li>Ejecutar FindMy para mostrar los equipos del dueño (implementado en la
 * vista)</li>
 * </ul>
 *
 * <p>
 * Los celulares se mueven autónomamente por el territorio siguiendo las
 * mismas reglas de movimiento que los demás equipos.
 * </p>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see Equipo
 * @see ETNube
 * @see EloTelTag
 * @see Tablet
 */
public class Cellular extends Equipo {
    /**
     * Constructor de la clase Cellular.
     *
     * @param owner Nombre del dueño del celular
     * @param x     Posición inicial X (píxeles)
     * @param y     Posición inicial Y (píxeles)
     * @param r     Rapidez de movimiento (píxeles/segundo)
     * @param theta Ángulo inicial de movimiento (radianes)
     * @param dt    Delta de ángulo para movimiento aleatorio (radianes)
     * @param nube  Referencia a la ETNube para reportar posiciones
     */
    public Cellular(String owner, double x, double y, double r, double theta, double dt, ETNube nube) {
        super(owner, x, y, r, theta, dt);
        this.nube = nube;
    }

    /**
     * Reporta la ubicación de un tag cercano a la ETNube.
     *
     * <p>
     * La posición reportada es la del celular (no la del tag), ya que
     * el tag no tiene GPS. Esto simula que el celular "detecta" al tag
     * dentro de su rango de alcance.
     * </p>
     *
     * @param tag El tag que se está reportando
     */

    /// Reporta la ubicación del tag
    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x.getValue(), this.y.getValue());
    }

    /**
     * Reporta la ubicación de una tablet cercana a la ETNube.
     *
     * <p>
     * La posición reportada es la del celular (no la de la tablet), ya que
     * la tablet no tiene GPS. Esto simula que el celular "detecta" a la tablet
     * dentro de su rango de alcance.
     * </p>
     *
     * @param tablet La tablet que se está reportando
     */

    /// Reporta la ubicacion de la tablet cercana al rango
    public void reportTabletLocation(Tablet tablet) {
        nube.updateLocation(tablet.getOwnerName(), "Tablet", this.x.getValue(), this.y.getValue());
    }

    /**
     * Reporta la propia ubicación del celular a la ETNube.
     *
     * <p>
     * Se llama automáticamente cada vez que el celular se mueve
     * (dentro del método moveAll de Territory).
     * </p>
     */

    /// Reporta la ubicación del celular a la nube
    public void reportLocation() {
        nube.updateLocation(this.ownerName, "Celular", this.x.getValue(), this.y.getValue());
    }

    // Método para la funcionalidad de FindMy
    // public void findMy() {
    // visor.showlocation(this.ownerName);
    // }

    private ETNube nube;
}
