package game.engine;

import game.characters.BasicFighter;
import game.characters.Fighter;

import java.util.Random;
import java.util.Scanner;

/**
 * GameContext class manages the state and flow of the game.
 */
public class GameContext {
    private GameState currentState;
    private Fighter bossAssassinator;
    private Random random;
    private Scanner scanner;
    private boolean isInteractive;

    /**
     * Constructs a new GameContext object.
     * This constructor initializes the game state to ExploringState,
     * creates a basic fighter named "Boss Assassinator",
     * and initializes the random number generator
     * and the scanner for user input.
     */
    public GameContext() {
        this.currentState = new ExploringState(this);
        this.bossAssassinator = new BasicFighter("Boss Assassinator");
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Set the current state of the game.
     * @param state The new state to set.
     */
    public void setState(GameState state) {
        this.currentState = state;
    }

    /**
     * Get the current state of the game.
     * @return The current game state.
     */
    public GameState getCurrentState() {
        return currentState;
    }


    /**
     * Start the game loop.
     */
    public void startGame() {
        while (!(currentState instanceof GameOverState)) {
            currentState.handle();
        }
        System.out.println("Game Over! Thanks for playing.");
    }

    /**
     * Get the main character (Boss Assassinator).
     * @return The main character.
     */
    public Fighter getBossAssassinator() {
        return bossAssassinator;
    }

    /**
     * Set the main character (Boss Assassinator).
     * @param bossAssassinator The main character to set.
     */
    public void setBossAssassinator(Fighter bossAssassinator) {
        this.bossAssassinator = bossAssassinator;
    }

    /**
     * Get the random number generator.
     * @return The random number generator.
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Get the scanner for user input.
     * @return The scanner for user input.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Set the game to interactive or automatic mode.
     * @param isInteractive True for interactive mode, false for automatic mode.
     */
    public void setInteractive(boolean isInteractive) {
        this.isInteractive = isInteractive;
    }

    /**
     * Check if the game is in interactive mode.
     * @return True if interactive mode, false if automatic mode.
     */
    public boolean isInteractive() {
        return isInteractive;
    }

}
