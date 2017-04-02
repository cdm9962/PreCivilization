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

    /**
     * Tornado constructor
     * @param model GameModel for the event to modify
     */
    public Tornado(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        return  "There is a tornado coming,\n" +
                "You can either try to run or hide\n" +
                "Enter 'run' or 'hide'\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        if(choice.equals("run")){
            return "You out ran the tornado!";
        } else if(choice.equals("hide")){
            return  "The tornado destoryed the food storage!\n" +
                    "Two people died in the aftermath.\n";
        } else {
            return null;
        }
    }
}