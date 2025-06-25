package subiectTestIancu;

import java.util.Objects;
import java.util.Set;

public class TrainTicket extends PublicTransportTicket implements Cloneable {


    private TrainType trainType;//has-a
    private Set<TrainTicket> setTrainTickets; //lista care nu are duplicate

    public TrainTicket(String departure, String destination, int distance, TrainType trainType) {
        super(departure, destination, distance);
        this.trainType = trainType;
    }

    //5. Override the getDiscount method to return the total discount
    //The method will return 0.5 if the TrainType is REGIO,
    //0.25 if the TrainType is INTERREGIO and 0 if it is INTERCITY
    @Override
    public float getDiscount() {
        if (this.trainType.equals(TrainType.REGIO)) {
            return 0.5f;
        } else if (this.trainType.equals(TrainType.INTERREGIO)) {
            return 0.25f;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainTicket that = (TrainTicket) o;
        return super.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(trainType);
    }

    //deep-copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        TrainTicket clona = (TrainTicket) super.clone();
        clona.trainType = trainType;
        clona.departure = departure;
        clona.destination = destination;
        clona.distance = distance;
        return clona;
    }


    //Override the getPrice() method to return the price using the following formula:
    //price = the price returned by the method from PublicTransportTicket x distance x discount
    @Override
    public float getPrice() {
        return super.getPrice() * distance * getDiscount();
    }

    @Override
    public String toString() {
//                return "> " + departure + " " + destination + " " +  distance; VARIANTA 2
        return super.toString();
    }

    //    8. Use a Java Collection (defined as a static attribute), at your choice,
//that will allow you to save all the distinct issued tickets
// Add the following public static methods to the TrainTicket.java class:
// - void issueTicket(TrainTicket) -> this will add a new element in the collection
// - Collection<TrainTicket> getTickets() -> this will return the tickets issued so far
// Two tickets are considered identical if they have the same departure and the same destination
    public void issueTicket(TrainTicket trainTicket) {
        setTrainTickets.add(trainTicket);
    }

    public Set<TrainTicket> getTickets() {
        return setTrainTickets;
    }


}