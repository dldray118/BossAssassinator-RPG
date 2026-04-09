package game.scenario;

import game.engine.CombatState;
import game.engine.ExploringState;
import game.engine.GameContext;
import game.engine.GameOverState;
import game.engine.GameState;
import game.engine.ShopState;

/**
 * Default run: random explore branch, shop returns to explore, combat chains or game over.
 */
public final class DefaultScenarioPlan implements ScenarioPlan {

    @Override
    public GameState nextAfterExplore(GameContext context) {
        if (context.getRandom().nextInt(10) < 6) {
            context.getSession().setEnemyLevel(0);
            return new CombatState(context);
        }
        return new ShopState(context);
    }

    @Override
    public GameState nextAfterShop(GameContext context) {
        return new ExploringState(context);
    }

    @Override
    public GameState nextAfterCombatVictory(GameContext context, int newEnemyTier) {
        if (newEnemyTier > 2) {
            return new GameOverState(context);
        }
        return new CombatState(context);
    }

    @Override
    public GameState nextAfterCombatDefeat(GameContext context) {
        return new GameOverState(context);
    }
}
