public class Viewer {
    private ETNube nube;
    public Viewer(ETNube nube){
        this.nube = nube;
    }
    public void showlocation(String Nombredueno){
        System.out.println("Find my: ");
        System.out.println("Datos de " + Nombredueno + ":" );
        nube.printUserEquipments(Nombredueno);
    }
}
