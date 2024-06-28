package game.engine;

import game.characters.*;

import java.util.Scanner;

public class CombatState implements GameState {
    private GameContext context;
    private Fighter bossAssassinator;
    private Enemy currentEnemy;
    private int enemyLevel; // 0 for small, 1 for medium, 2 for final boss

    public CombatState(GameContext context, int enemyLevel) {
        this.context = context;
        this.bossAssassinator = context.getBossAssassinator(); // Get the existing Boss Assassinator
        this.enemyLevel = enemyLevel; // Initialize with the current enemy level
        spawnEnemy();
    }

    private void spawnEnemy() {
        switch (enemyLevel) {
            case 0:
                currentEnemy = new SmallEnemy();
                break;
            case 1:
                currentEnemy = new MediumEnemy();
                break;
            case 2:
                currentEnemy = new BossEnemy();
                break;
        }
    }

    @Override
    public void handle() {
        System.out.println("Entering combat...");

        // Combat logic
        boolean isEnemyDefeated = fight(currentEnemy);

        if (isEnemyDefeated) {
            levelUpSkills();
            enemyLevel++;
            if (enemyLevel > 2) {
                System.out.println("Final Boss defeated. Game Over!");
                context.setState(new GameOverState(context));
            } else {
                System.out.println("Enemy defeated. Moving to the next level.");
                System.out.println();
                spawnEnemy();
                context.setState(new CombatState(context, enemyLevel)); // Pass the updated enemy level
            }
        } else {
            System.out.println("Boss Assassinator defeated. Game Over!");
            context.setState(new GameOverState(context));
        }
    }

    private boolean fight(Enemy enemy) {
        while (bossAssassinator.getHealth() > 0 && enemy.getHealth() > 0) {
            bossAssassinator.attack(enemy);
            if (enemy.getHealth() > 0) {
                enemy.attack(bossAssassinator);
                // bossAssassinator.takeDamage(enemy.getDamage()); // Already handled inside enemy.attack()
            }
            // Pause for user input if interactive mode
            if (context.isInteractive()) {
                Scanner scanner = context.getScanner();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
            }
        }

        boolean isEnemyDefeated = enemy.getHealth() <= 0;

        if (!isEnemyDefeated && enemyLevel == 2) {
            System.out.println("Using leveled up skills!");
        }

        return isEnemyDefeated;
    }


    private void levelUpSkills() {
        switch (enemyLevel) {
            case 0:
                bossAssassinator = new Skill1Decorator(bossAssassinator);
                break;
            case 1:
                bossAssassinator = new Skill2Decorator(bossAssassinator);
                break;
            case 2:
                bossAssassinator = new FinalSkillDecorator(bossAssassinator);
                break;
        }
        context.setBossAssassinator(bossAssassinator); // Update context with the leveled-up bossAssassinator
        System.out.println("Boss Assassinator leveled up!");
    }

    public void setBossAssassinator(Fighter bossAssassinator) {
        this.bossAssassinator = bossAssassinator;
    }


    public Fighter getBossAssassinator() {
        return bossAssassinator;
    }
}
