package model.events;

import model.GameModel;

/**
 * Abstract event class.
 *
 * @author Connor D. Milligan
 */
public abstract class Event {
    private GameModel model;

    /**
     * Event constructor
     * @param model GameModel for the event to modify
     */
    public Event(GameModel model){
        this.model = model;
    }

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
