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
}
