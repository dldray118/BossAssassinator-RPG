package game.ui;

import game.engine.CombatStepIntent;
import game.engine.ExploringIntent;
import game.engine.PlayerCommandSource;
import game.engine.ShopIntent;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Blocks the game thread until the player chooses an action on the JavaFX thread.
 */
public final class JavaFxPlayerCommandSource implements PlayerCommandSource {

    private final FlowPane actionBar;
    private final BlockingQueue<ShopIntent> shopChoices = new LinkedBlockingQueue<>();
    private final BlockingQueue<CombatStepIntent> combatAck = new LinkedBlockingQueue<>();
    private final BlockingQueue<ExploringIntent> exploreAck = new LinkedBlockingQueue<>();

    public JavaFxPlayerCommandSource(FlowPane actionBar) {
        this.actionBar = actionBar;
    }

    @Override
    public ShopIntent nextShopIntent(int itemCount) {
        Platform.runLater(() -> {
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
        Platform.runLater(() -> {
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
            actionBar.getChildren().clear();
            Button go = styledButton("Continue exploring →");
            go.setOnAction(e -> exploreAck.offer(ExploringIntent.PROCEED));
            actionBar.getChildren().add(go);
        });
        return takeUninterruptibly(exploreAck);
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
