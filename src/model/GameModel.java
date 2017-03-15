package model;

/**
 * @author Connor D. Milligan
 */
public class GameModel {
    private int health;
    private int food;
    private int water;
    private int clothing;
    private int storage;
    private int tools;
    private int groupSize;

    public GameModel(int health, int food, int water, int groupSize, int tools, int storage, int clothing) {
        this.health = health;
        this.food = food;
        this.water = water;
        this.groupSize = groupSize;
        this.tools = tools;
        this.storage = storage;
        this.clothing = clothing;
    }

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
}
