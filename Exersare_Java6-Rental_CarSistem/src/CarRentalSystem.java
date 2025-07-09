import java.util.HashMap;
import java.util.Scanner;

public class CarRentalSystem {

    private HashMap<String, String> rentedCars =
            new HashMap<String, String>(100, 0.5f);

    private HashMap<String, RentedCars> RentedCarsList =
            new HashMap<String, RentedCars>(100, 0.5f);

    private static String getPlateNo(Scanner sc) {
        System.out.println("Introduceti numarul de inmatriculare:");
        return sc.nextLine();
    }

    private static String getOwnerName(Scanner sc) {
        System.out.println("Introduceti numele proprietarului:");
        return sc.nextLine();
    }

    // search for a key in hashtable
    private boolean isCarRent(String plateNo) {
        return rentedCars.containsKey(plateNo);
    }

    // get the value associated to a key
    private String getCarRent(String plateNo) {
        return rentedCars.get(plateNo);
    }

    // add a new (key, value) pair
    private void rentCar(String plateNo, String ownerName) {
        rentedCars.put(plateNo, ownerName);
        if(!isOwnerExisting(ownerName)){
            RentedCars rentedCar = new RentedCars();
            rentedCar.addCar(plateNo);
            RentedCarsList.put(ownerName, rentedCar);
        }else{
            RentedCarsList.get(ownerName).addCar(plateNo);
        }

    }

    // remove an existing (key, value) pair
    private void returnCar(String plateNo) {
        rentedCars.remove(plateNo);
    }

    private int totalCarsRented() {
        return rentedCars.size();
    }

    private boolean isOwnerExisting(String ownerName) {
        return RentedCarsList.containsKey(ownerName);
    }
    private void getCarsNo(String ownerName) {
      if(RentedCarsList.containsKey(ownerName)) {
          System.out.println( RentedCarsList.get(ownerName).getSize());
      }
    }



    private void getCarsList(String ownerName) {
        System.out.println(RentedCarsList.get(ownerName).getCars());
    }




    private static void printCommandsList() {
        System.out.println("help                - Afiseaza aceasta lista de comenzi");
        System.out.println("add                 - Adauga o noua pereche (masina, sofer)");
        System.out.println("check               - Verifica daca o masina este deja luata");
        System.out.println("remove              - Sterge o masina existenta din hashtable");
        System.out.println("getOwner            - Afiseaza proprietarul curent al masinii");
        System.out.println("TotalCarsRented     - Afiseaza numarul total de masini inchiriate");
        System.out.println("getCarsListSize     - Aifiseaza numarul total de masini inchiriate de un anumit proprietar");
        System.out.println("getCarsList         - Aifiseaza lista de masiini de catre un proprietar");
        System.out.println("quit                - Inchide aplicatia");
    }


    public void run(Scanner sc) {
        boolean quit = false;
        while (!quit) {
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            String command = sc.nextLine();
            switch (command) {
                case "help":
                    printCommandsList();
                    break;
                case "add":
                    rentCar(getPlateNo(sc), getOwnerName(sc));
                    break;
                case "check":
                    System.out.println(isCarRent(getPlateNo(sc)));
                    break;
                case "remove":
                    returnCar(getPlateNo(sc));
                    break;
                case "getOwner":
                    System.out.println(getCarRent(getPlateNo(sc)));
                    break;
                case "TotalCarsRented":
                    System.out.println(totalCarsRented());
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    quit = true;
                    break;
                case "getCarsListSize":
                   getCarsNo(getOwnerName(sc));
                    break;
                case "getCarsList":
                    getCarsList(getOwnerName(sc));
                    break;
                default:
                    System.out.println("Unknown command. Choose from:");
                    printCommandsList();
            }
        }
    }

    public static void main(String[] args) {

        // create and run an instance (for test purpose)
        new CarRentalSystem().run(new Scanner(System.in));

    }


}