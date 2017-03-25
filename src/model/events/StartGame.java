package model.events;

import model.GameModel;

/**
 * Start Game event class.
 *
 * @author Connor D. Milligan
 */
public class StartGame extends Event {
    @Override
    public String startEvent() {
        return  "Welcome to the world of PreCivilization!\n" +
                "A computer simulation built to test the difficulties\n" +
                "and randomness inherent with living in a Pre-agricultural society.\n";
    }

    @Override
    public String endEvent(GameModel model, String choice) {
        return "In order to play you must allocate people to certain tasks\n" +
                "Be careful, you need to make sure that you have a variety of resources.\n";
    }
}

