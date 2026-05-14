import java.util.ArrayList;

/// Territorio virtual donde los celulares, tags y tablets se localizan y
/// se mueven.
public class Territory {
    /// Añade un celular
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

    /// Busca todos los tags y reporta su ubicación a la nube a través del celular
    /// mas cercano
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
            Cellular cell = findNearByCellular(tablet);/// busca el celular mas cercano al tag
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

    /// Busca el celular mas cercano a la tablet.
    private Cellular findNearByCellular(Tablet tablet) {
        for (Cellular cell : cellulars)
            if (tablet.isWithinRange(cell))
                return cell;
        return null;
    }

    /// Obtiene una instancia de tablet asociada a una persona.
    public Tablet getTablet(String ownerName) {
        for (Tablet tablet : tablets)
            if (tablet.getOwnerName().equals(ownerName))
                return tablet;
        return null;
    }

    /// Obtiene una instancia de celular asociada a una persona.
    public Cellular getCellular(String ownerName) {
        for (Cellular cell : cellulars)
            if (cell.getOwnerName().equals(ownerName))
                return cell;
        return null;
    }

    /// Obtiene una instancia de tag a partir del nombre del propietario y
    /// del equipo.
    public EloTelTag getTag(String ownerName, String equipmentName) {
        for (EloTelTag tag : tags)
            /// Si encuentra el tag, lo devuelve.
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName))
                return tag;
        System.err.println("No se encuentra el dispositivo: " + ownerName + "." + equipmentName);
        return null;

    }

    private ArrayList<Cellular> cellulars = new ArrayList<Cellular>();
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();
    private ArrayList<Tablet> tablets = new ArrayList<Tablet>();
}
