public class Viewer {
    private ETNube nube;
    public Viewer(ETNube nube){
        this.nube = nube;
    }
    public void showlocation(String Nombredueno){
        System.out.println("-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("Datos de " + Nombredueno + ":" );
        System.out.println("-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        nube.printUserEquipments(Nombredueno);
    }
}
