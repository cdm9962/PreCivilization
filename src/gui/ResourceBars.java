package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.GameModel;

/**
 * GUI class to hold and alter the game resource bars.
 *
 * @author Connor D. Milligan
 */
public class ResourceBars {
    private GameModel model;
    private GridPane resourceBars;

    // Default values for the bar sizes
    final int DEFAULT_WIDTH = 100;
    final int DEFAULT_HIGHT = 20;

    // The colors for the various bars
    public static final String HEALTH_COLOR = "-fx-background-color: red;";
    public static final String FOOD_COLOR = "-fx-background-color: green;";
    public static final String WATER_COLOR = "-fx-background-color: blue;";
    public static final String CLOTHING_COLOR = "-fx-background-color: orange;";
    public static final String TOOLS_COLOR = "-fx-background-color: purple;";
    public static final String MORALE_COLOR = "-fx-background-color: yellow;";

    // String values for the bar descriptions
    public static final String HEALTH_TITLE = "Health";
    public static final String FOOD_TITLE = "Food";
    public static final String WATER_TITLE = "Water";
    public static final String CLOTHING_TITLE = "Clothing";
    public static final String TOOLS_TITLE = "Tools";
    public static final String MORALE_TITLE = "Morale";
    public static final String GROUP_SIZE_TITLE = "Group Size";

    // Index values for each bar in the GridPane
    public static final int HEALTH_INDEX = 1;
    public static final int FOOD_INDEX = 3;
    public static final int WATER_INDEX = 5;
    public static final int CLOTHING_INDEX = 7;
    public static final int TOOLS_INDEX = 9;
    public static final int MORALE_INDEX = 11;
    public static final int GROUP_SIZE_INDEX = 12;

    /**
     * ResourceBars constructor.
     *
     * @param model GameModel for the simulation
     */
    public ResourceBars(GameModel model) {
        this.model = model;
    }

    /**
     * Method to create the various resource bars. Scales the bars based on their value in the model.
     *
     * @return GridPane representing the array of resource bars
     */
    public GridPane makeBars() {
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
        Label groupSizeLabel = new Label(GROUP_SIZE_TITLE + ": " + model.getGroupSize());
        grid.add(groupSizeLabel, col, row);
        col--;
        row++;

        // Sets and returns the resource bars
        resourceBars = grid;
        return grid;
    }

    /**
     * Method to update the resource bars on the game UI.
     */
    public void updateResourceBars() {
        // Update health resource bar
        ((HBox) resourceBars.getChildren().get(HEALTH_INDEX)).setMaxWidth(model.getHealth());
        ((Label) resourceBars.getChildren().get(HEALTH_INDEX - 1)).setText(HEALTH_TITLE + ":\t" + model.getHealth());
        // Update food resource bar
        ((HBox) resourceBars.getChildren().get(FOOD_INDEX)).setMaxWidth(model.getFood());
        ((Label) resourceBars.getChildren().get(FOOD_INDEX - 1)).setText(FOOD_TITLE + ":\t" + model.getFood());
        // Update water resource bar
        ((HBox) resourceBars.getChildren().get(WATER_INDEX)).setMaxWidth(model.getWater());
        ((Label) resourceBars.getChildren().get(WATER_INDEX - 1)).setText(WATER_TITLE + ":\t" + model.getWater());
        // Update clothing resource bar
        ((HBox) resourceBars.getChildren().get(CLOTHING_INDEX)).setMaxWidth(model.getClothing());
        ((Label) resourceBars.getChildren().get(CLOTHING_INDEX - 1)).setText(CLOTHING_TITLE + ":\t" + model.getClothing());
        // Update tools resource bar
        ((HBox) resourceBars.getChildren().get(TOOLS_INDEX)).setMaxWidth(model.getTools());
        ((Label) resourceBars.getChildren().get(TOOLS_INDEX - 1)).setText(TOOLS_TITLE + ":\t" + model.getTools());
        // Update morale resource bar
        ((HBox) resourceBars.getChildren().get(MORALE_INDEX)).setMaxWidth(model.getMorale());
        ((Label) resourceBars.getChildren().get(MORALE_INDEX - 1)).setText(MORALE_TITLE + ":\t" + model.getMorale());
        // Update group size display
        ((Label) resourceBars.getChildren().get(GROUP_SIZE_INDEX)).setText(GROUP_SIZE_TITLE+ ": " + model.getGroupSize());

    }
}
