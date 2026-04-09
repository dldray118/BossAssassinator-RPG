package game.engine;

import java.util.Scanner;

/**
 * Main class to start the Boss Assassinator RPG Game.
 */
public class Main {

    /**
     * The main method to start the Boss Assassinator RPG Game.
     * It initializes the game context, displays a menu for the user to
     * choose between interactive and automatic modes,
     * and starts the game based on the user's choice.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        GameContext game = new GameContext();
        Scanner scanner = game.getScanner();

        System.out.println("Welcome to the Boss Assassinator RPG Game!");
        System.out.println("Choose an option:");
        System.out.println("1. Play interactively");
        System.out.println("2. Run automatically");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            game.setInteractive(true);
            game.setPlayerCommands(new ScannerPlayerCommandSource(game.getScanner()));
        } else {
            game.setInteractive(false);
            game.setPlayerCommands(new RandomPlayerCommandSource(game.getRandom()));
        }

        game.startGame();
    }
}
