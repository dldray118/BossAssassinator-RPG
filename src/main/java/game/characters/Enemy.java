package game.characters;

/**
 * Enemy interface for enemy characters in the game.
 */
public interface Enemy {

    void attack(Fighter fighter);

    int getHealth();

    void setHealth(int health);

    String getName();

    int getDamage();

    /**
     * Maximum hit points for this enemy type (starting health at spawn).
     *
     * @return max HP used for UI meters; not mutated by damage
     */
    int getMaxHealth();
}
