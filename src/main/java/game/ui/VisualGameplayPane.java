package game.ui;

import game.characters.Enemy;
import game.characters.Fighter;
import game.items.Item;
import game.items.Potion;
import game.items.Weapon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.List;

/**
 * Presents hub, shop, or combat imagery driven by {@link game.engine.GameContext} state.
 * HP values and labels are read-only snapshots for display.
 */
public final class VisualGameplayPane extends StackPane {

    private final ImageView backdrop = new ImageView();
    private final Rectangle combatShade = new Rectangle();
    private final HBox combatRow = new HBox(28);
    private final ProgressBar heroHp = new ProgressBar(1);
    private final ProgressBar enemyHp = new ProgressBar(1);
    private final Label heroName = new Label();
    private final Label enemyName = new Label();
    private final ImageView heroImg = new ImageView();
    private final ImageView enemyImg = new ImageView();
    private final VBox shopSummary = new VBox(6);
    private final HBox hubHints;

    /**
     * Builds layers: backdrop, optional combat shade and portraits, shop summary overlay, hub labels.
     */
    public VisualGameplayPane() {
        getStyleClass().add("visual-gameplay-pane");
        backdrop.getStyleClass().add("visual-backdrop");
        backdrop.setSmooth(true);

        combatShade.setFill(Color.color(0.05, 0.05, 0.1, 0.94));
        combatShade.setVisible(false);
        combatShade.widthProperty().bind(widthProperty());
        combatShade.heightProperty().bind(heightProperty());

        configurePortrait(heroImg);
        configurePortrait(enemyImg);
        heroHp.setMaxWidth(Double.MAX_VALUE);
        enemyHp.setMaxWidth(Double.MAX_VALUE);
        heroHp.getStyleClass().add("combat-hp-bar");
        enemyHp.getStyleClass().add("combat-hp-bar");

        VBox heroCol = new VBox(8, heroImg, heroName, heroHp);
        VBox enemyCol = new VBox(8, enemyImg, enemyName, enemyHp);
        heroCol.setAlignment(Pos.TOP_CENTER);
        enemyCol.setAlignment(Pos.TOP_CENTER);
        heroCol.getStyleClass().add("combat-card");
        enemyCol.getStyleClass().add("combat-card");

        combatRow.getChildren().addAll(heroCol, enemyCol);
        combatRow.setAlignment(Pos.CENTER);
        combatRow.setPadding(new Insets(16));
        combatRow.setVisible(false);

        shopSummary.setAlignment(Pos.TOP_LEFT);
        shopSummary.setPadding(new Insets(12, 20, 12, 20));
        shopSummary.setMaxWidth(Region.USE_PREF_SIZE);
        StackPane.setAlignment(shopSummary, Pos.BOTTOM_LEFT);
        shopSummary.setVisible(false);
        shopSummary.getStyleClass().add("shop-summary");

        Label h1 = hintLabel("Supply row");
        Label h2 = hintLabel("Arena");
        Label h3 = hintLabel("Boss tower");
        hubHints = new HBox(20, h1, h2, h3);
        hubHints.setPadding(new Insets(14, 0, 0, 16));
        StackPane.setAlignment(hubHints, Pos.TOP_LEFT);
        hubHints.setVisible(false);

        getChildren().addAll(backdrop, combatShade, combatRow, shopSummary, hubHints);

        widthProperty().addListener((o, a, w) -> {
            if (w.doubleValue() > 0) {
                backdrop.setFitWidth(w.doubleValue());
            }
        });
        heightProperty().addListener((o, a, h) -> {
            if (h.doubleValue() > 0) {
                backdrop.setFitHeight(h.doubleValue());
            }
        });
        backdrop.setPreserveRatio(false);
    }

    /**
     * Hub exploration: city map and location hints; combat and shop overlays hidden.
     */
    public void showHub() {
        combatShade.setVisible(false);
        combatRow.setVisible(false);
        shopSummary.setVisible(false);
        hubHints.setVisible(true);
        backdrop.setVisible(true);
        backdrop.setImage(loadImage(GameUiAssets.HUB_MAP));
    }

    /**
     * Shop visit: storefront art and a short list of offered items (names only for the player).
     *
     * @param items same order as engine shop slots
     */
    public void showShop(List<Item> items) {
        combatShade.setVisible(false);
        combatRow.setVisible(false);
        hubHints.setVisible(false);
        shopSummary.getChildren().clear();
        for (int i = 0; i < items.size(); i++) {
            Label line = new Label((i + 1) + ". " + formatItemLine(items.get(i)));
            line.getStyleClass().add("shop-line");
            shopSummary.getChildren().add(line);
        }
        shopSummary.setVisible(true);
        backdrop.setVisible(true);
        backdrop.setImage(loadImage(GameUiAssets.SHOP_SCENE));
    }

    /**
     * Combat step: shaded stage with fighter and enemy portraits and HP meters.
     *
     * @param fighter player snapshot
     * @param enemy     active enemy snapshot
     * @param combatEnemyTier tier for portrait art (0–2)
     */
    public void showCombat(Fighter fighter, Enemy enemy, int combatEnemyTier) {
        hubHints.setVisible(false);
        shopSummary.setVisible(false);
        backdrop.setVisible(false);
        combatShade.setVisible(true);
        combatRow.setVisible(true);

        heroName.setText(fighter.getName());
        enemyName.setText(enemy.getName());
        heroImg.setImage(loadImage(GameUiAssets.HERO_PORTRAIT));
        enemyImg.setImage(loadImage(GameUiAssets.enemyPortraitForTier(combatEnemyTier)));

        double hMax = Math.max(1, fighter.getMaxHealth());
        double eMax = Math.max(1, enemy.getMaxHealth());
        heroHp.setProgress(clamp01(fighter.getHealth() / hMax));
        enemyHp.setProgress(clamp01(enemy.getHealth() / eMax));
    }

    private static void configurePortrait(ImageView iv) {
        iv.setFitWidth(160);
        iv.setFitHeight(160);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.getStyleClass().add("combat-portrait");
    }

    private static Label hintLabel(String text) {
        Label l = new Label(text);
        l.getStyleClass().add("hub-hint");
        return l;
    }

    private static Image loadImage(String classpath) {
        URL url = VisualGameplayPane.class.getResource(classpath);
        if (url == null) {
            return null;
        }
        return new Image(url.toExternalForm(), true);
    }

    private static double clamp01(double v) {
        if (v < 0) {
            return 0;
        }
        if (v > 1) {
            return 1;
        }
        return v;
    }

    /**
     * Single-line catalog text for the shop summary (no pricing in engine).
     */
    static String formatItemLine(Item item) {
        if (item instanceof Weapon) {
            Weapon w = (Weapon) item;
            return w.getName() + " · +" + w.getDamage() + " dmg";
        }
        if (item instanceof Potion) {
            return "Potion · restore to full HP";
        }
        String n = item.getName();
        return n != null ? n : "Item";
    }
}
