package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.Allocations;
import model.GameLoop;
import model.events.Event;
import model.GameModel;
import model.events.StartGame;
import model.events.StartRound;
import model.events.Tornado;

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
    private GridPane resourceBars;
    private GridPane userButtons;

    // The stored user data
    private String userCommand;
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
        // Launches the first game section
        if(userCommand.equals(StartGame.EVENT_NAME)) {
            topLabel.setText(StartGame.EVENT_NAME);
            currentEvent = new StartGame();
            updatePlayScreen(currentEvent.startEvent());
            Button nextButton1 = new Button(StartGame.NEXT_BUTTON);
            userButtons.getChildren().set(0, nextButton1);
            nextButton1.setOnAction(event1 -> {
                updatePlayScreen(currentEvent.endEvent(model, ""));
                Button nextButton2 = new Button(StartGame.NEXT_BUTTON);
                userButtons.getChildren().set(0, nextButton2);
                nextButton2.setOnAction(event2 -> {
                    userCommand = StartRound.EVENT_NAME;
                    update(model, this);
                });
            });

        // Asks for users resource allocation and starts a game round
        } else if(userCommand.equals(StartRound.EVENT_NAME)){
            topLabel.setText(StartRound.EVENT_NAME);
            currentEvent = new StartRound();
            updatePlayScreen(currentEvent.startEvent());
            GridPane allocationGrid = makeAllocationGrid();
            playScreen.setLeft(allocationGrid);

            Button nextButton3 = new Button(GameLoop.NEXT_BUTTON);
            userButtons.getChildren().set(0, nextButton3);
            nextButton3.setOnAction(event3 -> {
                userCommand = GameLoop.EVENT_NAME;
                update(model, this);
            });
        }





        // Test code for resource bar manipulation
        if(userCommand.equals("Attack")){
            System.out.println(model.getHealth());
            model.takeDamage(10);
            ((HBox) resourceBars.getChildren().get(1)).setMaxWidth(model.getHealth());
            ((Label) resourceBars.getChildren().get(0)).setText("Health:\t" + model.getHealth());
            if(model.isDead()) {
                playScreen.setLeft(new Pane(new Label("Thanks for play, GG no Re")));
            }
        }

        if(userCommand.equals("Tornado")){
            Event event = new Tornado();
            currentEvent = event;
            playScreen.setLeft(new Pane(new Label(event.startEvent())));
        }

        if(userCommand.equals("run")){
            playScreen.setLeft(new Pane(new Label(currentEvent.endEvent(model, userCommand))));
        }

        if(userCommand.equals("hide")){
            model.setFood((int) (model.getFood() * 0.95));
            ((HBox) resourceBars.getChildren().get(3)).setMaxWidth(model.getFood());
            ((Label) resourceBars.getChildren().get(2)).setText("Food:\t" + model.getFood());

            model.setGroupSize(model.getGroupSize() - 2);
            ((Label) resourceBars.getChildren().get(10)).setText("Group Size: " + model.getGroupSize());

            playScreen.setLeft(new Pane(new Label(currentEvent.endEvent(model, userCommand))));
        }
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
        border.setPadding(new Insets(5.0));
        border.setTop(makeTopLabel());
        border.setLeft(makeLeftLabel());
        border.setRight(makeBars());
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
        Image image = new Image("file:splash.png");
        ImageView logo = new ImageView(image);
        Pane pane = new Pane(logo);
        return pane;
    }

    /**
     * Method to create the various resource bars. Scales the bars based on their value in the model.
     * @return GridPane representing the array of resource bars
     */
    private GridPane makeBars(){
        // Default values for the bar sizes
        final int DEFAULT_WIDTH = 100;
        final int DEFAULT_HIGHT = 20;

        // String values for the bar descriptions
        final String HEALTH_TITLE = "Health";
        final String FOOD_TITLE = "Food";
        final String WATER_TITLE = "Water";
        final String CLOTHING_TITLE = "Clothing";
        final String TOOLS_TITLE = "Tools";
        final String MORALE_TITLE = "Morale";
        final String GROUP_SIZE_TITLE = "Group Size:";

        // The colors for the various bars
        final String HEALTH_COLOR = "-fx-background-color: red;";
        final String FOOD_COLOR = "-fx-background-color: green;";
        final String WATER_COLOR = "-fx-background-color: blue;";
        final String CLOTHING_COLOR = "-fx-background-color: orange;";
        final String TOOLS_COLOR = "-fx-background-color: purple;";
        final String MORALE_COLOR = "-fx-background-color: yellow;";

        // Column and row values for the grid pane
        int row = 0;
        int col = 0;

        // Creates and initializes the grid pane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Creates the health bar and label
        String healthValue = Integer.toString(model.getHealth());
        Label healthLabel = new Label(HEALTH_TITLE + ":\t" + healthValue);
        grid.add(healthLabel, col, row);
        col++;
        HBox healthBox = new HBox();
        healthBox.setPrefSize(model.getHealth(), DEFAULT_HIGHT);
        healthBox.setStyle(HEALTH_COLOR);
        grid.add(healthBox, col, row);
        col--;
        row++;

        // Creates the food bar and label
        String foodValue = Integer.toString(model.getFood());
        Label foodLabel = new Label(FOOD_TITLE + ":\t" + foodValue);
        grid.add(foodLabel, col, row);
        col++;
        HBox foodBox = new HBox();
        foodBox.setPrefSize(model.getFood(), DEFAULT_HIGHT);
        foodBox.setStyle(FOOD_COLOR);
        grid.add(foodBox, col, row);
        col--;
        row++;

        // Creates the water bar and label
        String waterValue = Integer.toString(model.getWater());
        Label waterLabel = new Label(WATER_TITLE + ":\t" + waterValue);
        grid.add(waterLabel, col, row);
        col++;
        HBox waterBox = new HBox();
        waterBox.setMinSize(model.getWater(), DEFAULT_HIGHT);
        waterBox.setStyle(WATER_COLOR);
        grid.add(waterBox, col, row);
        col--;
        row++;

        // Creates the clothing bar and label
        String clothingValue = Integer.toString(model.getClothing());
        Label clothingLabel = new Label(CLOTHING_TITLE + ":\t" + clothingValue);
        grid.add(clothingLabel, col, row);
        col++;
        HBox clothingBox = new HBox();
        clothingBox.setMinSize(model.getClothing(), DEFAULT_HIGHT);
        clothingBox.setStyle(CLOTHING_COLOR);
        grid.add(clothingBox, col, row);
        col--;
        row++;

        // Creates the tools bar and label
        String toolsValue = Integer.toString(model.getTools());
        Label toolsLabel = new Label(TOOLS_TITLE + ":\t" + toolsValue);
        grid.add(toolsLabel, col, row);
        col++;
        HBox toolsBox = new HBox();
        toolsBox.setMinSize(model.getTools(), DEFAULT_HIGHT);
        toolsBox.setStyle(TOOLS_COLOR);
        grid.add(toolsBox, col, row);
        col--;
        row++;

        // Creates the morale bar and label
        String moraleValue = Integer.toString(model.getHappiness());
        Label moraleLabel = new Label(MORALE_TITLE + ":\t" + moraleValue);
        grid.add(moraleLabel, col, row);
        col++;
        HBox moraleBox = new HBox();
        moraleBox.setMinSize(model.getHappiness(), DEFAULT_HIGHT);
        moraleBox.setStyle(MORALE_COLOR);
        grid.add(moraleBox, col, row);
        col--;
        row++;

        // Creates the group size label
        Label groupSizeLabel = new Label(GROUP_SIZE_TITLE + " " + model.getGroupSize());
        grid.add(groupSizeLabel, col, row);
        col--;
        row++;

        resourceBars = grid;
        return grid;
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
            userCommand = "Start Game";
            update(model, this);
        });
        return buttonGrid;
    }
}