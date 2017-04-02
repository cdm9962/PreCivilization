package model.events;

import model.GameModel;

/**
 * Abstract event class.
 *
 * @author Connor D. Milligan
 */
public abstract class Event {
    /**
     * Method to start the event and return the textual representation of the event start.
     * @return String representing what is happening at the start of the event
     */
    public abstract String startEvent();

    /**
     * Method to end the event and return the textual representation of the event end.
     * @return String representing what is happening at the end of the event
     */
    public abstract String endEvent(GameModel model, String choice);

}
