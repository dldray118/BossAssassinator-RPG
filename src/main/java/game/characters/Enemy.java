package game.characters;

/**
 * Enemy interface for enemy characters in the game.
 */
public interface Enemy {
    void attack();
    int getHealth();
    void setHealth(int health);
}
