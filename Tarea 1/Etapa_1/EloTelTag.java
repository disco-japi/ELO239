import java.io.PrintWriter;
import java.util.Scanner;

public class EloTelTag {
    public EloTelTag(String owner, String n, float _x, float _y) {
        ownerName = owner;
        name = n;
        x = _x;
        y = _y;
    }

    public String getName() {
        return name;
    }

    public void move(float delta_x, float delta_y) {
        x += delta_x;
        y += delta_y;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getHeader() {
        String s = "";
        return s;
    }

    public String getState() {
        return ("");
    }

    private final String name;
    private final String ownerName;
    private float x, y;
}
