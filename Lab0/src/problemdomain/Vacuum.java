
package problemdomain;

/**
 * Class representing a Vacuum appliance.
 * This class extends the Appliance class and includes additional attributes
 * specific to vacuums, such as grade and battery voltage.
 */
public class Vacuum extends Appliance {

    // Variables or attributes
    private String grade; // Grade of the vacuum
    private double batteryVoltage; // Battery voltage of the vacuum

    // Getters and Setters

    /**
     * Gets the grade of the vacuum.
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Gets the battery voltage of the vacuum.
     * @return the battery voltage
     */
    public double getBatteryVoltage() {
        return batteryVoltage;
    }

    /**
     * Sets the grade of the vacuum.
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Sets the battery voltage of the vacuum.
     * @param batteryVoltage the battery voltage to set
     */
    public void setBatteryVoltage(double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    // Constructor

    /**
     * Constructor to initialize a vacuum with the given attributes.
     * @param itemNumber the item number of the vacuum
     * @param brand the brand of the vacuum
     * @param quantity the quantity of the vacuum in stock
     * @param wattage the wattage of the vacuum
     * @param color the color of the vacuum
     * @param price the price of the vacuum
     * @param grade the grade of the vacuum
     * @param batteryVoltage the battery voltage of the vacuum
     */
    public Vacuum(long itemNumber, String brand, double quantity, double wattage, String color, double price,
                  String grade, double batteryVoltage) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.grade = grade;
        this.batteryVoltage = batteryVoltage;
    }

    // toString method

    /**
     * Returns a string representation of the vacuum.
     * @return a string representation of the vacuum
     */
    @Override
    public String toString() {
        String voltageText;
        if (batteryVoltage == 18) {
            voltageText = "Low";
        } else if (batteryVoltage == 24) {
            voltageText = "High";
        } else {
            voltageText = "Unknown";
        }

        return super.toString() + "\nGrade: " + grade + "\nBattery Voltage: " + voltageText + "\n";
    }
}
