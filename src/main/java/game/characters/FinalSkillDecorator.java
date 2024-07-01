package game.characters;

import game.items.Weapon;
import java.util.List;

/**
 * FinalSkillDecorator adds the "Ultimate Destruction" skill.
 */
public class FinalSkillDecorator extends FighterDecorator {
    public FinalSkillDecorator(Fighter fighter) {
        super(fighter);
        fighter.addSkill("Ultimate Destruction");
    }

    @Override
    public void attack(Enemy enemy) {
        System.out.println(decoratedFighter.getName() + " attacks with final skills!");
        decoratedFighter.attack(enemy);
        System.out.println("Using skill: Ultimate Destruction");
        int totalDamage = 10; // Base damage
        totalDamage += 20; // Final skill extra damage
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
        return decoratedFighter.getName() + " with Final Skill";
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
        return decoratedFighter.getSkills() + " Ultimate Destruction";
    }
}
