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
        if ((location=getLocation(owner, equipment)) == null) {
            location=new Point2D.Float(x,y);
            Data data = new Data(owner, equipment, location);
            cloudData.add(data);
        }
        location.setLocation(x,y);
    }
    public Point2D getLocation(String owner, String equipment) {
        for(Data data : cloudData)
            if(data.ownerName.equals(owner) && data.equipmentName.equals(equipment))
                return data.location;
        return null;
    }
    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for(Data data : cloudData)
            output.print(data.ownerName + "." + data.equipmentName + ".x . y\t");
        output.println();
    }
    public void printState(PrintStream output, int step) {
        output.print(step + "\t");
        for(Data data : cloudData)
            output.print(String.format(java.util.Locale.US, "%.2f\t%.2f\t", 
                    data.location.getX(), data.location.getY()));
        output.println();
    }

    public void printUserEquipments(String owner) {
        System.out.println("\n------items------\n");
        for(Data data : cloudData) {
            if(data.ownerName.equals(owner) && !data.equipmentName.equals("celular") && !data.equipmentName.equals("tablet")) {
                System.out.printf(java.util.Locale.US, "%s: %.2f, %.2f\n",   data.equipmentName, data.location.getX(), data.location.getY());
            }
        }

        System.out.println("dispsitivos:");
        for(Data data : cloudData) {
            if(data.ownerName.equals(owner) && (data.equipmentName.equals("celular") || data.equipmentName.equals("tablet"))) {
                System.out.printf(java.util.Locale.US, "%s: %.2f, %.2f\n",   data.equipmentName, data.location.getX(), data.location.getY());
            }
        }
    }
    
    private ArrayList<Data> cloudData;
    
    private static class Data {  // internal class
        public Data(String owner, String equipment, Point2D loc) {
            ownerName = owner;
            equipmentName = equipment;
            location = loc;
        }
        public Point2D location;
        public String ownerName, equipmentName;
    }
}
