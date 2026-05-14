/// Visualiza los datos en consola
public class Viewer {
    private ETNube nube;

    /// Inicializa un visualizador con los datos de la clase ETNube
    public Viewer(ETNube nube) {
        this.nube = nube;
    }

    /// Muestra la ubicación de los equipos asociados a una persona
    public void showlocation(String Nombredueno) {
        System.out.println("-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("Datos de " + Nombredueno + ":");
        System.out.println("-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        nube.printUserEquipments(Nombredueno);
    }
}
