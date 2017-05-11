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
    private Locations location;

    // Constant rate values based on the location
    public final double VERY_HIGH_RATE = 1.25;
    public final double HIGH_RATE = 1.00;
    public final double MEDIUM_RATE = 0.75;
    public final double LOW_RATE = 0.50;
    public final double VERY_LOW_RATE = 0.25;

    // Constant image files and their sources for the locations
    public final String FOREST_FILE = "/resources/forest.jpeg";
    public final String FORET_SOURCE = "https://static.pexels.com/photos/38136/pexels-photo-38136.jpeg";

    // Enum to hold the possible locations
    public enum Locations {
        Forest,
        Desert,
        Tundra,
        Jungle
    }

    /**
     * Location constructor.
     * @param location represents the ecosystem type.
     */
    public Location(Locations location){
        // Setup forest location
        if(location.equals(Locations.Forest)){
            this.huntingRate = HIGH_RATE;
            this.gatheringRate = HIGH_RATE;
            this.waterRate = MEDIUM_RATE;
            this.clothingRate = HIGH_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = MEDIUM_RATE;
            this.moraleRate = HIGH_RATE;
            this.location = Locations.Forest;
            // Setup desert location
        } else if(location.equals(Locations.Desert)) {
            this.huntingRate = MEDIUM_RATE;
            this.gatheringRate = LOW_RATE;
            this.waterRate = LOW_RATE;
            this.clothingRate = MEDIUM_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = HIGH_RATE;
            this.moraleRate = LOW_RATE;
            // Setup tundra location
        } else if(location.equals(Locations.Tundra)) {
            this.huntingRate = HIGH_RATE;
            this.gatheringRate = MEDIUM_RATE;
            this.waterRate = MEDIUM_RATE;
            this.clothingRate = MEDIUM_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = MEDIUM_RATE;
            this.moraleRate = MEDIUM_RATE;
            // Setup jungle location
        } else if(location.equals(Locations.Jungle)) {
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

    public double getWaterRate() { return waterRate; }

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
        return "\t\tHunting Rate: " + huntingRate + "\n" +
                "\t\tGathering Rate: " + gatheringRate + "\n" +
                "\t\tWater Rate: " + waterRate + "\n" +
                "\t\tClothing Rate: " + clothingRate + "\n" +
                "\t\tStorage Rate: " + storageRate + "\n" +
                "\t\tTools Rate: " + toolsRate + "\n" +
                "\t\tMorale Rate: " + moraleRate + "\n";
    }
}
