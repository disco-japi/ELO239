/// Clase de un equipo genérico
public class Equipo {
  /// Constructor de clase con parámetro de ubicación inicial y dueño
  public Equipo(String owner, float _x, float _y) {
    ownerName = owner;
    x = _x;
    y = _y;
  }

  /// Devuelve la ubicación actual del equipo en el eje X
  public float getX() {
    return x;
  }

  /// Devuelve la ubicación actual del equipo en el eje Y
  public float getY() {
    return y;
  }

  /// Desplaza el elemento en las coordenadas X e Y
  public void move(float delta_x, float delta_y) {
    x += delta_x;
    y += delta_y;
  }

  /// Devuelve el nombre del usuario
  public String getOwnerName() {
    return ownerName;
  }

  /// Devuelve una cabecera para uso con otros métodos
  public String getHeader() {
    return ownerName + ".x\t" + ownerName + ".y";
  }

  /// Devuelve la ubicación actual del elemento en forma de string formateado para
  /// su uso posterior
  public String getState() {
    return x + "\t" + y;
  }

  protected final String ownerName;
  protected float x, y;
}
