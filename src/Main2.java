public class Main2 {
    public static void main(String[] args) {
        for(int counter = 1; counter <= 10 ; counter++) {
            System.out.println(counter);
        }

        for(double rate = 2.0; rate <=5.0; rate++)
        {
            double InterestAmount = calculateInterest(10000, rate);
            System.out.println("10000 at "+ rate + " interest is "+InterestAmount);
        }

        for(double i = 7.5; i <= 10;i += 0.25){
            double InterestAmount = calculateInterest(100.00, i);
            System.out.println("100.00 "+ i + "Interest is "+InterestAmount);
        }
    }

    public static double calculateInterest(double amount, double interestRate){
        return (amount * (interestRate / 100));
    }
}

