package cl.utfsm.elo.eloteltag;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Clase base abstracta que representa un equipo genérico en la simulación.
 * Todos los equipos (celulares, tags, tablets) tienen un dueño, una posición,
 * una velocidad y pueden moverse por el territorio rebotando en los bordes.
 *
 * @author [Álvaro, Sebastián, Alejandro y Pablo]
 * @version 1.0
 */

public class Equipo {
    protected final String ownerName;
    protected DoubleProperty x = new SimpleDoubleProperty();
    protected DoubleProperty y = new SimpleDoubleProperty();
    private double r, theta, dtheta;

    /**
     * Constructor de la clase Equipo.
     *
     * @param owner  Nombre del dueño del equipo
     * @param _x     Posición inicial X (píxeles)
     * @param _y     Posición inicial Y (píxeles)
     * @param r      Rapidez de movimiento (píxeles/segundo)
     * @param theta  Ángulo inicial de movimiento (radianes)
     * @param dtheta Rango de variación aleatoria del ángulo (radianes)
     */

    public Equipo(String owner, double _x, double _y, double r, double theta, double dtheta) {
        ownerName = owner;
        x.set(_x);
        y.set(_y);
        this.r = r;
        this.theta = theta;
        this.dtheta = dtheta;
    }

    /**
     * Mueve el equipo según su velocidad y ángulo actual.
     *
     * <p>
     * El ángulo varía aleatoriamente dentro del rango definido por {@code dtheta}
     * antes de calcular el nuevo desplazamiento. Al llegar a los bordes del
     * territorio
     * (definidos por {@link TerritoryView#WIDTH} y {@link TerritoryView#HEIGHT}),
     * el equipo rebota:
     * </p>
     * <ul>
     * <li>Rebote horizontal: {@code theta = PI - theta}</li>
     * <li>Rebote vertical: {@code theta = -theta}</li>
     * </ul>
     *
     * @param dt Tiempo transcurrido desde el último movimiento (segundos)
     * @see TerritoryView#WIDTH
     * @see TerritoryView#HEIGHT
     */

    public void move(double dt) {
        theta += (Math.random() - 0.5) * dtheta; // angulo random para theta segun dtheta

        x.set(x.get() + r * Math.cos(theta) * dt);
        y.set(y.get() + r * Math.sin(theta) * dt); // caclcular nuva posiciones segun r, theta y dt

        if (x.get() < 0 || x.get() > TerritoryView.WIDTH) {
            theta = Math.PI - theta; // refleja el angulo horizontalmente
        }

        if (y.get() < 0 || y.get() > TerritoryView.HEIGHT) {
            theta = -theta; // refleja el angulo verticalmente
        }

    }

    /**
     * Obtiene la propiedad X para binding con JavaFX.
     * Permite que las vistas se actualicen automáticamente cuando cambia la
     * posición.
     *
     * @return Propiedad DoubleProperty del eje X
     */

    public DoubleProperty xProperty() {
        return x;
    }

    /**
     * Obtiene la propiedad Y para binding con JavaFX.
     * Permite que las vistas se actualicen automáticamente cuando cambia la
     * posición.
     *
     * @return Propiedad DoubleProperty del eje Y
     */
    public DoubleProperty yProperty() {
        return y;
    }

    /**
     * Obtiene el nombre del dueño del equipo.
     *
     * @return Nombre del dueño
     */

    public String getOwnerName() {
        return ownerName;
    }
}
