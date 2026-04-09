package game.scenario;

import game.engine.GameContext;
import game.engine.GameState;

/**
 * Decides the next {@link GameState} after major beats. Swap implementations to add linear
 * or scripted runs without editing {@link game.engine.ExploringState}, {@link game.engine.ShopState},
 * or {@link game.engine.CombatState}.
 */
public interface ScenarioPlan {

    GameState nextAfterExplore(GameContext context);

    GameState nextAfterShop(GameContext context);

    /**
     * @param newEnemyTier session tier after the player wins the current fight (already written to session)
     */
    GameState nextAfterCombatVictory(GameContext context, int newEnemyTier);

    GameState nextAfterCombatDefeat(GameContext context);
}
