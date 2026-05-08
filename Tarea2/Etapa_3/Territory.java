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
        for (Equipo eq : cellulars) {
            eq.move(timeStep); // mueve cada equipo por el tiempo dado
        }
        for (Equipo eq : tablets) {
            eq.move(timeStep); // mueve cada equipo por el tiempo dado
        }
        for (Equipo eq : tags) {
            eq.move(timeStep); // mueve cada equipo por el tiempo dado
        }
        forEachTabletTryToReportLocation();
        forEachTagTryToReportLocation();
    }

    public void forEachTagTryToReportLocation() {
        for (EloTelTag tag : tags) {
            Cellular cell = findNearByCellular(tag);
            if (cell != null)
                cell.reportTagLocation(tag);
        }
    }

    /// Reporta la ubicación en cada tablet
    public void forEachTabletTryToReportLocation() {
        for (Tablet tablet : tablets) { /// busca un tag y reporta su ubicacion a la nube a través del celular
                                        /// mas cercano
            Cellular cell = findNearByCellularTab(tablet);/// busca el celular mas cercano al tag
            if (cell != null)
                cell.reportTabletLocation(tablet);
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
