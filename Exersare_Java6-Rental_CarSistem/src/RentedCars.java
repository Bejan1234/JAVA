import java.util.ArrayList;
import java.util.List;

public class RentedCars {
    private List<String> cars = new ArrayList<String>();

    public void addCar(String car) {
        cars.add(car);
    }

    public int getSize(){
        return cars.size();
    }

    public void removeCar(String numarImatriculare){
        cars.remove(numarImatriculare);
    }

    public RentedCars() {

    }

    public List<String> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "RentedCars{" +
                "cars=" + cars +
                '}';
    }
}
