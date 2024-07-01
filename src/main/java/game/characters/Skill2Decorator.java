package game.characters;

import game.items.Weapon;
import java.util.List;

/**
 * Skill2Decorator adds the "Nunchuck Strike" skill.
 */
public class Skill2Decorator extends FighterDecorator {
    public Skill2Decorator(Fighter fighter) {
        super(fighter);
        fighter.addSkill("Nunchuck Strike");
    }

    @Override
    public void attack(Enemy enemy) {
        System.out.println(decoratedFighter.getName() + " attacks with level 2 skills!");
        decoratedFighter.attack(enemy);
        System.out.println("Using skill: Nunchuck Strike");
        int totalDamage = 10; // Base damage
        totalDamage += 10; // Level 2 skill extra damage
        enemy.setHealth(enemy.getHealth() - totalDamage);
        System.out.println("Enemy takes " + totalDamage + " damage, health is now " + enemy.getHealth());
    }

    @Override
    public void setWeapons(List<Weapon> weapons) {
        decoratedFighter.setWeapons(weapons);
    }

    @Override
    public void addSkill(String skill) {
        decoratedFighter.addSkill(skill);
    }

    @Override
    public String getName() {
        return decoratedFighter.getName() + " with Skill 2";
    }

    @Override
    public int getHealth() {
        return decoratedFighter.getHealth();
    }

    @Override
    public void setHealth(int health) {
        decoratedFighter.setHealth(health);
    }

    @Override
    public void takeDamage(int damage) {
        decoratedFighter.takeDamage(damage);
    }

    @Override
    public String getSkills() {
        return decoratedFighter.getSkills() + "Nunchuck Strike ";
    }
}
