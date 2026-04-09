package game.engine;

/**
 * One step per call from {@link GameContext#startGame()}.
 *
 * <p>Implementations should follow: player input (if any), phase logic, then at most one
 * {@link GameContext#setState(GameState)} to the next phase.
 *
 * <p>Graph: {@link ExploringState} to {@link CombatState} or {@link ShopState};
 * {@link ShopState} to {@link ExploringState}; {@link CombatState} to the next
 * {@link CombatState} or {@link GameOverState} from fight outcome.
 */
public interface GameState {
    void handle();
}

