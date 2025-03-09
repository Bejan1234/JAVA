public class Main {
    public static void main(String[] args) {
        int newscore = calculateScore("Tim", 500);
        System.out.println("New score: " + newscore);

        calculateScore(75);

        calculateScore();
    }

    public static int calculateScore(String playerName, int score) {
        System.out.println("Player " + playerName + " Scored " + score + " points ");
        return score * 1000;
    }

    public static int calculateScore(int score) {
        return calculateScore("Anonymus", score);
    }

    public static int calculateScore() {
        System.out.println(" Unnamed player scored ");
        return 0;
    }
}