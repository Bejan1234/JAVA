public class MainChallenge {

    public static void main(String[] args) {

        int position = calculateHightScorePosition(1500);
        displayHighScorePosition("Tim", position);

        position = calculateHightScorePosition(1000);
        displayHighScorePosition("James", position);

        position = calculateHightScorePosition(500);
        displayHighScorePosition("Arnold", position);

        position = calculateHightScorePosition(100);
        displayHighScorePosition("Dwayne", position);

        position = calculateHightScorePosition(100);
        displayHighScorePosition("Bob", position);
    }

    public static void displayHighScorePosition(String playerName, int hightScorePosition) {
        System.out.println(playerName + " managed to get into position " + hightScorePosition+ " on the high score list");
    }

    public static int calculateHightScorePosition(int playersScore) {
        if (playersScore >= 1000) {
            return 1;
        } else if (playersScore >= 500 && playersScore < 1000) {
            return 2;
        } else if (playersScore >= 100 && playersScore < 500) {
            return 3;
        } else
            return 4;
    }
}
