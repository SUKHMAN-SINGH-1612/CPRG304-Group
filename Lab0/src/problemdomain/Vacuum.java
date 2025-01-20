package problemdomain;

public class Vacuum extends Appliance
{
	
	// Variables or attributes
	
	private String grade;
	private double batteryVoltage;

	// Getters and Setters
	
	public String getGrade()
	{
		return grade;
	}
	public double getBatteryVoltage()
	{
		return batteryVoltage;
	}
	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	public void setBatteryVoltage(double batteryVoltage)
	{
		this.batteryVoltage = batteryVoltage;
	}
	
	// Constructor
	
	public Vacuum(double itemNumber, String brand, double quantity, double wattage, String color, double price,
			String grade, double batteryVoltage)
	{
		super(itemNumber, brand, quantity, wattage, color, price);
		this.grade = grade;
		this.batteryVoltage = batteryVoltage;
	}
	
	// toString method
	@Override
	
	public String toString()
	{
        String voltageText;
        if (batteryVoltage == 18) 
        {
            voltageText = "Low";
        }
        else if (batteryVoltage == 24)
        {
            voltageText = "High";
        }
        else
        {
            voltageText = "Unknown";
        }

		return super.toString() + "\nGrade: " + grade + "\nBattery Voltage: " + voltageText + "\n";
	}
}
