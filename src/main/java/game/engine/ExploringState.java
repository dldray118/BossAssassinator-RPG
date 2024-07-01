package game.engine;

import java.util.Scanner;

/**
 * ExploringState class handles the exploration phase of the game.
 */
class ExploringState implements GameState {
    private GameContext context;

    public ExploringState(GameContext context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("Exploring the city...");

        // Pause for user input if interactive mode
        if (context.isInteractive()) {
            Scanner scanner = context.getScanner();
            System.out.println("Press enter to continue exploring...");
            scanner.nextLine();
        }

        if (context.getRandom().nextInt(10) < 8) { // Adjusted for more chances to encounter an enemy
            context.setState(new CombatState(context, 0)); // Start with enemyLevel 0
        } else {
            context.setState(new ShopState(context));
        }
    }
}
