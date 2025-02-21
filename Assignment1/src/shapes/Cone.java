package shapes;

public class Cone
{
	double height;
	double radius;
	
	public Cone(double height, double radius)
	{
		this.height = height;
		this.radius = radius;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double calcVolume()
	{
		return Math.PI * Math.pow(radius, 2) * height / 3;
	}
	
	public double calcBaseArea()
	{
		return Math.PI * Math.pow(radius, 2);
	}
	
	// To String method
	
	public String toString()
	{
		return "Cone with height " + height + " and radius " + radius;
	}
	
}
