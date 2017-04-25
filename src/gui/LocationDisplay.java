package gui;

import javafx.scene.control.Label;
import model.GameModel;


/**
 * Class to display the location information on the UI.
 *
 * @author Connor D. Milligan
 */
public class LocationDisplay {
    // The private state of the location display
    private GameModel model;
    private Label locationDisplay;

    /**
     * LocationDisplay constructor.
     * @param model GameModel for the simulation
     */
    public LocationDisplay(GameModel model) {
        this.model = model;
        this.locationDisplay = new Label();
    }

    /**
     * Method to pull the location information into the label.
     * @return Label representing the updated location display
     */
    public Label createLocationDisplay() {
        locationDisplay.setText(model.getLocation().toString());
        return locationDisplay;
    }
}
