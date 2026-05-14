package cl.utfsm.elo.eloteltag;

/**
 * Representa un dispositivo EloTelTag (rastreador de objetos).
 *
 * <p>
 * Similar a un AirTag, es un dispositivo que se puede adjuntar a objetos
 * personales (llaves, mochila, maleta, etc.). No tiene GPS propio, por lo que
 * su posición es reportada a la ETNube a través de celulares cercanos cuando
 * están dentro del rango de detección.
 * </p>
 *
 * <p>
 * El rango de detección es de {@value #TRACKING_RANGE} píxeles
 * (10 metros en la simulación, asumiendo 1 píxel = 1 metro).
 * </p>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see Equipo
 * @see Cellular
 * @see ETNube
 */

public class EloTelTag extends Equipo {

    /**
     * Constructor de EloTelTag.
     *
     * @param owner Nombre del dueño del tag
     * @param n     Nombre identificador del tag
     * @param x     Posición inicial X (píxeles)
     * @param y     Posición inicial Y (píxeles)
     * @param r     Rapidez de movimiento (píxeles/segundo)
     * @param theta Ángulo inicial de movimiento (radianes)
     * @param dt    Delta de ángulo para movimiento aleatorio (radianes)
     */
    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dt) {
        super(owner, x, y, r, theta, dt);
        name = n;
    }

    /**
     * Obtiene el nombre identificador del tag.
     *
     * @return Nombre del tag
     */

    public String getName() {
        return name;
    }

    /**
     * Verifica si el tag está dentro del rango de detección de un celular.
     *
     * <p>
     * Calcula la distancia euclidiana entre el tag y el celular.
     * La distancia se redondea a 2 decimales.
     * </p>
     *
     * @param cell Celular a verificar
     * @return {@code true} si la distancia ≤ {@value #TRACKING_RANGE} píxeles,
     *         {@code false} en caso contrario
     */

    public boolean isWithinRange(Cellular cell) {
        double dx = this.x.getValue() - cell.x.getValue();
        double dy = this.y.getValue() - cell.y.getValue();
        double distance = (double) Math.sqrt(dx * dx + dy * dy);/// pitagoras
        double round = (double) Math.round(distance * 100) / 100;/// redondea a 2 decimales
        return round <= TRACKING_RANGE;
    }

    private final String name;
    private static final double TRACKING_RANGE = 50;
}
