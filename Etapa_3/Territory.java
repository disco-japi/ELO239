import java.util.ArrayList;

public class Territory { // Piece of land where cellulars, tags, and tablets are located and moved.
    private ArrayList<Cellular> cellulars = new ArrayList<>();
    private ArrayList<Tablet> tablets = new ArrayList<>();
    private ArrayList<EloTelTag> tags = new ArrayList<>();

    public void addCellular(Cellular cel) {
        cellulars.add(cel);
    }

    /// Añade un tag
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    /// Añade una tablet
    public void addTablet(Tablet tablet) {
        tablets.add(tablet);
    }

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
    public Cellular findNearByCellular(EloTelTag tag) {
        for (Cellular cell : cellulars)
            if (tag.isWithinRange(cell))
                return cell;
        return null;
    }
}
