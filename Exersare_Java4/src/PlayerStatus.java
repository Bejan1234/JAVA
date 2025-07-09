class PlayerStatus {

    private String nickname;
    private int score;
    private int lives;
    private int health;
    private String weaponInHand;
    private double positionX;
    private double positionY;
    private static String gameName;


    public void initPlayer(String nickname) {
        this.nickname = nickname;
    }

    public void initPlayer(String nickname, int lives) {
        this.nickname = nickname;
        this.lives = lives;
    }

    public void initPlayer(String nickname, int lives, int score) {
        this.nickname = nickname;
        this.lives = lives;
        this.score = score;
    }

    public boolean estePerfect(int n) {
        int sum = 0;
        for (int i = 0; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }

    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    public boolean isSumOFDigitsItsThree(int n) {
        if (n < 3) return false;
        int sum = 0;
        while (n > 0) {
            int ultimaCifra = n % 10;
            n /= 10;
            sum += ultimaCifra;
        }
        return sum == 3;
    }

    public boolean isEvenNUmberAndSumOFDigitsItsThree(int n) {
        return (n % 2 == 0) && isSumOFDigitsItsThree(n);
    }

    public void findArtifact(int artifactCode) {
        if (estePerfect(artifactCode)) {
            this.score += 5000;
            this.lives++;
            this.health = 100;
        } else if (isPrime(artifactCode)) {
            this.score += 1000;
            this.lives += 2;
            this.health += 25;
            if (this.health > 100) {
                this.health = 100;
            }
        } else if (isEvenNUmberAndSumOFDigitsItsThree(artifactCode)) {
            this.score -= 3000;
            this.health -= 25;
            if (this.health <= 0) {
                this.lives -= 1;
                this.health = 100;
            }
        } else {
            this.score += artifactCode;
        }
    }

    public boolean setWeaponInHand(String weaponInHand) {
        if (weaponInHand.equals("Knife") || weaponInHand.equals("Kalashnikov") || weaponInHand.equals("Sniper")) {
            if (weaponInHand.equals("Knife") && this.score >= 1000) {
                this.weaponInHand = "Knife";
                this.score -= 1000;
                return true;
            }
            if (weaponInHand.equals("Kalashnikov") && this.score >= 1000) {
                this.weaponInHand = "Kalashnikov";
                this.score -= 10000;
                return true;
            }
            if (weaponInHand.equals("Sniper") && this.score >= 20000) {
                this.weaponInHand = "Sniper";
                this.score -= 20000;
                return true;
            }
        }
        return false;
    }

    //Un camp devine proprietate a campurilor clasei cand avem getteri si setteri pe acel camp;
    public String getNickname() {
        return nickname;
    }

//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }

    public static String getGameName() {
        return gameName;
    }

    public static void setGameName(String gameName) {
        PlayerStatus.gameName = gameName;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public String getWeaponInHand() {
        return weaponInHand;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void MovePlayerTo(double pozX, double pozY) {
        this.positionX = pozX;
        this.positionY = pozY;
    }

//8.

    public double CalculDistanta(PlayerStatus Opponent) {
        double pozX = Math.pow(this.positionX - Opponent.positionX, 2);
        double pozY = Math.pow(this.positionY - Opponent.positionY, 2);
        return Math.sqrt(pozX + pozY);
    }

    //attack si should attack

    public boolean shouldAttackOpponent(PlayerStatus opponent) {
        // Dacă armele sunt la fel
        if (this.weaponInHand.equals(opponent.weaponInHand)) {
            double myPower = (3 * this.health + this.score / 1000.0) / 4;
            double opponentPower = (3 * opponent.health + opponent.score / 1000.0) / 4;
            return myPower > opponentPower;
        } else {
            // Dacă armele sunt diferite, comparăm puterea în funcție de distanță
            double distanta = this.CalculDistanta(opponent);
            String weapon1 = this.weaponInHand.toLowerCase();
            String weapon2 = opponent.weaponInHand.toLowerCase();

            // reguli de putere la distanță mare (> 1000): sniper > kalashnikov > knife
            // reguli de putere la distanță mică (<= 1000): kalashnikov > sniper > knife
            if (distanta > 1000) {
                return isStrongerAtLongDistance(weapon1, weapon2);
            } else {
                return isStrongerAtShortDistance(weapon1, weapon2);
            }
        }
    }

    private boolean isStrongerAtLongDistance(String w1, String w2) {
        return (w1.equals("sniper") && !w2.equals("sniper")) ||
                (w1.equals("kalashnikov") && w2.equals("knife"));
    }

    private boolean isStrongerAtShortDistance(String w1, String w2) {
        return (w1.equals("kalashnikov") && !w2.equals("kalashnikov")) ||
                (w1.equals("sniper") && w2.equals("knife"));
    }

    public boolean attackOpponent(PlayerStatus opponent) {
        if (shouldAttackOpponent(opponent)) {
            opponent.health -= 30;
            if (opponent.health <= 0) {
                opponent.lives--;
                if (opponent.lives > 0) {
                    opponent.health = 100;
                } else {
                    System.out.println("Game Over for " + opponent.getNickname());
                }
            }
            return true;
        }
        return false;
    }


    public void displayPlayerStatus() {
        System.out.format("+-------------------------+----------+----------+---------+--------------------------+%n");
        System.out.printf("|        Player name      | Lives    | Score    | Health  |          Weapon          |%n");
        System.out.format("+-------------------------+----------+----------+---------+--------------------------+%n");
        System.out.printf("| %-23s | %-8d | %-8d | %-7d | %-24s |%n",
                getNickname(), lives, score, health, getWeaponInHand());
        System.out.format("+-------------------------+----------+----------+---------+--------------------------+%n");
        System.out.println();
    }


}


