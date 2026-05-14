package cl.utfsm.elo.eloteltag;

/**
 * Representa un dispositivo Tablet en la simulación.
 *
 * <p>
 * La Tablet es similar a un {@link EloTelTag} pero con algunas diferencias:
 * no tiene GPS propio, por lo que su posición debe ser reportada por celulares
 * cercanos. A diferencia de los tags comunes, las tablets pueden ejecutar
 * la funcionalidad FindMy para mostrar los equipos de su dueño.
 * </p>
 *
 * <p>
 * El tiempo de búsqueda de celulares cercanos para las tablets es de
 * 5 segundos (mientras que para los tags es de 4 segundos).
 * </p>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see EloTelTag
 * @see Cellular
 * @see ETNube
 */

public class Tablet extends EloTelTag {
    /**
     * Constructor de la clase Tablet.
     *
     * <p>
     * El nombre del equipo se fija automáticamente como "Tablet".
     * </p>
     *
     * @param owner Nombre del dueño de la tablet
     * @param x     Posición inicial X (píxeles)
     * @param y     Posición inicial Y (píxeles)
     * @param r     Rapidez de movimiento (píxeles/segundo)
     * @param theta Ángulo inicial de movimiento (radianes)
     * @param dt    Delta de ángulo para movimiento aleatorio (radianes)
     * @param nube  Referencia a la ETNube para reportar posiciones
     */

    public Tablet(String owner, double x, double y, double r, double theta, double dt, ETNube nube) {
        super(owner, "Tablet", x, y, r, theta, dt);
    }

    // public void findMy() { visor.showlocation(this.ownerName); }

    /// Verifica si el celular está en el rango establecido
}
