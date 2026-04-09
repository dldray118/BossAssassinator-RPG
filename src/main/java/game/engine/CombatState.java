package game.engine;

import game.characters.*;
import game.domain.CombatProgress;
import game.domain.CombatRules;

/**
 * The CombatState class handles the combat phase of the game.
 * Players will engage in battles with different types of enemies.
 */
public class CombatState implements GameState {

    private GameContext context;
    private Fighter bossAssassinator;
    private Enemy currentEnemy;
    private int enemyLevel; // 0 for small, 1 for medium, 2 for final boss

    /**
     * Constructs a new CombatState with the specified game context.
     * Fighter and enemy tier are taken from {@code context.getSession()}.
     *
     * @param context the game context
     */
    public CombatState(GameContext context) {
        this.context = context;
        this.bossAssassinator = context.getSession().getFighter();
        this.enemyLevel = context.getSession().getEnemyLevel();
        spawnEnemy();
    }

    /**
     * Spawns a new enemy based on the current enemy level.
     */
    private void spawnEnemy() {
        currentEnemy = EnemyFactory.createForTier(enemyLevel);
    }

    /**
     * Announces combat, runs the fight, then sets the next state from the outcome only.
     */
    @Override
    public void handle() {
        System.out.println("Entering combat...");

        boolean isEnemyDefeated = fight(currentEnemy);

        if (isEnemyDefeated) {
            levelUpSkills();
            enemyLevel++;
            context.getSession().setEnemyLevel(enemyLevel);
            if (enemyLevel > 2) {
                System.out.println("Final Boss defeated!");
            } else {
                System.out.println("Enemy defeated. Moving to the next level.");
                System.out.println();
            }
            context.setState(
                    context.getSession().getScenarioPlan().nextAfterCombatVictory(context, enemyLevel));
        } else {
            System.out.println("Boss Assassinator defeated.");
            context.setState(
                    context.getSession().getScenarioPlan().nextAfterCombatDefeat(context));
        }
    }

    /**
     * Conducts the fight between the Boss Assassinator and the current enemy.
     *
     * @param enemy the current enemy to fight
     * @return {@code true} if the enemy is defeated, false otherwise
     */
    private boolean fight(Enemy enemy) {
        while (CombatRules.combatOngoing(bossAssassinator, enemy)) {
            CombatRules.exchangeRound(bossAssassinator, enemy);
            if (context.isInteractive() && CombatRules.combatOngoing(bossAssassinator, enemy)) {
                context.getPlayerCommands().nextCombatStepIntent();
            }
        }

        boolean isEnemyDefeated = CombatRules.enemyDefeated(enemy);

        if (!isEnemyDefeated && enemyLevel == 2) {
            System.out.println("Using leveled up skills!");
        }

        return isEnemyDefeated;
    }

    /**
     * Levels up the skills of the Boss Assassinator based on the current enemy level.
     * Updates the context with the leveled-up Boss Assassinator.
     */
    private void levelUpSkills() {
        bossAssassinator = CombatProgress.applySkillForDefeatedTier(bossAssassinator, enemyLevel);
        context.setBossAssassinator(bossAssassinator);
        System.out.println("Boss Assassinator leveled up!");

        Enemy finalBoss = EnemyFactory.createForTier(2);
        if (finalBoss.getHealth() > 0) {
            System.out.println("Boss Assassinator now has " + bossAssassinator.getSkills());
        }
    }

    /**
     * Gets the Boss Assassinator.
     *
     * @return the Boss Assassinator
     */
    public Fighter getBossAssassinator() {
        return bossAssassinator;
    }
}
