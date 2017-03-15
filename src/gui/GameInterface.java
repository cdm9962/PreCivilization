package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.GameModel;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Connor D. Milligan
 */
public class GameInterface extends Application implements Observer {
    private GameModel model;

    public static final String TITLE = "PreCivilization";

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

    private BorderPane makeBorder(){
        BorderPane border = new BorderPane();
        border.setTop(makeLabel());
        border.setRight(makeBars());
        return border;
    }

    private Pane makeLabel(){
        Label label = new Label("Welcome to PreCivilization!");
        Pane pane = new Pane(label);
        return pane;
    }

    private GridPane makeBars(){
        final int DEFAULT_WIDTH = 100;
        final int DEFAULT_HIGHT = 20;

        final String HEALTH_TITLE = "Health";
        final String FOOD_TITLE = "Food";
        final String WATER_TITLE = "Water";
        final String CLOTHING_TITLE = "Clothing";
        final String TOOLS_TITLE = "Tools";
        final String GROUP_SIZE_TITLE = "Group Size:";

        final String HEALTH_COLOR = "-fx-background-color: red;";
        final String FOOD_COLOR = "-fx-background-color: green;";
        final String WATER_COLOR = "-fx-background-color: blue;";
        final String CLOTHING_COLOR = "-fx-background-color: yellow;";
        final String TOOLS_COLOR = "-fx-background-color: purple;";

        int row = 0;
        int col = 0;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label healthLabel = new Label(HEALTH_TITLE);
        grid.add(healthLabel, col, row);
        col++;
        HBox healthBox = new HBox();
        healthBox.setMinSize(model.getHealth(), DEFAULT_HIGHT);
        healthBox.setStyle(HEALTH_COLOR);
        grid.add(healthBox, col, row);
        col--;
        row++;

        Label foodLabel = new Label(FOOD_TITLE);
        grid.add(foodLabel, col, row);
        col++;
        HBox foodBox = new HBox();
        foodBox.setMinSize(model.getFood(), DEFAULT_HIGHT);
        foodBox.setStyle(FOOD_COLOR);
        grid.add(foodBox, col, row);
        col--;
        row++;

        Label waterLabel = new Label(WATER_TITLE);
        grid.add(waterLabel, col, row);
        col++;
        HBox waterBox = new HBox();
        waterBox.setMinSize(model.getWater(), DEFAULT_HIGHT);
        waterBox.setStyle(WATER_COLOR);
        grid.add(waterBox, col, row);
        col--;
        row++;

        Label clothingLabel = new Label(CLOTHING_TITLE);
        grid.add(clothingLabel, col, row);
        col++;
        HBox clothingBox = new HBox();
        clothingBox.setMinSize(model.getClothing(), DEFAULT_HIGHT);
        clothingBox.setStyle(CLOTHING_COLOR);
        grid.add(clothingBox, col, row);
        col--;
        row++;

        Label toolsLabel = new Label(TOOLS_TITLE);
        grid.add(toolsLabel, col, row);
        col++;
        HBox toolsBox = new HBox();
        toolsBox.setMinSize(model.getTools(), DEFAULT_HIGHT);
        toolsBox.setStyle(TOOLS_COLOR);
        grid.add(toolsBox, col, row);
        col--;
        row++;

        Label groupSizeLabel = new Label(GROUP_SIZE_TITLE + " " + model.getGroupSize());
        grid.add(groupSizeLabel, col, row);
        col--;
        row++;

        return grid;
    }
}
