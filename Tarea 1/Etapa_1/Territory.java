import java.io.PrintStream;
import java.util.ArrayList;

public class Territory {  // Piece of land where cellulars, tags, and tablets are located and moved.
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }
    public EloTelTag getTag(String ownerName, String equipmentName) {
      //....
    }

    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (EloTelTag tag : tags)
            output.print(tag.getState()+"\t");
        output.println();
    }

    public void printState(PrintStream output, int step) {
      //.....
    }
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

}
