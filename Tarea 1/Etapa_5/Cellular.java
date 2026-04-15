public class Cellular extends Equipo {
    public Cellular(String owner, float _x, float _y, ETNube nube) {
        super(owner, _x, _y);
        this.nube = nube;
        this.visor = new Viewer(nube);
    }

    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x, this.y);
    }

    public void reportTabletLocation(Tablet tablet) {
        nube.updateLocation(tablet.getOwnerName(), "tablet", this.x, this.y);
    }

    public void reportLocation() {
        nube.updateLocation(this.ownerName, "celular", this.x, this.y);
    }

    public void findMy() {
        visor.showlocation(this.ownerName);
    }

    public void sound(String equipmentName) {
        ETNube.Data equipo = nube.getEquipment(this.ownerName, equipmentName);

        if (equipo == null) {
            System.out.println("Error: Equipo '" + equipmentName + "' no encontrado para el dueño " + this.ownerName);
            return;
        }

        double distancia = nube.getDistance(this.ownerName, "celular", this.ownerName, equipmentName);

        if (distancia < 10.0) {

            nube.soundEquipment(this.ownerName, equipmentName);
            double angulo = nube.getAngle(this.ownerName, "celular", this.ownerName, equipmentName);
            System.out.printf(java.util.Locale.US,
                    "%s.celular => distancia: %.2f m, ángulo: %.1f°\n",
                    this.ownerName, distancia, angulo);
        } else {
            System.out.printf(java.util.Locale.US,
                    "Equipo '%s' está demasiado lejos (%.2f m). No se puede activar sonido.\n",
                    equipmentName, distancia);
        }
    }

    private ETNube nube;
    private Viewer visor;
}
