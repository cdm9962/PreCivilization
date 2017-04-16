package model.events;

import model.GameModel;

/**
 * NewTools event class.
 *
 * @author Connor D. Milligan
 */
public class NewTools extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the NewTools event
    public static final String EVENT_NAME = "New Tools";
    public static final String NEXT_BUTTON = "Next";
    public static final String TRAIN_BUTTON = "Train";
    public static final String PASS_BUTTON = "Pass";

    /**
     * NewTools constructor
     * @param model GameModel for the event to modify
     */
    public NewTools(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        return  "A group member has discovered a new way to make tools!\n" +
                "You can spend time learning this new method, or pass the opportunity.\n\n" +
                "Enter 'Train' or 'Pass'.\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        // User chooses first option
        if(choice.equals(TRAIN_BUTTON)){
            model.alterTools(10);
            model.alterMorale(5);
            model.alterFood(5);
            model.alterWater(5);
            return "The group has successfully learned how to use the new tools!\n\n" +
                    "The group has gained 10 new tools.\n" +
                    "The group's morale has increased by 5.\n" +
                    "The group has depleted 5 lbs of food while training.\n" +
                    "The group has depleted 5 gallons of water while training.\n";

        // User chooses second option
        } else if(choice.equals(PASS_BUTTON)){
            model.alterMorale(10);
            return  "The group is happy to continue moving forward.\n\n" +
                    "The group's morale has increased by 10.\n";

        // Error
        } else {
            return null;
        }
    }
}
