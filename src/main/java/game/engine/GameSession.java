package game.engine;

import game.characters.Fighter;
import game.scenario.DefaultScenarioPlan;
import game.scenario.ScenarioPlan;

import java.util.Objects;

/**
 * Player fighter and enemy-tier index for one run.
 */
public class GameSession {

    private Fighter fighter;
    /** 0 = small, 1 = medium, 2 = boss. */
    private int enemyLevel;
    private ScenarioPlan scenarioPlan;

    /**
     * @param fighter initial player fighter
     */
    public GameSession(Fighter fighter) {
        this.fighter = fighter;
        this.enemyLevel = 0;
        this.scenarioPlan = new DefaultScenarioPlan();
    }

    public ScenarioPlan getScenarioPlan() {
        return scenarioPlan;
    }

    /**
     * @param scenarioPlan plan for post-beat transitions
     */
    public void setScenarioPlan(ScenarioPlan scenarioPlan) {
        this.scenarioPlan = Objects.requireNonNull(scenarioPlan);
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public int getEnemyLevel() {
        return enemyLevel;
    }

    public void setEnemyLevel(int enemyLevel) {
        this.enemyLevel = enemyLevel;
    }
}
