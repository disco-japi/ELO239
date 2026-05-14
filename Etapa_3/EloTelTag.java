public class EloTelTag extends Equipo {
    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dt) {
        super(owner, x, y, r, theta, dt);
        name = n;
    }

    public String getName() {
        return name;
    }

    public boolean isWithinRange(Cellular cell) {
        double dx = this.x.getValue() - cell.x.getValue();
        double dy = this.y.getValue() - cell.y.getValue();
        double distance = (double) Math.sqrt(dx * dx + dy * dy);///pitagoras
        double round = (double) Math.round(distance * 100) / 100;/// redondea a 2 decimales
        return round <= TRACKING_RANGE;
    }

    private final String name;
    private static final double TRACKING_RANGE = 50;
}
