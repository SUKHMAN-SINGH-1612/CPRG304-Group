
package problemdomain;

/**
 * Class representing a Microwave appliance.
 * This class extends the Appliance class and includes additional attributes
 * specific to microwaves, such as capacity and room type.
 */
public class Microwave extends Appliance {

    // Variables or attributes
    private float capacity; // Capacity of the microwave
    private String roomType; // Room type where the microwave is used

    // Getters and Setters

    /**
     * Gets the capacity of the microwave.
     * @return the capacity
     */
    public float getCapacity() {
        return capacity;
    }

    /**
     * Gets the room type where the microwave is used.
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets the capacity of the microwave.
     * @param capacity the capacity to set
     */
    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the room type where the microwave is used.
     * @param roomType the room type to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Constructor

    /**
     * Constructor to initialize a microwave with the given attributes.
     * @param itemNumber the item number of the microwave
     * @param brand the brand of the microwave
     * @param quantity the quantity of the microwave in stock
     * @param wattage the wattage of the microwave
     * @param color the color of the microwave
     * @param price the price of the microwave
     * @param capacity the capacity of the microwave
     * @param roomType the room type where the microwave is used
     */
    public Microwave(long itemNumber, String brand, double quantity, double wattage, String color, double price,
                     float capacity, String roomType) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.capacity = capacity;
        this.roomType = roomType;
    }

    // toString method

    /**
     * Returns a string representation of the microwave.
     * @return a string representation of the microwave
     */
    @Override
    public String toString() {
        String typeText;
        if (roomType.equals("K"))
            typeText = "Kitchen";
        else if (roomType.equals("W"))
            typeText = "Work Site";
        else
            typeText = "Unknown";

        return super.toString() + "\nCapacity: " + capacity + "\nRoom Type: " + typeText + "\n";
    }
}
