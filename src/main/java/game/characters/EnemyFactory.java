package game.characters;

/**
 * EnemyFactory class for creating different types of enemies.
 */
public class EnemyFactory {
    public Enemy createEnemy(String type) {
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
