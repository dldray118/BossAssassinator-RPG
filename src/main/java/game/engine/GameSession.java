package game.engine;

import game.characters.Fighter;

/**
 * Player fighter and enemy-tier index for one run.
 */
public class GameSession {

    private Fighter fighter;
    /** 0 = small, 1 = medium, 2 = boss. */
    private int enemyLevel;

    /**
     * @param fighter initial player fighter
     */
    public GameSession(Fighter fighter) {
        this.fighter = fighter;
        this.enemyLevel = 0;
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
