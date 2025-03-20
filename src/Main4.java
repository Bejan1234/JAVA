public class Main4 {
    public static void main(String[] args) {
        int digitCount = getDigitCount(123);
        System.out.println(digitCount);
        int reverse1 = reverse(123);
        System.out.println(reverse1);
        numberToWords(1000);
    }
    public static int reverse(int number) {
        int reverse = 0;
        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        return reverse;
    }
    public static int getDigitCount(int number){
        if(number < 0){
            return -1;
        }
        if(number == 0){
            return 1;
        }
        int count = 0;

        for (int i = 1; i <= number; i *= 10) {
            count++;
        }
        return count;
    }

    public static void numberToWords(int number){
        if(number < 0){
            System.out.println("Invalid value");
            return;
        }
        int reverse = reverse(number);
        int leadZeroes = getDigitCount(number) - getDigitCount(reverse) ;

        if (number == 0) {
            System.out.println("Zero");
            return;
        }
        while( reverse != 0){
            int lastDigit = reverse % 10;
            switch (lastDigit){
                case 0 -> System.out.println("Zero");
                case 1 -> System.out.println("One");
                case 2 -> System.out.println("Two");
                case 3 -> System.out.println("Three");
                case 4 -> System.out.println("Four");
                case 5 -> System.out.println("Five");
                case 6 -> System.out.println("Six");
                case 7 -> System.out.println("Seven");
                case 8 -> System.out.println("Eight");
                case 9 -> System.out.println("Nine");

            }
            reverse /= 10;
        }
        for (int i = 0; i < leadZeroes; i++) {
            System.out.println("Zero");
        }
    }
}
