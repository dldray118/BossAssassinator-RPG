package game.engine;

import java.util.Scanner;

/**
 * Main class to start the Street Fighter RPG Game.
 */
public class Main {
    public static void main(String[] args) {
        GameContext game = new GameContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Boss Assassinator RPG Game!");
        System.out.println("Choose an option:");
        System.out.println("1. Play interactively");
        System.out.println("2. Run automatically");

        int choice = scanner.nextInt();

        if (choice == 1) {
            game.setInteractive(true);
        } else {
            game.setInteractive(false);
        }

        game.startGame();
    }
}
