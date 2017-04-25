package model;

/**
 * Class to hold the location variables.
 * 
 * @author Connor D. Milligan
 */
public class Location {
    // The private state of the location
    private double huntingRate;
    private double gatheringRate;
    private double waterRate;
    private double clothingRate;
    private double storageRate;
    private double toolsRate;
    private double moraleRate;

    // Constant rate values based on the location
    public final double VERY_HIGH_RATE = 1.25;
    public final double HIGH_RATE = 1.00;
    public final double MEDIUM_RATE = 0.75;
    public final double LOW_RATE = 0.50;
    public final double VERY_LOW_RATE = 0.25;

    // Enum to hold the possible locations
    public enum Locations {
        FOREST,
        DESERT,
        TUNDRA,
        JUNGLE
    }

    /**
     * Location constructor.
     * @param location represents the ecosystem type.
     */
    public Location(Locations location){
        // Setup forest location
        if(location.equals(Locations.FOREST)){
            this.huntingRate = HIGH_RATE;
            this.gatheringRate = HIGH_RATE;
            this.waterRate = MEDIUM_RATE;
            this.clothingRate = HIGH_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = MEDIUM_RATE;
            this.moraleRate = HIGH_RATE;
            // Setup desert location
        } else if(location.equals(Locations.DESERT)) {
            this.huntingRate = MEDIUM_RATE;
            this.gatheringRate = LOW_RATE;
            this.waterRate = LOW_RATE;
            this.clothingRate = MEDIUM_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = HIGH_RATE;
            this.moraleRate = LOW_RATE;
            // Setup tundra location
        } else if(location.equals(Locations.TUNDRA)) {
            this.huntingRate = HIGH_RATE;
            this.gatheringRate = MEDIUM_RATE;
            this.waterRate = MEDIUM_RATE;
            this.clothingRate = MEDIUM_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = MEDIUM_RATE;
            this.moraleRate = MEDIUM_RATE;
            // Setup jungle location
        } else if(location.equals(Locations.JUNGLE)) {
            this.huntingRate = HIGH_RATE;
            this.gatheringRate = HIGH_RATE;
            this.waterRate = HIGH_RATE;
            this.clothingRate = HIGH_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = LOW_RATE;
            this.moraleRate = HIGH_RATE;
        }
    }

    public double getHuntingRate() {
        return huntingRate;
    }

    public double getGatheringRate() {
        return gatheringRate;
    }

    public double getWaterRate() {
        return waterRate;
    }

    public double getClothingRate() {
        return clothingRate;
    }

    public double getStorageRate() {
        return storageRate;
    }

    public double getToolsRate() {
        return toolsRate;
    }

    public double getMoraleRate() {
        return moraleRate;
    }

    @Override
    public String toString() {
        return "Location:" + "\n" +
                "\thuntingRate: " + huntingRate + "\n" +
                "\tgatheringRate: " + gatheringRate + "\n" +
                "\twaterRate: " + waterRate + "\n" +
                "\tclothingRate: " + clothingRate + "\n" +
                "\tstorageRate: " + storageRate + "\n" +
                "\ttoolsRate: " + toolsRate + "\n" +
                "\tmoraleRate: " + moraleRate + "\n";
    }
}
