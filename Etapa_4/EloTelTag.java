/// Clase del tag, que hereda de la clase base Equipo
public class EloTelTag extends Equipo {
    /// Constructor cuyos parámetros iniciales son
    public EloTelTag(String owner, String n, float _x, float _y) {
        super(owner, _x, _y);
        name = n;
    }

    /// Retorna el nombre del tag
    public String getName() {
        return name;
    }

    /// Retorna la cabezera del tag
    public String getHeader() {
        return ownerName + "." + name + ".x\t.y";
    }

    /// Verifica si está dentro del rango de alcance de un celular
    public boolean isWithinRange(Cellular cell) {
        float dx = this.x - cell.getX();
        float dy = this.y - cell.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);///pitagoras
        float round = (float) Math.round(distance * 100) / 100;/// redondea a 2 decimales
        return round <= TRACKING_RANGE;
    }

    private final String name;
    private static final float TRACKING_RANGE = 10.0f;

}
