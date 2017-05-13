package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.GameModel;

/**
 * Class to display the location information on the UI.
 *
 * @author Connor D. Milligan
 */
public class LocationDisplay {
    // The private state of the location display
    private GameModel model;
    private BorderPane locationDisplay;
    private Pane imagePane;

    /**
     * LocationDisplay constructor.
     * @param model GameModel for the simulation
     */
    public LocationDisplay(GameModel model) {
        this.model = model;
        this.locationDisplay = new BorderPane();
    }

    /**
     * Method to pull the location information into the label.
     * @return Label representing the updated location display
     */
    public BorderPane createLocationDisplay() {
        // Creates the location image
        Image image = new Image(getClass().getResource(model.getLocation().getCurrImage()).toExternalForm());
        ImageView logo = new ImageView(image);
        logo.setFitHeight(200.0);
        logo.setFitWidth(200.0);
        Pane pane = new Pane(logo);
        locationDisplay.setLeft(pane);

        // Creates the location rates
        Label locationLabel = new Label(model.getLocation().toString());
        locationLabel.setFont(new Font(18.0));
        locationLabel.setPadding(new Insets(0.0, 600.0, 0.0, 0.0));
        locationDisplay.setCenter(locationLabel);

        Label locationTypeLabel = new Label(model.getLocation().getCurrLocation() + " Location");
        locationTypeLabel.setPadding(new Insets(0.0, 0.0, 5.0, 0.0));
        locationDisplay.setTop(locationTypeLabel);

        Label sourceLabel = new Label("Sourced from: " + model.getLocation().getCurrSource());
        sourceLabel.setPadding(new Insets(5.0));
        locationDisplay.setBottom(sourceLabel);

        locationDisplay.setPadding(new Insets(0.0, 10.0, 0.0, 100.0));
        return locationDisplay;
    }
}
