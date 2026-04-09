package game.engine;

import game.scenario.ScenarioPlan;

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

    /** Delegates branch to {@link ScenarioPlan#nextAfterExplore(GameContext)}. */
    private void goCombatOrShopFromExplore() {
        context.setState(context.getSession().getScenarioPlan().nextAfterExplore(context));
    }
}

