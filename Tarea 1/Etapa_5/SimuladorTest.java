import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class SimuladorTest {
    SimuladorTest() {
        territory = new Territory();
        nube = new ETNube();
    }

    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        SimuladorTest stage = new SimuladorTest();
        stage.setupSimulator(confFile);
        PrintStream outFile = new PrintStream(new File("output.csv"));
        stage.runSimulation(movFile, outFile);
        outFile.close();
    }

    public void setupSimulator(Scanner in) {
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++)
            setupPersonEquipment(in);
    }

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

    private void setupCellular(Scanner in, String personName) {
        float x, y;
        x = in.nextFloat();
        y = in.nextFloat();
        Cellular cellular = new Cellular(personName, x, y, nube);
        territory.addCellular(cellular);
        nube.updateLocation(personName, "celular", x, y);
    }

    private void setupTablets(Scanner in, String personName) {
        float x, y;
        x = in.nextFloat();
        y = in.nextFloat();
        Tablet tablet = new Tablet(personName, x, y, nube);
        territory.addTablet(tablet);
        nube.updateLocation(personName, "tablet", x, y);
    }

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

    public void runSimulation(Scanner in, PrintStream output) {
        nube.printHeader(output);
        nube.printState(output, step);
        boolean printFindMyTitle = false;
        while (in.hasNext()) {
            String equipment = in.next();
            if (equipment.equalsIgnoreCase("Sound")) {
                procesarComandoSound(in);
                continue;
            }

            String[] parts = equipment.split("\\.");

            if (parts.length < 2) {

                System.out.println("Formato incorrecto: " + equipment);
                continue;
            }

            String personName = parts[0];
            String equipmentName = parts[1];

            if (in.hasNextFloat()) {
                float deltaX = in.nextFloat();
                float deltaY = in.nextFloat();
                step++;

                if (equipmentName.equals("celular")) {
                    Cellular c = territory.getCellular(personName);
                    if (c != null) {
                        c.move(deltaX, deltaY);
                        c.reportLocation();
                    } else if (equipmentName.equals(("tablet"))) {
                        Tablet tablet = territory.getTablet(personName);
                        if (tablet != null) {
                            tablet.move(deltaX, deltaY);
                        }
                    } else {
                        EloTelTag tag = territory.getTag(personName, equipmentName);
                        tag.move(deltaX, deltaY);
                    }
                    territory.forEachTagTryToReportLocation();
                    territory.forEachTabletTryToReportLocation();
                    nube.printState(output, step);
                } else {
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
    }

    private void procesarComandoSound(Scanner in) {

        if (!in.hasNext()) {
            System.out.println("Error: Comando Sound incompleto");
            return;
        }
        String dueno = in.next();

        if (!in.hasNext()) {
            System.out.println("Error: Comando Sound falta el nombre del equipo");
            return;
        }
        String nombreEquipo = in.next();

        Cellular celular = territory.getCellular(dueno);

        if (celular == null) {
            System.out.println("Error: No se encontró celular para el dueño '" + dueno + "'");
            return;
        }

        celular.sound(nombreEquipo);
    }

    private int step = 0;
    private Territory territory;
    private ETNube nube;
}
