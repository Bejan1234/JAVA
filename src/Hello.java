public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello , Tim");

        boolean isAlien = false;
        if(!isAlien) {
            System.out.println("It is not alien! ");
            System.out.println("It is alien!");

        }
        int topScore = 80;
        if(topScore <= 100) {
            System.out.println("You got the high score!");
        }

        int SecondTopScore = 60;
        if((topScore > SecondTopScore) && (topScore < 100)) {
            System.out.println("Greater then second top score and less than 100");

        }

        if((topScore > 50) && (topScore < 100)) {
            System.out.println("Either ot both of the condition are true");
        }

        int NewValue = 50;
        if(NewValue == 50) {
            System.out.println("This is an error");
        }

        boolean isCar = false;
        if(isCar){
            System.out.println("This is not supposed to happen");
        }

        String makeOfCar = "Volkswangen";
        boolean isDomestic = makeOfCar == "Volkswangen" ? false : true;

        if(isDomestic){
            System.out.println("It is a domestic car for our country !");
        }


        int ageOfClient = 20;
        String ageText = ageOfClient > 18 ? "Over Eighteen" : "Still a kid";
        System.out.println("Our client is" + ageText);

        String s = (isDomestic) ? "This car is domestic" : "This car is not domestic";
        System.out.println(s);

        //ex 1
        double Value1 = 20.00d;
        double Value2 = 80.00d;

        double result = (Value1 + Value2) * 100d;
        double reminder = result % 40.00d;
        System.out.println("Result total is : " + result);
        System.out.println("Reminder is : " + reminder);

        boolean flag = (reminder == 0) ? true : false;
        System.out.println("Remainder: "+ flag);

        if(!flag){
            System.out.println("Got some reminder");
        }

    }

}
