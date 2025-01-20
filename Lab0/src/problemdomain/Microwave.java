package problemdomain;

public class Microwave extends Appliance
{
	// Variables or attributes
	
	private float capacity;
	private String roomType;
	
	// Getters and Setters
	
	public float getCapacity()
	{
		return capacity;
	}
	public String getRoomType()
	{
		return roomType;
	}
	public void setCapacity(float capacity)
	{
		this.capacity = capacity;
	}
	public void setRoomType(String roomType)
	{
		this.roomType = roomType;
	}
	
	// Constructor
	
	public Microwave(double itemNumber, String brand, double quantity, double wattage, String color, double price,
			float capacity, String roomType)
	{
		super(itemNumber, brand, quantity, wattage, color, price);
		this.capacity = capacity;
		this.roomType = roomType;
	}
	
	// toString method
	@Override
	
	public String toString()
	{
		String typeText;
		if (roomType == "K")
		    typeText = "Kitchen";
		else if (roomType == "W")
		    typeText = "Work Site";
		else
		    typeText = "Unknown";
		
		return super.toString() + "\nCapacity: " + capacity + "\nRoom Type: " + typeText + "\n";
	}
}
