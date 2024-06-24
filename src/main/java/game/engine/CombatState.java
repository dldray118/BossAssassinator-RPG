package game.engine;

import game.characters.Enemy;
import game.characters.EnemyFactory;

/**
 * CombatState class handles the combat phase of the game.
 */
class CombatState implements GameState {
    private GameContext context;

    public CombatState(GameContext context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("Entering combat...");
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy enemy = enemyFactory.createEnemy("medium");
        System.out.println("Combat finished.");                             // Simulate combat here.
        context.setState(new ExploringState(context));
    }
}
