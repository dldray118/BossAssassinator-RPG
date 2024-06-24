package game.engine;

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
        if (context.getRandom().nextInt(10) < 7) {// Randomly encounter an enemy or find a shop.
            context.setState(new CombatState(context));
        } else {
            context.setState(new ShopState(context));
        }
    }
}
