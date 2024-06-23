package game.engine;

/**
 * CombatState class handles the combat phase of the game.
 */
class CombatState implements GameState {
    private GameContext context;

    public CombatState(GameContext context) {
        this.context = context;
    }

    @Override
    public void handle() {
    }
}
