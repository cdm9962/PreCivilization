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
        if(location.equals(Locations.FOREST)){
            this.huntingRate = HIGH_RATE;
            this.gatheringRate = HIGH_RATE;
            this.waterRate = MEDIUM_RATE;
            this.clothingRate = HIGH_RATE;
            this.storageRate = MEDIUM_RATE;
            this.toolsRate = MEDIUM_RATE;
            this.moraleRate = HIGH_RATE;
        }
    }

}
