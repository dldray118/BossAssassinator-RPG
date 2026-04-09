package game.scenario;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import game.engine.CombatState;
import game.engine.ExploringState;
import game.engine.GameContext;
import game.engine.GameOverState;
import game.engine.GameState;
import game.engine.RandomPlayerCommandSource;
import game.engine.ShopState;

import org.junit.jupiter.api.Test;

/** Custom {@link ScenarioPlan} can steer explore exit without changing state classes. */
public class ScenarioPlanTest {

    @Test
    void customPlanForcesCombatFromExplore() {
        GameContext ctx = new GameContext();
        ctx.setInteractive(false);
        ctx.setPlayerCommands(new RandomPlayerCommandSource(ctx.getRandom()));
        ctx.getSession().setScenarioPlan(new ScenarioPlan() {
            @Override
            public GameState nextAfterExplore(GameContext c) {
                c.getSession().setEnemyLevel(0);
                return new CombatState(c);
            }

            @Override
            public GameState nextAfterShop(GameContext c) {
                return new ExploringState(c);
            }

            @Override
            public GameState nextAfterCombatVictory(GameContext c, int newEnemyTier) {
                return new GameOverState(c);
            }

            @Override
            public GameState nextAfterCombatDefeat(GameContext c) {
                return new GameOverState(c);
            }
        });
        ctx.getCurrentState().handle();
        assertInstanceOf(CombatState.class, ctx.getCurrentState());
    }

    @Test
    void defaultPlanShopExitGoesExplore() {
        GameContext ctx = new GameContext();
        ctx.setInteractive(false);
        ctx.setState(new ShopState(ctx));
        ctx.getCurrentState().handle();
        assertInstanceOf(ExploringState.class, ctx.getCurrentState());
    }
}
