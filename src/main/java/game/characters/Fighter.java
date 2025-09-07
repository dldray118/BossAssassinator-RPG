package game.characters;

import game.items.Weapon;
import java.util.List;

/**
 * Fighter interface for characters in the game.
 */
public interface Fighter {

    void attack(Enemy enemy);

    void addSkill(String skill);

    String getName();

    void setWeapons(List<Weapon> weapons);

    int getHealth();

    void setHealth(int health);

    void takeDamage(int damage);

    String getSkills();

    int getMaxHealth();
}
