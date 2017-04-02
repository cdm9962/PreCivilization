package model;

import java.util.Random;

/**
 * The main game loop. Repeated through all rounds of the game.
 *
 * @author Connor D. Milligan
 */
public class GameLoop {
    private GameModel model;

    // Constant string values for the StartRound event
    public static final String EVENT_NAME = "Game Loop";
    public static final String NEXT_BUTTON = "Next";

    public GameLoop(GameModel model){
        this.model = model;
    }

    public void runLoop(){

    }
}
