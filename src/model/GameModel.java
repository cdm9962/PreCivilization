package model;

import model.events.*;

import java.util.Observable;

import java.util.Random;

/**
 * The model for the game.  Updates based on user interactions and choices.
 *
 * @author Connor D. Milligan
 */
public class GameModel extends Observable {
    // The private state of the model
    private int health;
    private int food;
    private int water;
    private int clothing;
    private int storage;
    private int tools;
    private int morale;
    private int groupSize;
    private Allocations allocations;
    private Location location;

    // The random number generator
    private Random rand;

    // Constant/Default values for the game model
    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_FOOD = 100;
    public static final int DEFAULT_WATER = 100;
    public static final int DEFAULT_CLOTHING = 100;
    public static final int DEFAULT_STORAGE = 100;
    public static final int DEFAULT_TOOLS = 100;
    public static final int DEFAULT_MORALE = 100;
    public static final int DEFAULT_GROUP_SIZE = 5;

    /**
     * Manual constructor for testing.
     * @param health the overall health of the group
     * @param food the amount of food currently stored
     * @param water the amount of water currently stored
     * @param tools the amount of tools currently stored
     * @param storage the amount of storage currently available
     * @param clothing the amount of clothing currently stored
     * @param morale the overall morale of the group
     * @param groupSize the number of people in the group
     */
    public GameModel(int health, int food, int water, int clothing, int storage, int tools, int morale, int groupSize) {
        this.health = health;
        this.food = food;
        this.water = water;
        this.clothing = clothing;
        this.storage = storage;
        this.tools = tools;
        this.morale = morale;
        this.groupSize = groupSize;
        this.rand = new Random();
    }

    /**
     * Default constructor.
     */
    public GameModel(){
        this.health = DEFAULT_HEALTH;
        this.food = DEFAULT_FOOD;
        this.water = DEFAULT_WATER;
        this.clothing = DEFAULT_CLOTHING;
        this.storage = DEFAULT_STORAGE;
        this.tools = DEFAULT_TOOLS;
        this.morale = DEFAULT_MORALE;
        this.groupSize = DEFAULT_GROUP_SIZE;
        this.rand = new Random();
    }

    /**
     * Method to check if a player has lost the game.
     * @return boolean representing if the player has lost
     */
    public boolean isDead(){
        if(health == 50) {
            return true;
        } else if (food == 0 || water == 0 || clothing == 0 || tools == 0 || morale == 0){
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method to modify the model values base don the user allocations and the game location.
     */
    public void modifyValues() {
        this.modifyFood();
        this.modifyWater();
        this.modifyClothing();
        this.modifyTools();
        this.modifyMorale();
        this.modifyGroupSize();
        this.modifyHealth();
    }

    /**
     * Method to print all model values to the game UI.
     * @return String representing the model values
     */
    public String printValues(){
        return "Food: " + food + "\n" +
                "Water: " + water + "\n" +
                "Clothing: " + clothing + "\n" +
                "Tools: " + tools + "\n" +
                "Morale: " + morale + "\n" +
                "Group Size: " + groupSize + "\n";
    }

    /**
     * Method to create a random event if one is randomly chosen to occur.
     * @return Event object representing the created event
     */
    public Event createEvent() {
        int isEvent = rand.nextInt(100) + 1;
        System.out.println("Is Event: " + isEvent);

        // Check if an event is triggered
        if(isEvent > 50) {
            int chooseEvent = rand.nextInt(100) + 1;
            System.out.println("Choose Event: " + chooseEvent);
            if(chooseEvent > 75) {
                return new Tornado(this);
            } else if(chooseEvent > 50) {
                return new NewTools(this);
            } else if(chooseEvent > 25){
                return new RiverCrossing(this);
            } else {
                return new OpposingGroup(this);
            }
        }
        // No event is triggered
        return null;
    }

    /**
     * Method to modify the food value based on the user allocations and the game location.
     */
    public void modifyFood() {
        food = (int) (food + (this.allocations.getHuntingAlloc() * this.location.getHuntingRate()) +
                (this.allocations.getGatheringAlloc() * this.location.getGatheringRate()) - (groupSize * 0.5));
        if(food > DEFAULT_FOOD) {
            food = DEFAULT_FOOD;
        }
    }

    /**
     * Method to modify the water value based on the user allocations and the game location.
     */
    public void modifyWater() {
        water = (int) (water + (this.allocations.getWaterAlloc() * this.location.getWaterRate()) +
                (this.allocations.getWaterAlloc() * this.location.getWaterRate()) - (groupSize * 0.5));
        if(water > DEFAULT_WATER) {
            water = DEFAULT_WATER;
        }
    }

    /**
     * Method to modify the clothing value based on the user allocations and the game location.
     */
    public void modifyClothing() {
        clothing = (int) (clothing + (this.allocations.getClothingAlloc() * this.location.getClothingRate()) +
                (this.allocations.getClothingAlloc() * this.location.getClothingRate()) - (groupSize * 0.5));
        if(clothing > DEFAULT_CLOTHING) {
            clothing = DEFAULT_CLOTHING;
        }
    }

    /**
     * Method to modify the tools value based on the user allocations and the game location.
     */
    public void modifyTools() {
        tools = (int) (tools + (this.allocations.getToolsAlloc() * this.location.getToolsRate()) +
                (this.allocations.getToolsAlloc() * this.location.getToolsRate()) - (groupSize * 0.5));
        if(tools > DEFAULT_TOOLS) {
            tools = DEFAULT_TOOLS;
        }
    }

    /**
     * Method to modify the morale value based on the user allocations and the game location.
     */
    public void modifyMorale() {
        morale = (int) (morale + (this.allocations.getMoraleAlloc() * this.location.getMoraleRate()) +
                (this.allocations.getMoraleAlloc() * this.location.getMoraleRate()) - (groupSize * 0.5));
        if(morale > DEFAULT_MORALE) {
            morale = DEFAULT_MORALE;
        }
    }

    /**
     * Method to modify the group size randomly with each iteration.
     */
    public void modifyGroupSize() {
        int changeSize = rand.nextInt(100) + 1;
        if(changeSize > 90) {
            alterGroupSize(2);
        } else if(changeSize > 80) {
            alterGroupSize(1);
        } else if (changeSize > 70) {
            alterGroupSize(-1);
        } else if(changeSize > 60) {
            alterGroupSize(-2);
        }
    }

    /**
     * Method to modify the health based on the other resource values.
     */
    public void modifyHealth() {
        double ratio = (double) (food + water + clothing + tools + morale) / 500;
        health = (int) (100 * ratio);
    }

    /**
     * Method to change the food value by a certain amount.
     * @param amount int representing the amount to change
     */
    public void alterFood(int amount) {
        food += amount;
        if(food < 0){
            food = 0;
        } else if(food > DEFAULT_FOOD) {
            food = DEFAULT_FOOD;
        }
    }

    /**
     * Method to change the water value by a certain amount.
     * @param amount int representing the amount to change
     */
    public void alterWater(int amount) {
        water += amount;
        if(water < 0){
            water = 0;
        } else if(water > DEFAULT_WATER) {
            water = DEFAULT_WATER;
        }
    }

    /**
     * Method to change the clothing value by a certain amount.
     * @param amount int representing the amount to change
     */
    public void alterClothing(int amount) {
        clothing += amount;
        if(clothing < 0){
            clothing = 0;
        } else if(clothing > DEFAULT_CLOTHING) {
            clothing = DEFAULT_CLOTHING;
        }
    }

    /**
     * Method to change the tools value by a certain amount.
     * @param amount int representing the amount to change
     */
    public void alterTools(int amount) {
        tools += amount;
        if(tools < 0){
            tools = 0;
        } else if(tools > DEFAULT_TOOLS) {
            tools = DEFAULT_TOOLS;
        }
    }

    /**
     * Method to change the morale value by a certain amount.
     * @param amount int representing the amount to change
     */
    public void alterMorale(int amount) {
        morale += amount;
        if(morale < 0){
            morale = 0;
        } else if(morale > DEFAULT_MORALE) {
            morale = DEFAULT_MORALE;
        }
    }

    /**
     * Method to change the group size value by a certain amount.
     * @param amount int representing the amount to change
     */
    public void alterGroupSize(int amount) {
        groupSize += amount;
        if(groupSize < 0){
            groupSize = 0;
        }
    }

    public void setLocation(){
        this.location = new Location(Location.Locations.FOREST);
    }

    ///
    /// Getter and Setter methods for the private state.
    ///

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    public int getClothing() {
        return clothing;
    }

    public int getTools() {
        return tools;
    }

    public int getHappiness(){ return this.morale; }

    public void setHappiness(int morale){ this.morale = morale; }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getGroupSize() {
        return groupSize;
    }


    public int getMorale() {
        return morale;
    }

    public Allocations getAllocations() {
        return allocations;
    }

    public void setAllocations(Allocations allocations) {
        this.allocations = allocations;
    }
}