package model;

/**
 * Class to hold and manipulate player resource allocation information.
 *
 * @author Connor D. Milligan
 */
public class Allocations {
    // The private state of the allocations
    private int huntingAlloc;
    private int gatheringAlloc;
    private int waterAlloc;
    private int clothingAlloc;
    private int storageAlloc;
    private int toolsAlloc;
    private int moraleAlloc;

    // Constant button values
    public static final int DEFAULT_ALLOC = 0;
    public static final int DECREMENT = -1;
    public static final int INCREMENT = 1;
    public static final String DECREMENT_BUTTON = "<";
    public static final String INCREMENT_BUTTON = ">";

    // Constant allocation names
    public static final String HUNTING_LABEL = "Hunting";
    public static final String GATHERING_LABEL = "Gathering";
    public static final String WATER_LABEL = "Water";
    public static final String CLOTHING_LABEL = "Clothing";
    public static final String TOOLS_LABEL = "Tools";
    public static final String MORALE_LABEL = "Morale";

    /**
     * Manual constructor.
     * @param huntingAlloc int representing the number of people allocated to hunting
     * @param gatheringAlloc int representing the number of people allocated to gathering
     * @param waterAlloc int representing the number of people allocated to water procurement
     * @param clothingAlloc int representing the number of people allocated to making clothing
     * @param storageAlloc int representing the number of people allocated to creating storage
     * @param toolsAlloc int representing the number of people allocated to making tools
     * @param moraleAlloc int representing the number of people allocated to increasing happiness
     */
    public Allocations(int huntingAlloc, int gatheringAlloc, int waterAlloc, int clothingAlloc, int storageAlloc, int toolsAlloc, int moraleAlloc) {
        this.huntingAlloc = huntingAlloc;
        this.gatheringAlloc = gatheringAlloc;
        this.waterAlloc = waterAlloc;
        this.clothingAlloc = clothingAlloc;
        this.storageAlloc = storageAlloc;
        this.toolsAlloc = toolsAlloc;
        this.moraleAlloc = moraleAlloc;
    }

    /**
     * Constructor for Allocations.
     */
    public Allocations() {
        this.huntingAlloc = DEFAULT_ALLOC;
        this.gatheringAlloc = DEFAULT_ALLOC;
        this.waterAlloc = DEFAULT_ALLOC;
        this.clothingAlloc = DEFAULT_ALLOC;
        this.storageAlloc = DEFAULT_ALLOC;
        this.toolsAlloc = DEFAULT_ALLOC;
        this.moraleAlloc = DEFAULT_ALLOC;
    }

    /**
     * Method for checking to total player allocation.
     * @return int representing the total number of people currently allocated
     */
    public int getTotalAllocation(){
        return huntingAlloc + gatheringAlloc + waterAlloc + clothingAlloc + toolsAlloc + moraleAlloc;
    }

    /**
     * Getter method for the hunting allocation.
     * @return int representing the current hunting allocation
     */
    public int getHuntingAlloc() {
        return huntingAlloc;
    }

    /**
     * Setter method for the hunting allocation.
     * @param change int representing the amount to change the hunting allocation
     */
    public void setHuntingAlloc(int change) {
        huntingAlloc += change;
        if(huntingAlloc < 0){
            huntingAlloc = 0;
        }
    }

    /**
     * Getter method for the gathering allocation.
     * @return int representing the current gathering allocation
     */
    public int getGatheringAlloc() {
        return gatheringAlloc;
    }

    /**
     * Setter method for the gathering allocation.
     * @param change int representing the amount to change the gathering allocation
     */
    public void setGatheringAlloc(int change) {
        gatheringAlloc += change;
        if(gatheringAlloc < 0){
            gatheringAlloc = 0;
        }
    }

    /**
     * Getter method for the water allocation.
     * @return int representing the current water allocation
     */
    public int getWaterAlloc() {
        return waterAlloc;
    }

    /**
     * Setter method for the water allocation.
     * @param change int representing the amount to change the water allocation
     */
    public void setWaterAlloc(int change) {
        waterAlloc += change;
        if(waterAlloc < 0){
            waterAlloc = 0;
        }
    }

    /**
     * Getter method for the clothing allocation.
     * @return int representing the current hunting allocation
     */
    public int getClothingAlloc() {
        return clothingAlloc;
    }

    /**
     * Setter method for the clothing allocation.
     * @param change int representing the amount to change the clothing allocation
     */
    public void setClothingAlloc(int change) {
        clothingAlloc += change;
        if(clothingAlloc < 0){
            clothingAlloc = 0;
        }
    }

    /**
     * Getter method for the storage allocation.
     * @return int representing the current storage allocation
     */
    public int getStorageAlloc() {
        return storageAlloc;
    }

    /**
     * Setter method for the storage allocation.
     * @param change int representing the amount to change the storage allocation
     */
    public void setStorageAlloc(int change) {
        storageAlloc += change;
        if(storageAlloc < 0){
            storageAlloc = 0;
        }
    }

    /**
     * Getter method for the tools allocation.
     * @return int representing the current tools allocation
     */
    public int getToolsAlloc() {
        return toolsAlloc;
    }

    /**
     * Setter method for the tools allocation.
     * @param change int representing the amount to change the tools allocation
     */
    public void setToolsAlloc(int change) {
        toolsAlloc += change;
        if(toolsAlloc < 0){
            toolsAlloc = 0;
        }
    }

    /**
     * Getter method for the morale allocation.
     * @return int representing the current morale allocation
     */
    public int getMoraleAlloc() {
        return moraleAlloc;
    }

    /**
     * Setter method for the morale allocation.
     * @param change int representing the amount to change the morale allocation
     */
    public void setMoraleAlloc(int change) {
        moraleAlloc += change;
        if(moraleAlloc < 0){
            moraleAlloc = 0;
        }
    }
}
