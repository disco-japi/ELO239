
///import javax.xml.crypto.Data;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

/// Clase de simulación del servicio en la nube ETNube, para la localización
/// de equipos
public class ETNube {
    /// Inicializa los datos de la nube como un arreglo complejo de datos tipo Data
    public ETNube() {
        cloudData = new ArrayList<Data>();
    }

    /// Actualiza la localización de un equipo en los datos de la nube
    public void updateLocation(String owner, String equipment, float x, float y) {
        Point2D location;
        if ((location = getLocation(owner, equipment)) == null) {
            location = new Point2D.Float(x, y);
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
    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (Data data : cloudData)
            output.print(data.ownerName + "." + data.equipmentName + ".x . y\t");
        output.println();
    }

    /// Imprime a un stream de datos la ubicación registrada en la nube de
    /// los dispositivos
    public void printState(PrintStream output, int step) {
        output.print(step + "\t");
        for (Data data : cloudData)
            output.print(String.format(java.util.Locale.US, "%.2f\t%.2f\t",
                    data.location.getX(), data.location.getY()));
        output.println();
    }

    /// Imrpime a consola los dispositivos del usuario
    public void printUserEquipments(String owner) {
        System.out.println("\n------items------\n");
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner) && !data.equipmentName.equals("celular")
                    && !data.equipmentName.equals("tablet")) {
                System.out.printf(java.util.Locale.US, "%s: %.2f, %.2f\n", data.equipmentName, data.location.getX(),
                        data.location.getY());
            }
        }

        System.out.println("dispsitivos:");
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner)
                    && (data.equipmentName.equals("celular") || data.equipmentName.equals("tablet"))) {
                System.out.printf(java.util.Locale.US, "%s: %.2f, %.2f\n", data.equipmentName, data.location.getX(),
                        data.location.getY());
            }
        }
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
