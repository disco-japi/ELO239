package cl.utfsm.elo.eloteltag;

import java.util.ArrayList;

/**
 * Representa el territorio donde se encuentran y se mueven los equipos
 * (celulares, tags y tablets).
 *
 * <p>
 * Esta clase actúa como un contenedor que gestiona todos los dispositivos
 * en la simulación. Permite agregar equipos, moverlos todos simultáneamente
 * y buscar celulares cercanos a un tag o tablet.
 * </p>
 *
 * @author Álvaro, Sebastián, Alejandro y Pablo
 * @version 1.0
 * @see Cellular
 * @see EloTelTag
 * @see Tablet
 */
public class Territory { // Piece of land where cellulars, tags, and tablets are located and moved.
    private ArrayList<Cellular> cellulars = new ArrayList<>();
    private ArrayList<Tablet> tablets = new ArrayList<>();
    private ArrayList<EloTelTag> tags = new ArrayList<>();

    /**
     * Agrega un celular al territorio.
     *
     * @param cel Celular a agregar
     */
    public void addCellular(Cellular cel) {
        cellulars.add(cel);
    }

    // Añade un tag
    /**
     * Agrega un tag al territorio.
     *
     * @param tag Tag a agregar
     */
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    /// Añade una tablet
    /**
     * Agrega una tablet al territorio.
     *
     * @param tablet Tablet a agregar
     */
    public void addTablet(Tablet tablet) {
        tablets.add(tablet);
    }

    /**
     * Mueve todos los equipos del territorio durante un intervalo de tiempo.
     *
     * <p>
     * Antes de mover cada celular, se reporta su posición actual a la ETNube.
     * Luego se actualiza la posición de cada equipo según su velocidad y ángulo.
     * </p>
     *
     * @param timeStep Tiempo transcurrido desde el último movimiento (segundos)
     */
    public void moveAll(double timeStep) {
        for (Cellular eq : cellulars) {
            eq.reportLocation();
            eq.move(timeStep); // mueve cada equipo por el tiempo dado
        }
        for (Tablet tablet : tablets) {
            tablet.move(timeStep); // mueve cada equipo por el tiempo dado
        }
        for (EloTelTag tag : tags) {
            tag.move(timeStep); // mueve cada equipo por el tiempo dado
        }
    }

    /// Busca el celular mas cercano al tag/tablet
    /**
     * Busca el celular más cercano a un tag o tablet dentro del rango de detección.
     *
     * <p>
     * Recorre todos los celulares y verifica si el tag está dentro del rango
     * de detección (definido por {@link EloTelTag#isWithinRange(Cellular)}).
     * Retorna el primer celular que encuentre dentro del rango.
     * </p>
     *
     * @param tag Tag o tablet (hereda de EloTelTag) para el cual se busca un
     *            celular cercano
     * @return El primer celular dentro del rango de detección, o {@code null} si no
     *         hay ninguno
     */
    public Cellular findNearByCellular(EloTelTag tag) {
        for (Cellular cell : cellulars)
            if (tag.isWithinRange(cell))
                return cell;
        return null;
    }
}
