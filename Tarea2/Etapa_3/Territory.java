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
            Cellular cell = findNearByCellularTab(tablet);/// busca el celular mas cercano al tag
            if (cell != null)
                cell.reportTabletLocation(tablet);
        }
        for (EloTelTag tag : tags) {
            tag.move(timeStep); // mueve cada equipo por el tiempo dado
            Cellular cell = findNearByCellular(tag);
            if (cell != null)
                cell.reportTagLocation(tag);
        }
    }

    /// Busca el celular mas cercano al tag
    private Cellular findNearByCellular(EloTelTag tag) {
        for (Cellular cell : cellulars)
            if (tag.isWithinRange(cell))
                return cell;
        return null;
    }

    /// Busca el celular mas cercano al tag
    private Cellular findNearByCellularTab(Tablet tablet) {
        for (Cellular cell : cellulars)
            if (tablet.isWithinRange(cell))
                return cell;
        return null;
    }

}
