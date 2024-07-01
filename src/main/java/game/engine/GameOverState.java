package game.engine;

/**
 * GameOverState class handles the game over phase.
 */
public class GameOverState implements GameState {
    private GameContext context;

    public GameOverState(GameContext context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("Game Over!");
    }
}
