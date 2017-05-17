package model.events;

import model.GameModel;

/**
 * Start Game event class.
 *
 * @author Connor D. Milligan
 */
public class EndGame extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the EndGame event
    public static final String EVENT_NAME = "End Game";
    public static final String NEXT_BUTTON = "New Game";

    /**
     * EndGame constructor
     * @param model GameModel for the event to modify
     */
    public EndGame(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        return  "Your group has perished!\n" +
                "You survived for: " + model.getTurns() + " Turns\n" +
                "Hit 'New Game' to try again.\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        return null;
    }
}
