package problemdomain;

public class Refrigerator extends Appliance
{

	// Variables or attributes
	
	private double numberOfDoors;
	private double height;
	private double width;

	
	// Getters and Setters
	
	public double getNumberOfDoors()
	{
		return numberOfDoors;
	}
	public double getHeight()
	{
		return height;
	}
	public double getWidth()
	{
		return width;
	}
	public void setNumberOfDoors(double numberOfDoors)
	{
		this.numberOfDoors = numberOfDoors;
	}
	public void setHeight(double height)
	{
		this.height = height;
	}
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	// Constructor
	
	public Refrigerator(long itemNumber, String brand, double quantity, double wattage, String color, double price,
			double numberOfDoors, double height, double width)
	{
		super(itemNumber, brand, quantity, wattage, color, price);
		this.numberOfDoors = numberOfDoors;
		this.height = height;
		this.width = width;
	}
	
	// toString method
	@Override
	public String toString()
	{
		String doorsText;
        if (numberOfDoors == 2)
            doorsText = "Double Doors";
        else if (numberOfDoors == 3)
            doorsText = "Three Doors";
        else if (numberOfDoors == 4)
            doorsText = "Four Door";
        else
            doorsText = "Unknown";
        
		return super.toString() + "\nNumber of Doors: " + doorsText + "\nHeight: " + height + "\nWidth: " + width
				+ "\n";
	}
	
}
