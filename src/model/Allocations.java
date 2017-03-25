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

    public static final int DEFAULT_ALLOC = 0;
    public static final int DECREMENT = -1;
    public static final int INCREMENT = 1;
    public static final String DECREMENT_BUTTON = "<";
    public static final String INCREMENT_BUTTON = ">";

    public static final String HUNTING_LABEL = "Hunting";
    public static final String GATHERING_LABEL = "Gathering";

    public Allocations(int huntingAlloc, int gatheringAlloc, int waterAlloc, int clothingAlloc, int storageAlloc, int toolsAlloc, int moraleAlloc) {
        this.huntingAlloc = huntingAlloc;
        this.gatheringAlloc = gatheringAlloc;
        this.waterAlloc = waterAlloc;
        this.clothingAlloc = clothingAlloc;
        this.storageAlloc = storageAlloc;
        this.toolsAlloc = toolsAlloc;
        this.moraleAlloc = moraleAlloc;
    }

    public Allocations() {
        this.huntingAlloc = DEFAULT_ALLOC;
        this.gatheringAlloc = DEFAULT_ALLOC;
        this.waterAlloc = DEFAULT_ALLOC;
        this.clothingAlloc = DEFAULT_ALLOC;
        this.storageAlloc = DEFAULT_ALLOC;
        this.toolsAlloc = DEFAULT_ALLOC;
        this.moraleAlloc = DEFAULT_ALLOC;
    }

    public int getHuntingAlloc() {
        return huntingAlloc;
    }

    public void setHuntingAlloc(int change) {
        huntingAlloc += change;
        if(huntingAlloc < 0){
            huntingAlloc = 0;
        }
    }

    public int getGatheringAlloc() {
        return gatheringAlloc;
    }

    public void setGatheringAlloc(int gatheringAlloc) {
        this.gatheringAlloc = gatheringAlloc;
    }

    public int getWaterAlloc() {
        return waterAlloc;
    }

    public void setWaterAlloc(int waterAlloc) {
        this.waterAlloc = waterAlloc;
    }

    public int getClothingAlloc() {
        return clothingAlloc;
    }

    public void setClothingAlloc(int clothingAlloc) {
        this.clothingAlloc = clothingAlloc;
    }

    public int getStorageAlloc() {
        return storageAlloc;
    }

    public void setStorageAlloc(int storageAlloc) {
        this.storageAlloc = storageAlloc;
    }

    public int getToolsAlloc() {
        return toolsAlloc;
    }

    public void setToolsAlloc(int toolsAlloc) {
        this.toolsAlloc = toolsAlloc;
    }

    public int getMoraleAlloc() {
        return moraleAlloc;
    }

    public void setMoraleAlloc(int moraleAlloc) {
        this.moraleAlloc = moraleAlloc;
    }
}
