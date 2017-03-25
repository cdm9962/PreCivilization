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

    public Allocations(int huntingAlloc, int gatheringAlloc, int waterAlloc, int clothingAlloc, int storageAlloc, int toolsAlloc, int moraleAlloc) {
        this.huntingAlloc = huntingAlloc;
        this.gatheringAlloc = gatheringAlloc;
        this.waterAlloc = waterAlloc;
        this.clothingAlloc = clothingAlloc;
        this.storageAlloc = storageAlloc;
        this.toolsAlloc = toolsAlloc;
        this.moraleAlloc = moraleAlloc;
    }

    public int getHuntingAlloc() {
        return huntingAlloc;
    }

    public void setHuntingAlloc(int huntingAlloc) {
        this.huntingAlloc = huntingAlloc;
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
