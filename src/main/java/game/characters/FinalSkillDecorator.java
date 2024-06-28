package game.characters;

import game.items.Weapon;
import java.util.List;

public class FinalSkillDecorator extends FighterDecorator {
    public FinalSkillDecorator(Fighter decoratedFighter) {
        super(decoratedFighter);
    }

    @Override
    public void attack(Enemy enemy) {
        decoratedFighter.attack(enemy);
        System.out.println("Using all weapons from the shop!");
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
