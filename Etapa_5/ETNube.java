//import javax.xml.crypto.Data;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

public class ETNube {
    public ETNube() {
        cloudData = new ArrayList<Data>();
    }

    public void updateLocation(String owner, String equipment, float x, float y) {
        Point2D location;
        if ((location = getLocation(owner, equipment)) == null) {
            location = new Point2D.Float(x, y);
            Data data = new Data(owner, equipment, location);
            cloudData.add(data);
        }
        location.setLocation(x, y);
    }

    public Point2D getLocation(String owner, String equipment) {
        for (Data data : cloudData)
            if (data.ownerName.equals(owner) && data.equipmentName.equals(equipment))
                return data.location;
        return null;
    }

    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (Data data : cloudData)
            output.print(data.ownerName + "." + data.equipmentName + ".x . y\t");
        output.println();
    }

    public void printState(PrintStream output, int step) {
        output.print(step + "\t");
        for (Data data : cloudData)
            output.print(String.format(java.util.Locale.US, "%.2f\t%.2f\t",
                    data.location.getX(), data.location.getY()));
        output.println();
    }

    public void printUserEquipments(String owner) {
        System.out.println("\n------items------\n");
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner) && !data.equipmentName.equals("celular") && !data.equipmentName.equals("tablet")) {
                System.out.printf(java.util.Locale.US, "%s: %.2f, %.2f\n", data.equipmentName, data.location.getX(), data.location.getY());
            }
        }
        System.out.println("dispsitivos:");
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner) && (data.equipmentName.equals("celular") || data.equipmentName.equals("tablet"))) {
                System.out.printf(java.util.Locale.US, "%s: %.2f, %.2f\n", data.equipmentName, data.location.getX(), data.location.getY());
            }
        }
    }

    public Data getEquipment(String owner, String equipmentName) {
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner) && data.equipmentName.equals(equipmentName)) {
                return data;
            }
        }
        return null;
    }

    public void soundEquipment(String owner, String equipmentName) {
        Data equipo = getEquipment(owner, equipmentName);
        if (equipo != null) {
            System.out.println(equipmentName + " sonando");
        }
    }

    public double getDistance(String owner1, String equipment1, String owner2, String equipment2) {
        Point2D pos1 = getLocation(owner1, equipment1);
        Point2D pos2 = getLocation(owner2, equipment2);
        if (pos1 == null || pos2 == null) {
            return Double.MAX_VALUE;
        }
        return pos1.distance(pos2);
    }

    public double getAngle(String owner1, String equipment1, String owner2, String equipment2) {
        Point2D pos1 = getLocation(owner1, equipment1);
        Point2D pos2 = getLocation(owner2, equipment2);
        if (pos1 == null || pos2 == null) {
            return 0;
        }
        double dx = pos2.getX() - pos1.getX();
        double dy = pos2.getY() - pos1.getY();
        double angulo = Math.toDegrees(Math.atan2(dy, dx));
        if (angulo < 0) {
            angulo += 360;
        }
        return angulo;
    }

    public ArrayList<Data> getCloudData() {
        return cloudData;
    }

    private ArrayList<Data> cloudData;

    public static class Data {
        public Data(String owner, String equipment, Point2D loc) {
            ownerName = owner;
            equipmentName = equipment;
            location = loc;
        }
        public Point2D location;
        public String ownerName, equipmentName;
    }
}