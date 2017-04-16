package model.events;

import model.GameModel;

/**
 * Tornado event class.
 *
 * @author Connor D. Milligan
 */
public class Tornado extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the Tornado event
    public static final String EVENT_NAME = "Tornado";
    public static final String NEXT_BUTTON = "Next";
    public static final String RUN_BUTTON = "Run";
    public static final String HIDE_BUTTON = "Hide";

    /**
     * Tornado constructor
     * @param model GameModel for the event to modify
     */
    public Tornado(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        return  "There is a tornado coming from the distance.\n" +
                "You can either try to run or hide.\n\n" +
                "Enter 'Run' or 'Hide'.\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        // User chooses first option
        if(choice.equals(RUN_BUTTON)){
            model.alterFood(-10);
            return "You out ran the tornado!\n\n" +
                    "10 lbs of food was left behind.\n";

        // User chooses second options
        } else if(choice.equals(HIDE_BUTTON)){
            model.alterGroupSize(-2);
            model.alterFood(-10);
            return  "The tornado destroyed the food storage!\n\n" +
                     "Two people died in the aftermath.\n" +
                     "10 lbs of food was destroyed.\n";

        // Error
        } else {
            return null;
        }
    }
}