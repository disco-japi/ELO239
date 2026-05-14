package cl.utfsm.elo.eloteltag;

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

    public String getFindMy(String owner) {
        String h1 = "Bienes de " + owner + "\nÍtems:\n";
        String items = "";
        String h2 = "Dispositivos:\n";
        String devices = "";
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner)) {
                String buffer = (data.equipmentName + ": ("
                        + (int) getLocation(owner, data.equipmentName).getX() + " , "
                        + (int) getLocation(owner, data.equipmentName).getY() + ")\n");
                if (!data.equipmentName.equals("Celular") && !data.equipmentName.equals("Tablet")) {
                    items = items.concat(buffer);
                } else {
                    devices = devices.concat(buffer);
                }
            }
        }
        return h1 + items + h2 + devices;
    }

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
