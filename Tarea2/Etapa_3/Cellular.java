public class Cellular extends Equipo {
    public Cellular(String owner, double x, double y, double r, double theta, double dt, ETNube nube) {
        super(owner, x, y, r, theta, dt);
        this.nube = nube;
    }

    /// Reporta la ubicación del tag
    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x.getValue(), this.y.getValue());
    }

    /// Reporta la ubicacion de la tablet cercana al rango
    public void reportTabletLocation(Tablet tablet) {
        nube.updateLocation(tablet.getOwnerName(), "tablet", this.x.getValue(), this.y.getValue());
    }

    /// Reporta la ubicación del celular a la nube
    public void reportLocation() {
        nube.updateLocation(this.ownerName, "celular", this.x.getValue(), this.y.getValue());
    }

    // Método para la funcionalidad de FindMy
    // public void findMy() {
    // visor.showlocation(this.ownerName);
    // }

    private ETNube nube;
}
