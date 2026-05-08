
///import javax.xml.crypto.Data;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/// Clase de simulación del servicio en la nube ETNube, para la localización
/// de equipos
public class ETNube {
    /// Inicializa los datos de la nube como un arreglo complejo de datos tipo Data
    public ETNube() {
        cloudData = new ArrayList<Data>();
    }

    /// Actualiza la localización de un equipo en los datos de la nube
    public void updateLocation(String owner, String equipment, double x, double y) {
        Point2D location;
        if ((location = getLocation(owner, equipment)) == null) {
            location = new Point2D.Double(x, y);
            Data data = new Data(owner, equipment, location);
            cloudData.add(data);
        }
        location.setLocation(x, y);
    }

    /// Obtiene la localización de un equipamiento de acuerdo a los datos en la nube
    public Point2D getLocation(String owner, String equipment) {
        for (Data data : cloudData)
            if (data.ownerName.equals(owner) && data.equipmentName.equals(equipment))
                return data.location;
        return null;
    }

    /// Imprime a un stream de datos la cabecera para un conjunto de datos en el
    /// formato establecido

    private ArrayList<Data> cloudData;

    /// Define la clase estática interna con la cual se almacenan los datos
    private static class Data {
        public Data(String owner, String equipment, Point2D loc) {
            ownerName = owner;
            equipmentName = equipment;
            location = loc;
        }

        public Point2D location;
        public String ownerName, equipmentName;
    }
}
