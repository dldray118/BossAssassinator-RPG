package game.characters;

/**
 * The EnemyFactory class is responsible for creating different types of enemies.
 * It provides a static method to create an enemy based on the specified type.
 */
public class EnemyFactory {

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
                return new SmallEnemy();
            case "medium":
                return new MediumEnemy();
            case "boss":
                return new BossEnemy();
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
