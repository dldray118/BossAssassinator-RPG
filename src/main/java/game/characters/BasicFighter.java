package game.characters;

import game.items.Weapon;
import java.util.ArrayList;
import java.util.List;

public class BasicFighter implements Fighter {
    private int health = 100;
    private List<Weapon> weapons = new ArrayList<>();
    private List<String> skills = new ArrayList<>();
    private String name;

    public BasicFighter(String name) {
        this.name = name;
    }

    @Override
    public void attack(Enemy enemy) {
        int totalDamage = 10; // Basic attack damage
        if (!weapons.isEmpty()) {
            for (Weapon weapon : weapons) {
                System.out.println(name + " attacks with " + weapon.getName());
                totalDamage += weapon.getDamage();
            }
        } else {
            System.out.println(name + " attacks with basic skills!");
        }
        for (String skill : skills) {
            System.out.println("Using skill: " + skill);
            totalDamage += 5; // Each skill adds damage
        }
        int enemyHealth = enemy.getHealth() - totalDamage;
        enemy.setHealth(enemyHealth);
        System.out.println("Enemy takes " + totalDamage + " damage, health is now " + enemyHealth);
    }

    @Override
    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public void addSkill(String skill) {
        skills.add(skill);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage, health is now " + health);
    }

}
