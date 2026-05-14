import java.io.PrintStream;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Territory { // Piece of land where cellulars, tags, and tablets are located and moved.
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    public EloTelTag getTag(String ownerName, String equipmentName) {
        if (!tags.isEmpty()) {
            for (int i = 0; i < tags.size(); i++) {
                if (tags.get(i).getName().equals(equipmentName) && tags.get(i).getOwnerName().equals(ownerName)) {
                    return tags.get(i);
                }
            }
        }
        System.err.println("No se encuentra el dispositivo requerido");
        return null;
    }

    public void printHeader(FileWriter output) throws IOException {
        output.write("Step\t");
        for (EloTelTag tag : tags)
            output.write(tag.getHeader() + "\t");
        output.write("\n");
    }

    public void printState(FileWriter output, int step) throws IOException {
        output.write(step + "\t");
        for (EloTelTag tag : tags)
            output.write(tag.getState() + "\t");
        output.write("\n");
    }

    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

}
