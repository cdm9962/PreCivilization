package model.events;

import model.GameModel;

import java.util.Random;

/**
 * The main game loop. Repeated through all rounds of the game.
 *
 * @author Connor D. Milligan
 */
public class GameLoop extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the StartRound event
    public static final String EVENT_NAME = "Game Loop";
    public static final String NEXT_BUTTON = "Next";

    /**
     * Event constructor
     * @param model GameModel for the event to modify
     */
    public GameLoop(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        model.modifyValues();
        return "Running simulation...";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        return "Simulation results:\n" +
                model.printValues();
    }
}
