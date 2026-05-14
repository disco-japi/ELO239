/// Dispositivo localizable con GPS propio
public class Cellular extends Equipo {
    /// Inicializa una instancia de celular con su ubicación inicial
    public Cellular(String owner, float _x, float _y, ETNube nube) {
        super(owner, _x, _y);
        this.nube = nube;
        this.visor = new Viewer(nube);
    }

    /// Reporta la ubicación del tag
    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x, this.y);
    }

    /// Reporta la ubicacion de la tablet cercana al rango
    public void reportTabletLocation(Tablet tablet) {
        nube.updateLocation(tablet.getOwnerName(), "tablet", this.x, this.y);
    }

    /// Reporta la ubicación del celular a la nube
    public void reportLocation() {
        nube.updateLocation(this.ownerName, "celular", this.x, this.y);
    }

    /// Método para la funcionalidad de FindMy
    public void findMy() {
        visor.showlocation(this.ownerName);
    }

    private ETNube nube;
    private Viewer visor;
}
