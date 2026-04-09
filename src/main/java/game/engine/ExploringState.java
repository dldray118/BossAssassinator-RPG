package game.engine;

/**
 * ExploringState class handles the exploration phase of the game.
 */
public class ExploringState implements GameState {
    private GameContext context;

    public ExploringState(GameContext context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("Exploring the city...");

        if (context.isInteractive()) {
            context.getPlayerCommands().nextExploringIntent();
        }

        if (context.getRandom().nextInt(10) < 6) { // Adjusted for more chances to encounter an
            // enemy
            context.getSession().setEnemyLevel(0);
            context.setState(new CombatState(context));
        } else {
            context.setState(new ShopState(context));
        }
    }
}

