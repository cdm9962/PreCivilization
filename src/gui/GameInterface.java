package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.GameModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * The graphical user interface object for the game. Displays the game data.
 *
 * @author Connor D. Milligan
 */
public class GameInterface extends Application implements Observer {
    // the model of the game, holds the private state data
    private GameModel model;

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
        return border;
    }

    /**
     * Method to create the text label at the top of the application.
     * @return
     */
    private Pane makeTopLabel(){
        Label label = new Label("Welcome to PreCivilization!");
        Pane pane = new Pane(label);
        return pane;
    }

    private Pane makeLeftLabel() {
//        Label label = new Label();
//        label.setMinSize(1000, 1000);
//        label.setWrapText(true);
//        label.setFont(new Font("Monospaced", 20));
//
//        try {
//            String logo = scanLogo();
//            label.setText(logo);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        Image image = new Image("file:splash.png");
        ImageView logo = new ImageView(image);
        Pane pane = new Pane(logo);
        return pane;
    }

    private String scanLogo() throws FileNotFoundException {
        InputStream stream = new FileInputStream("splashLogo.txt");
        Scanner scanner = new Scanner(stream);
        String logo = " ";

        while (scanner.hasNextLine()){
            logo += scanner.nextLine();
            logo += "\n ";
            System.out.println(logo);
        }

        return logo;
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
        Label healthLabel = new Label(HEALTH_TITLE);
        grid.add(healthLabel, col, row);
        col++;
        HBox healthBox = new HBox();
        healthBox.setMinSize(model.getHealth(), DEFAULT_HIGHT);
        healthBox.setStyle(HEALTH_COLOR);
        grid.add(healthBox, col, row);
        col--;
        row++;

        // Creates the food bar and label
        Label foodLabel = new Label(FOOD_TITLE);
        grid.add(foodLabel, col, row);
        col++;
        HBox foodBox = new HBox();
        foodBox.setMinSize(model.getFood(), DEFAULT_HIGHT);
        foodBox.setStyle(FOOD_COLOR);
        grid.add(foodBox, col, row);
        col--;
        row++;

        // Creates the water bar and label
        Label waterLabel = new Label(WATER_TITLE);
        grid.add(waterLabel, col, row);
        col++;
        HBox waterBox = new HBox();
        waterBox.setMinSize(model.getWater(), DEFAULT_HIGHT);
        waterBox.setStyle(WATER_COLOR);
        grid.add(waterBox, col, row);
        col--;
        row++;

        // Creates the clothing bar and label
        Label clothingLabel = new Label(CLOTHING_TITLE);
        grid.add(clothingLabel, col, row);
        col++;
        HBox clothingBox = new HBox();
        clothingBox.setMinSize(model.getClothing(), DEFAULT_HIGHT);
        clothingBox.setStyle(CLOTHING_COLOR);
        grid.add(clothingBox, col, row);
        col--;
        row++;

        // Creates the tools bar and label
        Label toolsLabel = new Label(TOOLS_TITLE);
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

        return grid;
    }
}
