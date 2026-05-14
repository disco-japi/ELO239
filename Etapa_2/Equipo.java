public class Equipo {
    public Equipo(String owner, float _x, float _y) {
        ownerName = owner;
        x=_x;
        y=_y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void move(float delta_x, float delta_y) {
      x+=delta_x;
      y+=delta_y;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getHeader() {
      return ownerName + ".x\t" + ownerName + ".y";
    }
    public String getState() {
      return x + "\t" + y;
    }
    protected final String ownerName;
    protected float x,y;
}
