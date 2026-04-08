import java.io.PrintStream;
import java.util.ArrayList;

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

    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (EloTelTag tag : tags)
            output.print(tag.getOwnerName() + "." + tag.getName() + ".x.y" + "\t");
        output.println();
    }

    public void printState(PrintStream output, int step) {
        output.println("");
    }

    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

}
