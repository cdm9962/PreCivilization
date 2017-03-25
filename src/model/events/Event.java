package model.events;

import model.GameModel;

/**
 * Abstract event class.
 *
 * @author Connor D. Milligan
 */
public abstract class Event {
    public abstract String startEvent();

    public abstract String endEvent(GameModel model, String choice);

}
