package model.events;

import model.GameModel;

import java.util.Random;

/**
 * The main game loop. Repeated through all rounds of the game.
 *
 * @author Connor D. Milligan
 */
public class GameLoop extends Event {
    // Constant string values for the StartRound event
    public static final String EVENT_NAME = "Game Loop";
    public static final String NEXT_BUTTON = "Next";

    /**
     * GameLoop constructor
     * @param model GameModel for the event to modify.
     */
    public GameLoop(GameModel model){
        super(model);
    }

    @Override
    public String startEvent() {
        return null;
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        return null;
    }
}
