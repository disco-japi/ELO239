public class Tablet extends Equipo{
    private ETNube nube;
    private Viewer visor;
    private static final float TRACKING_RANGE = 10.0f;
    public Tablet(String owner, float _x, float _y, ETNube nube){
        super(owner,_x,_y);
        this.nube = nube;
        this.visor = new Viewer(nube);
    }
    public void findMy(){
        visor.showlocation(this.ownerName);
    }
    public boolean isWithinRange(Cellular cell) {
        float dx = this.x - cell.getX();
        float dy = this.y - cell.getY();
        float distance = (float) Math.sqrt(dx *dx + dy * dy);
        float round = (float) Math.round(distance * 100) / 100;
        return round <= TRACKING_RANGE;
    }
}
