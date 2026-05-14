public class EloTelTag extends Equipo {

    public EloTelTag(String owner, String n, float _x, float _y) {
        super(owner, _x, _y);
        name=n;
    }
    public String getName(){
        return name;
    }
    public String getHeader() {
        return ownerName + "." + name + ".x\t.y";
    }
    public void sonar(){
        System.out.println(this.getName()+"sonando");
    }
    public boolean isWithinRange(Cellular cell) {
        float dx = this.x - cell.getX();
        float dy = this.y - cell.getY();
        float distance = (float) Math.sqrt(dx *dx + dy * dy);
        float round = (float) Math.round(distance * 100) / 100;
        return round <= TRACKING_RANGE;
    }
    private final String name;
    private static final float TRACKING_RANGE = 10.0f;
    
}
