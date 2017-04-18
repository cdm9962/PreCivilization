package model.events;

import model.GameModel;

/**
 * OpposingGroup event class.
 *
 * @author Connor D. Milligan
 */
public class OpposingGroup extends Event {
    // The game model for modifying based on the event results
    private GameModel model;

    // Constant string values for the OpposingGroup event
    public static final String EVENT_NAME = "Opposing Group";
    public static final String NEXT_BUTTON = "Next";
    public static final String STAY_BUTTON = "Stay";
    public static final String LEAVE_BUTTON = "Leave";

    /**
     * OpposingGroup constructor
     * @param model GameModel for the event to modify
     */
    public OpposingGroup(GameModel model){
        this.model = model;
    }

    @Override
    public String startEvent() {
        return  "There is another group settled nearby, and they don't seem to be happy with your group's presence.\n" +
                "You can either stay in the area or move along to a new location.\n\n" +
                "Enter 'Stay' or 'Leave'.\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        // User chooses first option
        if(choice.equals(STAY_BUTTON)) {
            model.alterTools(5);
            model.alterMorale(5);
            model.alterGroupSize(-4);
            return "The opposing group is offended by your group staying in the area.\n" +
                    "They attack your group, but you are able to fight them off.\n\n" +
                    "5 tools were found on the opposing group's bodies.\n" +
                    "Morale increased by 5 due to the victory.\n" +
                    "4 group members died in the conflict.\n";

            // User chooses second options
        } else if(choice.equals(LEAVE_BUTTON)) {
            model.alterFood(-10);
            model.alterWater(-10);
            model.alterMorale(-5);
            model.alterGroupSize(2);
            return  "The group gatherers up their materials and leaves the area.\n\n" +
                    "10 lbs of food is used along the journey.\n" +
                    "10 gallons of water is used along the journey.\n" +
                    "2 members from the opposing group decide to join you in your journey.\n";

            // Error
        } else {
            return null;
        }
    }
}
