import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/// Clase principal del simulador
public class SimuladorTest {
    /// Constructor para facilitar multiples instancias de la simulación
    SimuladorTest() {
        territory = new Territory();
        nube = new ETNube();
    }

    /// Método principal del programa
    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        SimuladorTest stage = new SimuladorTest();
        stage.setupSimulator(confFile); /// read configuration file and create objects
        PrintStream outFile = new PrintStream(new File("output.csv"));
        stage.runSimulation(movFile, outFile);/// execute file's instructions
        outFile.close();
    }

    /// Configura la simulación en base al archivo de configuración
    public void setupSimulator(Scanner in) { /// create objects from file
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++)
            setupPersonEquipment(in);
    }

    /// Configura los distintos dispositivos de acuerdo el archivo de condiguración
    /// (vease `setupSimulator()`)
    private void setupPersonEquipment(Scanner in) {

        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet = in.nextInt() == 1;
        setupCellular(in, personName);

        for (int j = 0; j < tagNumber; j++)
            setupEloTags(in, personName);
        if (isThereTablet) {
            setupTablets(in, personName);
        }
    }

    /// Configura un celular dentro de la simulación
    private void setupCellular(Scanner in, String personName) {
        float x, y;
        x = in.nextFloat();
        y = in.nextFloat();
        Cellular cellular = new Cellular(personName, x, y, nube);
        territory.addCellular(cellular);
        nube.updateLocation(personName, "celular", x, y);
    }

    /// Configura una tablet dentro de la simulación
    private void setupTablets(Scanner in, String personName) {
        float x, y;
        x = in.nextFloat();
        y = in.nextFloat();
        Tablet tablet = new Tablet(personName, x, y, nube);
        territory.addTablet(tablet);
        nube.updateLocation(personName, "tablet", x, y);
    }

    /// Inicializa los Tags y sus asociaciones dentro de la simulación
    private void setupEloTags(Scanner in, String personName) {
        EloTelTag tag;
        float x, y;
        String tagName = in.next();
        x = in.nextFloat();
        y = in.nextFloat();
        tag = new EloTelTag(personName, tagName, x, y);
        territory.addTag(tag);
        nube.updateLocation(tag.getOwnerName(), tag.getName(), x, y);
    }

    /// Ejecuta la simulación
    public void runSimulation(Scanner in, PrintStream output) {
        nube.printHeader(output); /// in this stage, print cloud's state
        nube.printState(output, step);
        boolean printFindMyTitle = false;
        while (in.hasNext()) {
            String equipment = in.next(); /// read person'a name . equipment's name
            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String equipmentName = parts[1];

            if (in.hasNextFloat()) { /// si es un movimiento físico
                float deltaX = in.nextFloat();
                float deltaY = in.nextFloat();
                step++;

                if (equipmentName.equals("celular")) {
                    Cellular c = territory.getCellular(personName);/// busca el celular de esta persona
                    if (c != null) {
                        c.move(deltaX, deltaY);
                        c.reportLocation();/// reporta la nueva ubicacion del celular a la nube
                    }
                } else if (equipmentName.equals(("tablet"))) {
                    Tablet tablet = territory.getTablet(personName);
                    if (tablet != null) {
                        tablet.move(deltaX, deltaY);
                    }
                } else { /// in this stage, it must be a tag
                    EloTelTag tag = territory.getTag(personName, equipmentName); /// find this user's tag
                    if (tag != null)
                        tag.move(deltaX, deltaY);
                }
                territory.forEachTagTryToReportLocation();
                territory.forEachTabletTryToReportLocation();
                nube.printState(output, step);
            } else {/// si es un FindMy
                if (in.hasNext()) {
                    String command = in.next();
                    if (command.equals("FindMy")) {
                        if (!printFindMyTitle) {
                            System.out.println("\n-*-*-*-FIND MY-*-*-*-\n");

                            printFindMyTitle = true;
                        }

                        if (equipmentName.equals("celular")) {

                            Cellular c = territory.getCellular(personName);
                            if (c != null)
                                c.findMy();
                        } else if (equipmentName.equals("tablet")) {
                            Tablet t = territory.getTablet(personName);
                            if (t != null)
                                t.findMy();
                        } else {
                            Cellular c = territory.getCellular(personName);
                            Tablet t = territory.getTablet(personName);

                            if (c != null)
                                c.findMy();
                            else if (t != null)
                                t.findMy();
                        }
                    }
                }

            }
        }
    }

    private int step = 0;
    private Territory territory;
    private ETNube nube;
}
