package shapes;

public class Pyramid
{
	double height;
	double side;
	
	public Pyramid(double height, double side)
	{
		this.height = height;
		this.side = side;
	}
	
	public double calcVolume()
	{
		return Math.pow(side, 2) * height / 3;
	}
	
	public double calcBaseArea()
	{
		return Math.pow(side, 2);
	}
	
	// To String method
	
	public String toString()
	{
		return "Pyramid with height " + height + " and side " + side;
	}
}
