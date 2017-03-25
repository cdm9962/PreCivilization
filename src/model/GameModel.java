package model;

import java.util.Observable;

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

    // Constant/Default values for the game model
    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_FOOD = 100;
    public static final int DEFAULT_WATER = 100;
    public static final int DEFAULT_CLOTHING = 100;
    public static final int DEFAULT_STORAGE = 100;
    public static final int DEFAULT_TOOLS = 100;
    public static final int DEFAULT_MORALE = 100;
    public static final int DEFAULT_GROUP_SIZE = 50;

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
    }

    /**
     * Method for reducing a players health.
     * @param damage int representing the amount a damage a player is to take
     */
    public void takeDamage(int damage){
        this.setHealth(this.getHealth() - damage);
        // Ensures that health does not drop below zero and checks for death
        if(this.getHealth() < 0){
            this.setHealth(0);
            System.out.println("YOU ARE KILL");
        }
    }

    /**
     * Method to check if a player has lost the game.
     * @return boolean representing if the player has lost
     */
    public boolean isDead(){
        return this.getHealth() == 0;
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

    public void setFood(int food) {
        this.food = food;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getClothing() {
        return clothing;
    }

    public void setClothing(int clothing) {
        this.clothing = clothing;
    }

    public int getTools() {
        return tools;
    }

    public void setTools(int tools) {
        this.tools = tools;
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

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public Allocations getAllocations() {
        return allocations;
    }

    public void setAllocations(Allocations allocations) {
        this.allocations = allocations;
    }
}