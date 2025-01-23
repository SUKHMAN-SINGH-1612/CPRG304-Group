
package problemdomain;

/**
 * Class representing a Refrigerator appliance.
 * This class extends the Appliance class and includes additional attributes
 * specific to refrigerators, such as number of doors, height, and width.
 */
public class Refrigerator extends Appliance {

    // Variables or attributes
    private double numberOfDoors; // Number of doors of the refrigerator
    private double height; // Height of the refrigerator
    private double width; // Width of the refrigerator

    // Getters and Setters

    /**
     * Gets the number of doors of the refrigerator.
     * @return the number of doors
     */
    public double getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * Gets the height of the refrigerator.
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the width of the refrigerator.
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the number of doors of the refrigerator.
     * @param numberOfDoors the number of doors to set
     */
    public void setNumberOfDoors(double numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Sets the height of the refrigerator.
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Sets the width of the refrigerator.
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    // Constructor

    /**
     * Constructor to initialize a refrigerator with the given attributes.
     * @param itemNumber the item number of the refrigerator
     * @param brand the brand of the refrigerator
     * @param quantity the quantity of the refrigerator in stock
     * @param wattage the wattage of the refrigerator
     * @param color the color of the refrigerator
     * @param price the price of the refrigerator
     * @param numberOfDoors the number of doors of the refrigerator
     * @param height the height of the refrigerator
     * @param width the width of the refrigerator
     */
    public Refrigerator(long itemNumber, String brand, double quantity, double wattage, String color, double price,
                        double numberOfDoors, double height, double width) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.numberOfDoors = numberOfDoors;
        this.height = height;
        this.width = width;
    }

    // toString method

    /**
     * Returns a string representation of the refrigerator.
     * @return a string representation of the refrigerator
     */
    @Override
    public String toString() {
        String doorsText;
        if (numberOfDoors == 2)
            doorsText = "Double Doors";
        else if (numberOfDoors == 3)
            doorsText = "Three Doors";
        else if (numberOfDoors == 4)
            doorsText = "Four Door";
        else
            doorsText = "Unknown";

        return super.toString() + "\nNumber of Doors: " + doorsText + "\nHeight: " + height + "\nWidth: " + width + "\n";
    }
}
