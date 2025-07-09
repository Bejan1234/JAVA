
@FunctionalInterface
public interface IProperty {
    //o interfata care are o singura metoda abstracta;
    void takeOff();
    default boolean hasSufficientFuel(float fuelAmount) {
        return fuelAmount > 500.0;
    }
    default boolean hasSufficientCrewCost(float crewCost) {
        return crewCost > 3000.0;
    }
    default boolean isMaintenanceComplete(String maintenanceStatus) {
        return maintenanceStatus != null && !maintenanceStatus.isEmpty();
    }
}