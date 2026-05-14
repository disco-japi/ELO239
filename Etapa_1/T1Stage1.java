import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class T1Stage1 {
    T1Stage1() {
        territory = new Territory();
    }

    public static void main(String args[]) throws IOException {
        // throws exception to avoid catching
        // exception in the program
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        FileWriter output = new FileWriter("output.csv");
        output.write("");
        output.close();
        output = new FileWriter("output.csv", true);
        T1Stage1 stage = new T1Stage1();
        stage.setupSimulator(confFile); // read configuration file and create objects
        stage.runSimulation(movFile, System.out, output); // execute file's instructions
        output.close();
    }

    public void setupSimulator(Scanner in) { // create objects from file
        EloTelTag tag;
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean isThereTablet = in.nextInt() == 1;
            in.nextFloat();
            in.nextFloat(); // skip cellular's location
            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                float x = in.nextFloat();
                float y = in.nextFloat();
                tag = new EloTelTag(personName, tagName, x, y);
                territory.addTag(tag);
            }
            if (isThereTablet) {
                in.nextFloat();
                in.nextFloat(); // skip tablet's location
            }
        }
    }

    public void runSimulation(Scanner in, PrintStream output, FileWriter fileOutput) throws IOException { // Ejecuta la
                                                                                                          // simulación
        territory.printHeader(fileOutput);
        territory.printState(fileOutput, step);
        while (in.hasNextLine()) {
            step++;
            String equipment = in.next();
            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String tagName = parts[1];
            EloTelTag tag = territory.getTag(personName, tagName);
            if (in.hasNextFloat()) {
                float deltaX = in.nextFloat();
                float deltaY = in.nextFloat();
                tag.move(deltaX, deltaY);
            } else {
                if (in.next().equals("FindMy")) {
                    // Implementar FindMy
                }
            }
            territory.printState(fileOutput, step);
        }
    }

    private Territory territory;
    private int step = 0;
}
