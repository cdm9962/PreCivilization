package gui;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.Allocations;
import model.events.*;
import model.GameModel;

import java.util.Observable;
import java.util.Observer;

/**
 * The graphical user interface for the game. Displays the game data.
 *
 * @author Connor D. Milligan
 */
public class GameInterface extends Application implements Observer {
    // the model of the game, holds the private state data
    private GameModel model;

    // The various UI elements that need to be updated
    private Label topLabel;
    private BorderPane playScreen;
    private ResourceBars resourceBars;
    private GridPane userButtons;

    private Event currentEvent;

    // Constant/Default values for the game interface
    public static final String TITLE = "PreCivilization";

    /**
     * Constructor for the game interface.
     */
    public GameInterface(){
        this.model = new GameModel();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(makeBorder()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void update(Observable o, Object arg) {
        // Updates the resource bars on the UI to reflect the previous event
        resourceBars.updateResourceBars();

        // Launches the first game section
        if(currentEvent instanceof StartGame) {
            topLabel.setText(StartGame.EVENT_NAME);
            updatePlayScreen(currentEvent.startEvent());

            // Creates user interaction buttons
            Button nextButton = new Button(StartGame.NEXT_BUTTON);
            userButtons.getChildren().set(0, nextButton);
            nextButton.setOnAction(event1 -> {
                updatePlayScreen(currentEvent.endEvent(model, ""));
                Button nextButton2 = new Button(StartGame.NEXT_BUTTON);
                userButtons.getChildren().set(0, nextButton2);
                nextButton2.setOnAction(event2 -> {
                    currentEvent = new StartRound(model);
                    update(model, this);
                });
            });

        // Asks for users resource allocation and starts a game round
        } else if(currentEvent instanceof StartRound) {
            topLabel.setText(StartRound.EVENT_NAME);
            updatePlayScreen(currentEvent.startEvent());
            playScreen.setCenter(new Pane(new LocationDisplay(model).createLocationDisplay()));
            GridPane allocationGrid = makeAllocationGrid();
            playScreen.setLeft(allocationGrid);

            // Creates user interaction buttons
            Button nextButton = new Button(StartRound.NEXT_BUTTON);
            userButtons.getChildren().set(0, nextButton);
            nextButton.setOnAction(event3 -> {
                if(model.getAllocations().getTotalAllocation() == model.getGroupSize()) {
                    currentEvent = new GameLoop(model);
                    update(model, this);
                }
            });

            // Checks if the group is dead
            if(model.isDead()) {
                currentEvent = new EndGame(model);
                update(model, this);
            }

        // Starts the main game loop
        } else if(currentEvent instanceof GameLoop){
            topLabel.setText(GameLoop.EVENT_NAME);
            updatePlayScreen(currentEvent.startEvent());

            // Creates user interaction buttons
            Button nextButton = new Button(GameLoop.NEXT_BUTTON);
            userButtons.getChildren().set(0, nextButton);
            nextButton.setOnAction(event -> {
                updatePlayScreen(currentEvent.endEvent(model, ""));
                Button nextButton2 = new Button(GameLoop.NEXT_BUTTON);
                userButtons.getChildren().set(0, nextButton2);
                Event randomEvent = model.createEvent();
                if(randomEvent != null) {
                    currentEvent = randomEvent;
                }
                nextButton2.setOnAction(event2 -> {
                    if(!(currentEvent instanceof GameLoop)) {
                        update(model, this);
                    } else {
                        currentEvent = new StartRound(model);
                        update(model, this);
                    }
                });
            });

        // Starts the special event if one is active
        } else if(currentEvent instanceof EndGame) {
            topLabel.setText(EndGame.EVENT_NAME);
            updatePlayScreen(currentEvent.startEvent());
            playScreen.setCenter(new Label(""));

            // Creates user interaction buttons
            Button nextButton = new Button(EndGame.NEXT_BUTTON);
            userButtons.getChildren().set(0, nextButton);
            nextButton.setOnAction(event3 -> {
                this.model = new GameModel();
                currentEvent = new StartGame(model);
                this.resourceBars = new ResourceBars(model);
                playScreen.setRight(resourceBars.makeBars());
                update(model, this);
            });

        } else {
            // Clears center location display
            playScreen.setCenter(new Label(""));
            runEvent();
        }
    }


    /**
     * Method to run a special event with user input.
     */
    public void runEvent() {
        // String values for the event
        String eventName;
        String eventOption1;
        String eventOption2;
        String nextOption;

        // Checks for the event type
        if(currentEvent instanceof Tornado) {
            eventName = Tornado.EVENT_NAME;
            eventOption1 = Tornado.RUN_BUTTON;
            eventOption2 = Tornado.HIDE_BUTTON;
            nextOption = Tornado.NEXT_BUTTON;
        } else if(currentEvent instanceof NewTools) {
            eventName = NewTools.EVENT_NAME;
            eventOption1 = NewTools.TRAIN_BUTTON;
            eventOption2 = NewTools.PASS_BUTTON;
            nextOption = NewTools.NEXT_BUTTON;
        } else if(currentEvent instanceof RiverCrossing) {
            eventName = RiverCrossing.EVENT_NAME;
            eventOption1 = RiverCrossing.BUILD_BUTTON;
            eventOption2 = RiverCrossing.SWIM_BUTTON;
            nextOption = RiverCrossing.NEXT_BUTTON;
        } else {
            eventName = OpposingGroup.EVENT_NAME;
            eventOption1 = OpposingGroup.STAY_BUTTON;
            eventOption2 = OpposingGroup.LEAVE_BUTTON;
            nextOption = OpposingGroup.NEXT_BUTTON;
        }

        // Updates and starts the event
        topLabel.setText(eventName);
        updatePlayScreen(currentEvent.startEvent());

        // Creates the first user option
        Button option1Button = new Button(eventOption1);
        userButtons.getChildren().set(0, option1Button);
        option1Button.setOnAction(event -> {
            updatePlayScreen(currentEvent.endEvent(model, eventOption1));
            Button nextButton = new Button(nextOption);
            userButtons.getChildren().set(0, nextButton);
            userButtons.getChildren().remove(1);
            nextButton.setOnAction(event2 -> {
                currentEvent = new StartRound(model);
                update(model, this);
            });


        });

        // Creates the second user option
        Button option2Button = new Button(eventOption2);
        userButtons.add(option2Button, 1, 0);
        option2Button.setOnAction(event -> {
            updatePlayScreen(currentEvent.endEvent(model, eventOption2));
            Button nextButton2 = new Button(nextOption);
            userButtons.getChildren().set(0, nextButton2);
            userButtons.getChildren().remove(1);
            nextButton2.setOnAction(event2 -> {
                currentEvent = new StartRound(model);
                update(model, this);
            });

        });
    }

    /**
     * Method to update the play screen with new text.
     * @param text String representing the text to display on the screen
     */
    public void updatePlayScreen(String text){
        Label label = new Label(text);
        label.setPadding(new Insets(5.0));
        playScreen.setLeft(new Pane(label));
    }

    /**
     * Method to create the allocation grid for user selection.
     * @return GridPane representing the buttons and labels for the allocation grid
     */
    public GridPane makeAllocationGrid(){
        // Sets the default allocations and creates the allocation grid
        model.setAllocations(new Allocations());
        GridPane grid = new GridPane();
        grid.setHgap(5.0);
        grid.setVgap(5.0);

        // Creates the hunting buttons and label
        Button huntingDecrease = new Button(Allocations.DECREMENT_BUTTON);
        Label huntingLabel = new Label(Allocations.HUNTING_LABEL + ": " + model.getAllocations().getHuntingAlloc());
        Button huntingIncrease = new Button(Allocations.INCREMENT_BUTTON);
        grid.add(huntingDecrease, 0, 0);
        grid.add(huntingLabel, 1, 0);
        grid.add(huntingIncrease, 2, 0);

        // Sets actions for the hunting allocation buttons
        huntingDecrease.setOnAction(event -> {
            model.getAllocations().setHuntingAlloc(Allocations.DECREMENT);
            ((Label) grid.getChildren().get(1)).setText(Allocations.HUNTING_LABEL + ": " + model.getAllocations().getHuntingAlloc());
        });
        huntingIncrease.setOnAction(event -> {
            if(model.getAllocations().getTotalAllocation() < model.getGroupSize()) {
                model.getAllocations().setHuntingAlloc(Allocations.INCREMENT);
            }
            ((Label) grid.getChildren().get(1)).setText(Allocations.HUNTING_LABEL + ": " + model.getAllocations().getHuntingAlloc());
        });

        // Creates the gathering buttons and label
        Button gatheringDecrease = new Button(Allocations.DECREMENT_BUTTON);
        Label gatheringLabel = new Label(Allocations.GATHERING_LABEL + ": " + model.getAllocations().getGatheringAlloc());
        Button gatheringIncrease = new Button(Allocations.INCREMENT_BUTTON);
        grid.add(gatheringDecrease, 0, 1);
        grid.add(gatheringLabel, 1, 1);
        grid.add(gatheringIncrease, 2, 1);

        // Sets actions for the gathering allocation buttons
        gatheringDecrease.setOnAction(event -> {
            model.getAllocations().setGatheringAlloc(Allocations.DECREMENT);
            ((Label) grid.getChildren().get(4)).setText(Allocations.GATHERING_LABEL + ": " + model.getAllocations().getGatheringAlloc());
        });
        gatheringIncrease.setOnAction(event -> {
            if(model.getAllocations().getTotalAllocation() < model.getGroupSize()) {
                model.getAllocations().setGatheringAlloc(Allocations.INCREMENT);
            }
            ((Label) grid.getChildren().get(4)).setText(Allocations.GATHERING_LABEL + ": " + model.getAllocations().getGatheringAlloc());
        });

        // Creates the water buttons and label
        Button waterDecrease = new Button(Allocations.DECREMENT_BUTTON);
        Label waterLabel = new Label(Allocations.WATER_LABEL + ": " + model.getAllocations().getWaterAlloc());
        Button waterIncrease = new Button(Allocations.INCREMENT_BUTTON);
        grid.add(waterDecrease, 0, 2);
        grid.add(waterLabel, 1, 2);
        grid.add(waterIncrease, 2, 2);

        // Sets actions for the water allocation buttons
        waterDecrease.setOnAction(event -> {
            model.getAllocations().setWaterAlloc(Allocations.DECREMENT);
            ((Label) grid.getChildren().get(7)).setText(Allocations.WATER_LABEL + ": " + model.getAllocations().getWaterAlloc());
        });
        waterIncrease.setOnAction(event -> {
            if(model.getAllocations().getTotalAllocation() < model.getGroupSize()) {
                model.getAllocations().setWaterAlloc(Allocations.INCREMENT);
            }
            ((Label) grid.getChildren().get(7)).setText(Allocations.WATER_LABEL + ": " + model.getAllocations().getWaterAlloc());
        });

        // Creates the clothing buttons and label
        Button clothingDecrease = new Button(Allocations.DECREMENT_BUTTON);
        Label clothingLabel = new Label(Allocations.CLOTHING_LABEL + ": " + model.getAllocations().getClothingAlloc());
        Button clothingIncrease = new Button(Allocations.INCREMENT_BUTTON);
        grid.add(clothingDecrease, 0, 3);
        grid.add(clothingLabel, 1, 3);
        grid.add(clothingIncrease, 2, 3);

        // Sets actions for the clothing allocation buttons
        clothingDecrease.setOnAction(event -> {
            model.getAllocations().setClothingAlloc(Allocations.DECREMENT);
            ((Label) grid.getChildren().get(10)).setText(Allocations.CLOTHING_LABEL + ": " + model.getAllocations().getClothingAlloc());
        });
        clothingIncrease.setOnAction(event -> {
            if(model.getAllocations().getTotalAllocation() < model.getGroupSize()) {
                model.getAllocations().setClothingAlloc(Allocations.INCREMENT);
            }
            ((Label) grid.getChildren().get(10)).setText(Allocations.CLOTHING_LABEL + ": " + model.getAllocations().getClothingAlloc());
        });

        // Creates the tools buttons and label
        Button toolsDecrease = new Button(Allocations.DECREMENT_BUTTON);
        Label toolsLabel = new Label(Allocations.TOOLS_LABEL + ": " + model.getAllocations().getToolsAlloc());
        Button toolsIncrease = new Button(Allocations.INCREMENT_BUTTON);
        grid.add(toolsDecrease, 0, 4);
        grid.add(toolsLabel, 1, 4);
        grid.add(toolsIncrease, 2, 4);

        // Sets actions for the tools allocation buttons
        toolsDecrease.setOnAction(event -> {
            model.getAllocations().setToolsAlloc(Allocations.DECREMENT);
            ((Label) grid.getChildren().get(13)).setText(Allocations.TOOLS_LABEL + ": " + model.getAllocations().getToolsAlloc());
        });
        toolsIncrease.setOnAction(event -> {
            if(model.getAllocations().getTotalAllocation() < model.getGroupSize()) {
                model.getAllocations().setToolsAlloc(Allocations.INCREMENT);
            }
            ((Label) grid.getChildren().get(13)).setText(Allocations.TOOLS_LABEL + ": " + model.getAllocations().getToolsAlloc());
        });

        // Creates the morale buttons and label
        Button moraleDecrease = new Button(Allocations.DECREMENT_BUTTON);
        Label moraleLabel = new Label(Allocations.MORALE_LABEL + ": " + model.getAllocations().getMoraleAlloc());
        Button moraleIncrease = new Button(Allocations.INCREMENT_BUTTON);
        grid.add(moraleDecrease, 0, 5);
        grid.add(moraleLabel, 1, 5);
        grid.add(moraleIncrease, 2, 5);

        // Sets actions for the morale allocation buttons
        moraleDecrease.setOnAction(event -> {
            model.getAllocations().setMoraleAlloc(Allocations.DECREMENT);
            ((Label) grid.getChildren().get(16)).setText(Allocations.MORALE_LABEL + ": " + model.getAllocations().getMoraleAlloc());
        });
        moraleIncrease.setOnAction(event -> {
            if(model.getAllocations().getTotalAllocation() < model.getGroupSize()) {
                model.getAllocations().setMoraleAlloc(Allocations.INCREMENT);
            }
            ((Label) grid.getChildren().get(16)).setText(Allocations.MORALE_LABEL + ": " + model.getAllocations().getMoraleAlloc());
        });

        return grid;
    }

    /**
     * Method to create the various border pane objects.
     * @return BorderPane representing the scene for the application
     */
    private BorderPane makeBorder(){
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10.0));
        border.setTop(makeTopLabel());
        border.setLeft(makeLeftLabel());
        resourceBars = new ResourceBars(model);
        border.setRight(resourceBars.makeBars());
        border.setBottom(makeUserButtons());
        playScreen = border;
        return border;
    }

    /**
     * Method to create the text label at the top of the application.
     * @return Pane representing the top label
     */
    private Pane makeTopLabel(){
        Label label = new Label("Welcome to PreCivilization!");
        Pane pane = new Pane(label);
        topLabel = label;
        topLabel.setFont(new Font(20.0));
        topLabel.setPadding(new Insets(0.0, 0.0, 15.0, 5.0));
        return pane;
    }

    /**
     * Method to create the splash screen at the left of the application.
     * @return Pane representing the left label
     */
    private Pane makeLeftLabel() {
        Image image = new Image(getClass().getResource("/resources/splash.png").toExternalForm());
        ImageView logo = new ImageView(image);
        Pane pane = new Pane(logo);
        return pane;
    }

    /**
     * Method to create the user input buttons on the bottom of the pane.
     * @return GridPane representing the user input section
     */
    private GridPane makeUserButtons(){
        GridPane buttonGrid = new GridPane();
        Button startButton = new Button("Start Game");
        buttonGrid.add(startButton, 0, 0);
        userButtons = buttonGrid;
        startButton.setOnAction(event ->{
            currentEvent = new StartGame(model);
            update(model, this);
        });
        return buttonGrid;
    }
}