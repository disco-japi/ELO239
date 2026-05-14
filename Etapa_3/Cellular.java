public class Cellular extends Equipo {
    public Cellular(String owner, float _x, float _y, ETNube nube) {
        super(owner, _x, _y);
        this.nube = nube;
        this.visor = new Viewer(nube);
    }

    public void reportTagLocation(EloTelTag tag) {  // it reports cellular location
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x, this.y);
    }

    public void reportLocation(){
        nube.updateLocation(this.ownerName, "celular", this.x, this.y); // reporta la ubicacion del celular a la nube
    }

    public void findMy(){
        visor.showlocation(this.ownerName);
    }

    private ETNube nube;
    private Viewer visor;
}
