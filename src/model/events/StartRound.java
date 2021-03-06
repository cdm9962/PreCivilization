package model.events;

import model.GameModel;

/**
 * Start Round event class.
 *
 * @author Connor D. Milligan
 */
public class StartRound extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the StartRound event
    public static final String EVENT_NAME = "Start Round";
    public static final String NEXT_BUTTON = "Next";

    /**
     * StartRound constructor
     * @param model GameModel for the event to modify
     */
    public StartRound(GameModel model){
        this.model = model;
        this.model.setLocation();
    }

    @Override
    public String startEvent() {
        return  "Now it is time to allocate how many of your people you want\n" +
                "to go after a particular resource.";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        return "In order to play you must allocate people to certain tasks\n" +
                "Be careful, you need to make sure that you have a variety of resources.\n";
    }
}
