package shapes;

public class SquarePrism
{
	double height;
	double side;
	
	public SquarePrism(double height, double side)
	{
		this.height = height;
		this.side = side;
	}
	
	public double calcVolume()
	{
		return side * side * height;
	}
	
	public double calcBaseArea()
	{
		return side * side;
	}
	
	// To string method
	
	public String toString()
	{
		return "Square Prism: Height = " + height + ", Side = " + side;
	}
}
