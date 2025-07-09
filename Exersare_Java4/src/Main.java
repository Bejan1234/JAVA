public class Main {
    public static void main(String[] args) {
        // Setăm numele jocului (valoare statică)
        PlayerStatus.setGameName("SuperBattle");

        // Inițializăm jucătorul 1
        PlayerStatus player1 = new PlayerStatus();
        player1.initPlayer("Ionut", 3, 20000);
        player1.setHealth(100);
        player1.setWeaponInHand("Sniper");
        player1.MovePlayerTo(0, 0);

        // Inițializăm jucătorul 2
        PlayerStatus player2 = new PlayerStatus();
        player2.initPlayer("Andrei", 3, 15000);
        player2.setHealth(90);
        player2.setWeaponInHand("Kalashnikov");
        player2.MovePlayerTo(0, 1200); // la distanță mare

        // Afișăm starea inițială a jucătorilor
        System.out.println("Starea inițială:");
        player1.displayPlayerStatus();
        player2.displayPlayerStatus();

        // Afișăm distanța dintre jucători
        System.out.println("Distanta intre jucatori: " + player1.CalculDistanta(player2));
        System.out.println();

        // Verificăm dacă ar trebui să atace
        System.out.println("Ar trebui Ionut să atace pe Andrei? " + player1.shouldAttackOpponent(player2));

        // Executăm atacul
        boolean rezultatAtac = player1.attackOpponent(player2);
        System.out.println("Atac reusit? " + rezultatAtac);
        System.out.println();

        // Afișăm starea finală a jucătorilor
        System.out.println("Starea dupa atac:");
        player1.displayPlayerStatus();
        player2.displayPlayerStatus();
    }

}
