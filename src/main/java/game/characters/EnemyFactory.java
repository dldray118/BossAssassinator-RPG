package game.characters;

/**
 * The EnemyFactory class is responsible for creating different types of enemies.
 * It provides a static method to create an enemy based on the specified type.
 */
public class EnemyFactory {

    /**
     * Creates an enemy for combat tier: 0 small, 1 medium, 2 boss.
     *
     * @param tier session / combat tier index
     * @return enemy for that tier
     * @throws IllegalArgumentException if tier is not 0, 1, or 2
     */
    public static Enemy createForTier(int tier) {
        switch (tier) {
            case 0:
                return new SmallEnemy();
            case 1:
                return new MediumEnemy();
            case 2:
                return new BossEnemy();
            default:
                throw new IllegalArgumentException("Unknown enemy tier: " + tier);
        }
    }

    /**
     * Creates an enemy based on the specified type.
     *
     * @param type the type of enemy to create. Valid types are "small", "medium", and "boss".
     * @return an instance of Enemy corresponding to the specified type.
     * @throws IllegalArgumentException if the specified type is unknown.
     */
    public static Enemy createEnemy(String type) {
        switch (type.toLowerCase()) {
            case "small":
                return createForTier(0);
            case "medium":
                return createForTier(1);
            case "boss":
                return createForTier(2);
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
