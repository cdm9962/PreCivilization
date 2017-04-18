package model.events;

import model.GameModel;

/**
 * RiverCrossing event class.
 *
 * @author Connor D. Milligan
 */
public class RiverCrossing extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the RiverCrossing event
    public static final String EVENT_NAME = "RiverCrossing";
    public static final String NEXT_BUTTON = "Next";
    public static final String BUILD_BUTTON = "Build";
    public static final String SWIM_BUTTON = "Swim";

    /**
     * RiverCrossing constructor
     * @param model GameModel for the event to modify
     */
    public RiverCrossing(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        return  "There is a moderately deep river ahead .\n" +
                "You can either spend time and resources building rafts or attempt to swim across it.\n\n" +
                "Enter 'Build' or 'Swim'.\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        // User chooses first option
        if(choice.equals(BUILD_BUTTON)){
            model.alterFood(-10);
            model.alterWater(10);
            model.alterTools(-10);
            return "The group successfully crossed the river on rafts.\n\n" +
                    "10 lbs of food was used in the interim.\n" +
                    "10 gallons of water was gathered from the river.\n" +
                    "10 tools were used in the construction of the rafts.\n";

            // User chooses second options
        } else if(choice.equals(SWIM_BUTTON)){
            model.alterGroupSize(-3);
            return  "The group makes it to the other side of the river and notices that a few people are missing.\n\n" +
                    "Three people died in the river crossing.\n";

            // Error
        } else {
            return null;
        }
    }
}
