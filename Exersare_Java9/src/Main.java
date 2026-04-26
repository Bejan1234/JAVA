import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        ArrayList<String> groceries = new ArrayList<>();

        while (flag) {
         printActions();
         switch (Integer.parseInt(sc.nextLine())) {
             case 1 -> addItems(groceries);
             case 2 -> removeItems(groceries);
             default -> flag = false;
         }
         groceries.sort(Comparator.naturalOrder());
            System.out.println(groceries);
        }
    }

    private static void addItems(ArrayList<String> groceries){
        System.out.println("Enter the item to add: ");
        String[] items = sc.nextLine().split(", ");
//        groceries.addAll(List.of(items));
        for (String i : items) {
            String trimed = i.trim();
           if(groceries.indexOf(trimed) < 0){
                groceries.add(trimed);
           }
        }

    }

    private static void removeItems(ArrayList<String> groceries){
        System.out.println(" Remove the item: ");
        String[] items = sc.nextLine().split(", ");
        for (String i : items) {
            String trimed = i.trim();
                groceries.remove(trimed);
        }

    }
   private static void printActions(){
  String textBlock = """
        Available actions:
        0 - to shutdown
        1 - to print contacts
        2 - to add a new contact
        3 - to update an existing contact
        4 - to remove an existing contact
        5 - query if an existing contact exists
        6 - to print a list of available actions
        """;
       System.out.println(textBlock+ "Choose your action: ");
   }


}

