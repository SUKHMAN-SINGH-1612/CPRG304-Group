
package problemdomain;

/**
 * Abstract class representing a general appliance.
 * This class provides common attributes and methods for all types of appliances.
 */
public abstract class Appliance {

    // Variables or attributes
    private long itemNumber; // Unique identifier for the appliance
    private String brand; // Brand of the appliance
    private double quantity; // Quantity of the appliance in stock
    private double wattage; // Wattage of the appliance
    private String color; // Color of the appliance
    private double price; // Price of the appliance

    // Getters and Setters

    /**
     * Gets the item number of the appliance.
     * @return the item number
     */
    public long getItemNumber() {
        return itemNumber;
    }

    /**
     * Gets the brand of the appliance.
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the quantity of the appliance in stock.
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Gets the wattage of the appliance.
     * @return the wattage
     */
    public double getWattage() {
        return wattage;
    }

    /**
     * Gets the color of the appliance.
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the price of the appliance.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the item number of the appliance.
     * @param itemNumber the item number to set
     */
    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Sets the brand of the appliance.
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the quantity of the appliance in stock.
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the wattage of the appliance.
     * @param wattage the wattage to set
     */
    public void setWattage(double wattage) {
        this.wattage = wattage;
    }

    /**
     * Sets the color of the appliance.
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets the price of the appliance.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    // Constructor

    /**
     * Constructor to initialize an appliance with the given attributes.
     * @param itemNumber the item number of the appliance
     * @param brand the brand of the appliance
     * @param quantity the quantity of the appliance in stock
     * @param wattage the wattage of the appliance
     * @param color the color of the appliance
     * @param price the price of the appliance
     */
    public Appliance(long itemNumber, String brand, double quantity, double wattage, String color, double price) {
        super();
        this.itemNumber = itemNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.wattage = wattage;
        this.color = color;
        this.price = price;
    }

    // toString method

    /**
     * Returns a string representation of the appliance.
     * @return a string representation of the appliance
     */
    @Override
    public String toString() {
        return "\nItemNumber: " + itemNumber + "\nBrand: " + brand + "\nQuantity: " + quantity + "\nWattage: " + wattage +
                "\nColor: " + color + "\nPrice: " + price;
    }
}
