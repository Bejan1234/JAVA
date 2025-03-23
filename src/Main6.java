import java.util.Scanner;


    public class Main6 {

        public static void main(String[] args) {
            GetFiveNum();
        }

        public static void GetFiveNum() {
            Scanner sc = new Scanner(System.in);
            int sum = 0;


            for (int i = 0; i < 5; i++) {
                System.out.println("Enter number " + (i + 1) + ": ");
                String number = sc.nextLine();
                try {
                    int numberInt = Integer.parseInt(number);
                    numberInt = checknumb(numberInt);

                } catch (NumberFormatException e) {

                    System.out.println("Invalid input. Please enter a valid number.");
                    i--;
                }
            }

            System.out.println("The sum is: " + sum);
        }

        public static int checknumb(int num) {
            if (num > 1000 || num <= 0) {
                return -1;
            }
            return num;
        }
    }


