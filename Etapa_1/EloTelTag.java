import java.io.PrintWriter;
import java.util.Scanner;

public class EloTelTag {
    public EloTelTag(String owner, String n, float _x, float _y) { // Método constructor
        ownerName = owner;
        name = n;
        x = _x;
        y = _y;
    }

    public String getName() { // Obtiene el nombre del tag
        return name;
    }

    public void move(float delta_x, float delta_y) { // Mueve el tag
        x += delta_x;
        y += delta_y;
    }

    public String getOwnerName() { // Obtiene el nombre del propietario del tag
        return ownerName;
    }

    public String getHeader() { // Obtiene la cabecera para el documento
        return ownerName + "." + name + ".x\t.y";
    }

    public String getState() { // Obtiene las coordenadas formateadas para el documento
        return (x + "\t" + y);
    }

    private final String name;
    private final String ownerName;
    private float x, y;
}
