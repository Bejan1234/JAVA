import java.io.Serializable;//is-a

public class AtlanticAirplane extends Airplane implements Serializable {

    private boolean areEscala;
    private static long serialVersionUID = 1L;

    public AtlanticAirplane() {
    }

    public AtlanticAirplane(float kerosene, String maintenance, String insurance, float flightCrewCost, AirplaneType airplaneType, boolean areEscala) throws Exception {
        super(kerosene, maintenance, insurance, flightCrewCost, airplaneType);
        this.areEscala = areEscala;
    }

    public boolean isAreEscala() {
        return areEscala;
    }

    public void setAreEscala(boolean areEscala) {
        this.areEscala = areEscala;
    }

    @Override
    public String toString() {
        return "list_set.AtlanticAirplane{" +
                "areEscala=" + areEscala +
                "} " + super.toString();
    }
}