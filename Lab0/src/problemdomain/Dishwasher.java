
package problemdomain;

/**
 * Class representing a Dishwasher appliance.
 * This class extends the Appliance class and includes additional attributes
 * specific to dishwashers, such as feature and sound rating.
 */
public class Dishwasher extends Appliance {

    // Variables or attributes
    private String feature; // Special feature of the dishwasher
    private String soundRating; // Sound rating of the dishwasher

    // Getters and Setters

    /**
     * Gets the feature of the dishwasher.
     * @return the feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Gets the sound rating of the dishwasher.
     * @return the sound rating
     */
    public String getSoundRating() {
        return soundRating;
    }

    /**
     * Sets the feature of the dishwasher.
     * @param feature the feature to set
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * Sets the sound rating of the dishwasher.
     * @param soundRating the sound rating to set
     */
    public void setSoundRating(String soundRating) {
        this.soundRating = soundRating;
    }

    // Constructor

    /**
     * Constructor to initialize a dishwasher with the given attributes.
     * @param itemNumber the item number of the dishwasher
     * @param brand the brand of the dishwasher
     * @param quantity the quantity of the dishwasher in stock
     * @param wattage the wattage of the dishwasher
     * @param color the color of the dishwasher
     * @param price the price of the dishwasher
     * @param feature the special feature of the dishwasher
     * @param soundRating the sound rating of the dishwasher
     */
    public Dishwasher(long itemNumber, String brand, double quantity, double wattage, String color, double price,
                      String feature, String soundRating) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.feature = feature;
        this.soundRating = soundRating;
    }

    // toString method

    /**
     * Returns a string representation of the dishwasher.
     * @return a string representation of the dishwasher
     */
    @Override
    public String toString() {
        String soundRateText;
        if (soundRating.equals("Qt"))
            soundRateText = "Quietest";
        else if (soundRating.equals("Qr"))
            soundRateText = "Quieter";
        else if (soundRating.equals("Qu"))
            soundRateText = "Quiet";
        else if (soundRating.equals("M"))
            soundRateText = "Moderate";
        else
            soundRateText = "Unknown";

        return super.toString() + "\nFeatures: " + feature + "\nSoundRating: " + soundRateText + "\n";
    }
}
