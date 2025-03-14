import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {

        int currentYear = 2022;

        System.out.println(getInputFromConsole(currentYear));

        System.out.println(getInputFromScanner(currentYear));
    }

    public static String getInputFromConsole(int currentYear) {

        String name = System.console().readLine("Hi, What's your Name? ");
        System.out.println("Hi " + name + ", Thanks for taking the course!");

        String dateOfBirth = System.console().readLine("What year were you born? ");
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old";
    }

    public static String getInputFromScanner(int currentYear) {
        Scanner scaner = new Scanner(System.in);

        System.out.println("What is your name? ");
        String name = scaner.nextLine();

        System.out.println("What year were you born? ");
        String dateOfBirth = scaner.nextLine();
        int age = currentYear - Integer.parseInt(dateOfBirth);
        return "So you are " + age + " years old";

    }
}
