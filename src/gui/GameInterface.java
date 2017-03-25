package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.events.Event;
import model.GameModel;
import model.events.StartGame;
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

    private BorderPane playScreen;
    private GridPane resourceBars;
    private GridPane userButtons;

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
        if(userCommand.equals("Start Game")) {
            Event event = new StartGame();
            currentEvent = event;
            updatePlayScreen(currentEvent.startEvent());
            Button nextButton = new Button("Next");
            nextButton.setOnAction(event1 -> {
                updatePlayScreen(currentEvent.endEvent(model, ""));
            });
            userButtons.getChildren().set(0, nextButton);
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
        playScreen.setLeft(new Pane(new Label(text)));
    }

    /**
     * Method to create the various border pane objects.
     * @return BorderPane representing the scene for the application
     */
    private BorderPane makeBorder(){
        BorderPane border = new BorderPane();
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
        final String GROUP_SIZE_TITLE = "Group Size:";

        // The colors for the various bars
        final String HEALTH_COLOR = "-fx-background-color: red;";
        final String FOOD_COLOR = "-fx-background-color: green;";
        final String WATER_COLOR = "-fx-background-color: blue;";
        final String CLOTHING_COLOR = "-fx-background-color: yellow;";
        final String TOOLS_COLOR = "-fx-background-color: purple;";

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


    /**
     * Method to create the user input box on the bottom of the pane.
     * @return GridPane representing the user input section
     */
    private GridPane makeUserBox(){
        GridPane grid = new GridPane();
        Label title = new Label("Enter Command: ");
        grid.add(title, 0, 0);
        TextField input = new TextField();
        grid.add(input, 1, 0);

        input.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                String command = input.getCharacters().toString();
                if(command.equals("Start Game")){
                    System.out.println("Starting Game...");
                    userCommand = command;
                    update(model, this);
                } else if(command.equals("Attack")){
                    System.out.println("Attacking...");
                    userCommand = command;
                    update(model, this);
                } else if(command.equals("Tornado")){
                    System.out.println("Tornado...");
                    userCommand = command;
                    update(model, this);
                } else if(command.equals("run")){
                    System.out.println("Running...");
                    userCommand = command;
                    update(model, this);
                } else if(command.equals("hide")){
                    System.out.println("Hiding...");
                    userCommand = command;
                    update(model, this);
                }
            }
        });

        return grid;
    }
}
