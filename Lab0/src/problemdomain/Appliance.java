package problemdomain;

public abstract class Appliance
{
	
	// Variables or attributes
	
	private long itemNumber;
	private String brand;
	private double quantity;
	private double wattage;
	private String color;
	private double price;
	
	// Getters and Setters
	
	public long getItemNumber()
	{
		return itemNumber;
	}
	public String getBrand()
	{
		return brand;
	}
	public double getQuantity()
	{
		return quantity;
	}
	public double getWattage()
	{
		return wattage;
	}
	public String getColor()
	{
		return color;
	}
	public double getPrice()
	{
		return price;
	}
	public void setItemNumber(long itemNumber)
	{
		this.itemNumber = itemNumber;
	}
	public void setBrand(String brand)
	{
		this.brand = brand;
	}
	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
	}
	public void setWattage(double wattage)
	{
		this.wattage = wattage;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	// Constructor
	
	public Appliance(long itemNumber, String brand, double quantity, double wattage, String color, double price)
	{
		super();
		this.itemNumber = itemNumber;
		this.brand = brand;
		this.quantity = quantity;
		this.wattage = wattage;
		this.color = color;
		this.price = price;
	}
	
	// toString method
	
	@Override
	public String toString()
	{
		return "\nItemNumber: " + itemNumber + "\nBrand: " + brand + "\nQuantity: " + quantity + "\nWattage: " + wattage +
                "\nColor: " + color + "\nPrice: " + price;
	}
	
}
