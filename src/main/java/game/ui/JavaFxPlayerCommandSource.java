package game.ui;

import game.engine.CombatState;
import game.engine.CombatStepIntent;
import game.engine.ExploringIntent;
import game.engine.GameContext;
import game.engine.GameState;
import game.engine.PlayerCommandSource;
import game.engine.ShopIntent;
import game.engine.ShopState;
import game.items.Item;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Blocks the game thread until the player chooses an action on the JavaFX thread.
 * Updates {@link VisualGameplayPane} for explore / shop / combat to match {@link GameContext#getCurrentState()}.
 */
public final class JavaFxPlayerCommandSource implements PlayerCommandSource {

    private final FlowPane actionBar;
    private final VisualGameplayPane visual;
    private final GameContext context;
    private final BlockingQueue<ShopIntent> shopChoices = new LinkedBlockingQueue<>();
    private final BlockingQueue<CombatStepIntent> combatAck = new LinkedBlockingQueue<>();
    private final BlockingQueue<ExploringIntent> exploreAck = new LinkedBlockingQueue<>();

    /**
     * @param actionBar bottom button row
     * @param visual    hub / shop / combat imagery
     * @param context   live game context (same instance as the running loop)
     */
    public JavaFxPlayerCommandSource(FlowPane actionBar, VisualGameplayPane visual, GameContext context) {
        this.actionBar = actionBar;
        this.visual = visual;
        this.context = context;
    }

    @Override
    public ShopIntent nextShopIntent(int itemCount) {
        ShopState shop = castCurrentState(ShopState.class);
        List<Item> displayed = shop.getDisplayedItems();
        Platform.runLater(() -> {
            visual.showShop(displayed);
            actionBar.getChildren().clear();
            Button leave = styledButton("Leave shop");
            leave.setOnAction(e -> shopChoices.offer(ShopIntent.leave()));
            actionBar.getChildren().add(leave);
            for (int i = 1; i <= itemCount; i++) {
                int slot = i;
                Button buy = styledButton("Buy slot " + slot);
                buy.setOnAction(e -> shopChoices.offer(ShopIntent.selectItem(slot)));
                actionBar.getChildren().add(buy);
            }
        });
        return takeUninterruptibly(shopChoices);
    }

    @Override
    public CombatStepIntent nextCombatStepIntent() {
        CombatState combat = castCurrentState(CombatState.class);
        Platform.runLater(() -> {
            visual.showCombat(
                    combat.getBossAssassinator(),
                    combat.getCurrentEnemy(),
                    combat.getCombatEnemyTier());
            actionBar.getChildren().clear();
            Button go = styledButton("Continue combat →");
            go.setOnAction(e -> combatAck.offer(CombatStepIntent.PROCEED));
            actionBar.getChildren().add(go);
        });
        return takeUninterruptibly(combatAck);
    }

    @Override
    public ExploringIntent nextExploringIntent() {
        Platform.runLater(() -> {
            visual.showHub();
            actionBar.getChildren().clear();
            Button go = styledButton("Venture into the city →");
            go.setOnAction(e -> exploreAck.offer(ExploringIntent.PROCEED));
            actionBar.getChildren().add(go);
        });
        return takeUninterruptibly(exploreAck);
    }

    private <T> T castCurrentState(Class<T> type) {
        GameState state = context.getCurrentState();
        if (!type.isInstance(state)) {
            throw new IllegalStateException("Expected " + type.getSimpleName() + " but was "
                    + state.getClass().getSimpleName());
        }
        return type.cast(state);
    }

    private static Button styledButton(String text) {
        Button b = new Button(text);
        b.getStyleClass().add("game-button");
        return b;
    }

    private static <T> T takeUninterruptibly(BlockingQueue<T> q) {
        try {
            return q.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
