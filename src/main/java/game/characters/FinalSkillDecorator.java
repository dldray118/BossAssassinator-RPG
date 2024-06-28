package game.characters;

import game.items.Weapon;
import java.util.List;

public class FinalSkillDecorator extends FighterDecorator {
    public FinalSkillDecorator(Fighter decoratedFighter) {
        super(decoratedFighter);
    }

    @Override
    public void attack(Enemy enemy) {
        System.out.println(decoratedFighter.getName() + " attacks with mad boss skills!");
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
        return decoratedFighter.getName();
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
}
