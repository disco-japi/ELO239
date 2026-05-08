public class Tablet extends Equipo {
    private static final double TRACKING_RANGE = 50;

    public Tablet(String owner, double x, double y, double r, double theta, double dt, ETNube nube) {
        super(owner, x, y, r, theta, dt);
    }

    /// public void findMy() { visor.showlocation(this.ownerName); }

    /// Verifica si el celular está en el rango establecido
    public boolean isWithinRange(Cellular cell) {
        double dx = this.x.getValue() - cell.x.getValue();
        double dy = this.y.getValue() - cell.y.getValue();
        double distance = (float) Math.sqrt(dx * dx + dy * dy);///pitagoras
        double round = (float) Math.round(distance * 100) / 100;/// redondea a 2 decimales
        return round <= TRACKING_RANGE;
    }
}
