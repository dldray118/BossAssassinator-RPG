package game.engine;

/**
 * ExploringState class handles the exploration phase of the game.
 */
public class ExploringState implements GameState {
    private GameContext context;

    public ExploringState(GameContext context) {
        this.context = context;
    }

    /**
     * Message, optional explore intent, then one random branch to combat or shop.
     */
    @Override
    public void handle() {
        System.out.println("Exploring the city...");

        if (context.isInteractive()) {
            context.getPlayerCommands().nextExploringIntent();
        }

        goCombatOrShopFromExplore();
    }

    /** Single random roll for explore: ~60% combat (tier 0), else shop. */
    private void goCombatOrShopFromExplore() {
        if (context.getRandom().nextInt(10) < 6) {
            context.getSession().setEnemyLevel(0);
            context.setState(new CombatState(context));
        } else {
            context.setState(new ShopState(context));
        }
    }
}

