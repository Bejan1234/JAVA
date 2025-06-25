package subiectTestIancu;

import java.util.Objects;

public abstract class PublicTransportTicket implements Buyable {
    protected String departure;
    protected String destination;
    protected int distance;

    public PublicTransportTicket(String departure, String destination, int distance) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    public abstract float getDiscount();

    //"> Bucuresti Iasi 500"
    @Override
    public String toString() {
        return "> " + departure + " " + destination + " " +  distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicTransportTicket that = (PublicTransportTicket) o;
        return Objects.equals(departure, that.departure)&& Objects.equals(destination,that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination);
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}