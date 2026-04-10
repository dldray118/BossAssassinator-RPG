package game.ui;

/**
 * Classpath locations for bundled GUI imagery. Replace files on disk to reskin without code changes.
 */
public final class GameUiAssets {

    /** Player portrait for combat UI. */
    public static final String HERO_PORTRAIT = "/game/ui/assets/boss-assassin-hero.png";

    /** City hub backdrop with location markers. */
    public static final String HUB_MAP = "/game/ui/assets/boss-assassin-hub-map.png";

    /** Shop storefront scene. */
    public static final String SHOP_SCENE = "/game/ui/assets/boss-assassin-shop-scene.png";

    /** Tier 0 enemy portrait. */
    public static final String ENEMY_GRUNT = "/game/ui/assets/boss-assassin-enemy-grunt.png";

    /** Tier 1 enemy portrait. */
    public static final String ENEMY_ELITE = "/game/ui/assets/boss-assassin-enemy-elite.png";

    /** Tier 2 enemy portrait. */
    public static final String ENEMY_BOSS = "/game/ui/assets/boss-assassin-enemy-boss.png";

    private GameUiAssets() {
    }

    /**
     * Portrait path for combat tier (0–2).
     *
     * @param combatEnemyTier {@link game.engine.CombatState#getCombatEnemyTier()}
     * @return resource path under {@code /game/ui/assets/}
     */
    public static String enemyPortraitForTier(int combatEnemyTier) {
        switch (combatEnemyTier) {
            case 0:
                return ENEMY_GRUNT;
            case 1:
                return ENEMY_ELITE;
            case 2:
                return ENEMY_BOSS;
            default:
                return ENEMY_GRUNT;
        }
    }
}
