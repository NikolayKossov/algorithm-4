import java.util.Random;
import java.util.Scanner;

public class RouletteGame {
    private static final double WIN_PROBABILITY = 9.0 / 19.0;
    private int initialFunds;
    private int targetWin;
    private int betAmount;
    private int experiments;

    public RouletteGame(int initialFunds, int targetWin, int betAmount, int experiments) {
        this.initialFunds = initialFunds;
        this.targetWin = targetWin;
        this.betAmount = betAmount;
        this.experiments = experiments;
    }

    public void runExperiments() {
        int wins = 0;
        for (int i = 0; i < experiments; i++) {
            if (playGame()) {
                wins++;
            }
        }
        double probability = (double) wins / experiments;
        String formattedProbability = String.format("%.8f", probability * 100);
        System.out.println("Probability of winning " + targetWin + " dollars starting with " + initialFunds + " dollars: " + formattedProbability + "%" + "The wins:" + wins);
    }

    private boolean playGame() {
        int money = initialFunds;
        Random random = new Random();

        while (money > 0 && money < initialFunds + targetWin) {
            if (random.nextDouble() < WIN_PROBABILITY) {
                money += betAmount;
            } else {
                money -= betAmount;
            }
        }

        return money >= initialFunds + targetWin;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter initial funds:");
        int initialFunds = scanner.nextInt();

        System.out.println("Enter target win amount:");
        int targetWin = scanner.nextInt();

        System.out.println("Enter bet amount:");
        int betAmount = scanner.nextInt();

        System.out.println("Enter number of experiments:");
        int experiments = scanner.nextInt();

        RouletteGame game = new RouletteGame(initialFunds, targetWin, betAmount, experiments);
        game.runExperiments();
    }
}
