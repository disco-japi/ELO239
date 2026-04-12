import java.util.ArrayList;

public class Territory {  // Piece of land where cellulars, tags, and tablets are located and moved.
    public void addCellular(Cellular cel) {
        cellulars.add(cel);
    }
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }
    public void addTablet(Tablet tablet) { tablets.add(tablet); }


    public void forEachTagTryToReportLocation() {
        for(EloTelTag tag : tags){ //busca un tag y reporta su ubicacion a la nube a través del celular mas cercano
            Cellular cell = findNearByCellular(tag);//busca el celular mas cercano al tag
            if(cell != null)
                cell.reportTagLocation(tag);
        }
    }

    public void forEachTabletTryToReportLocation() {
        for(Tablet tablet : tablets){ //busca un tag y reporta su ubicacion a la nube a través del celular mas cercano
            Cellular cell = findNearByCellular(tablet);//busca el celular mas cercano al tag
            if(cell != null)
                cell.reportTabletLocation(tablet);
        }
    }


    private Cellular findNearByCellular(EloTelTag tag) { //para tags
        for (Cellular cell : cellulars) if (tag.isWithinRange(cell)) return cell;
        return null;
    }

    private Cellular findNearByCellular(Tablet tablet) { //para tags
        for (Cellular cell : cellulars) if (tablet.isWithinRange(cell)) return cell;
        return null;
    }

    public Tablet getTablet(String ownerName){
        for (Tablet tablet : tablets)
            if(tablet.getOwnerName().equals(ownerName)) return tablet;
        return null;
    }
    public Cellular getCellular(String ownerName) {
        for (Cellular cell : cellulars)
            if (cell.getOwnerName().equals(ownerName)) return cell;
        return null;
    }
    public EloTelTag getTag(String ownerName, String equipmentName) {
        for(EloTelTag tag : tags)//busca un tag con el mismo nombre de propietario y equipo
            if(tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName))//si lo encuentra, lo devuelve
                return tag;
        System.err.println("No se encuentra el dispositivo: " + ownerName + "." + equipmentName);
        return null;

    }
    private ArrayList<Cellular> cellulars = new ArrayList<Cellular>();
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();
    private ArrayList<Tablet> tablets = new ArrayList<Tablet>();
}
