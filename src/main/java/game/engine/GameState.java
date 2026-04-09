package game.engine;

/**
 * One step per call from {@link GameContext#startGame()}.
 *
 * <p>Implementations should follow: player input (if any), phase logic, then at most one
 * {@link GameContext#setState(GameState)} to the next phase.
 *
 * <p>Where to go next after each beat is defined by {@link game.scenario.ScenarioPlan}
 * (see {@link game.scenario.DefaultScenarioPlan}).
 */
public interface GameState {
    void handle();
}

