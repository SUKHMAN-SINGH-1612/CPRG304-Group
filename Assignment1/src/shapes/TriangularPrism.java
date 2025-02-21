package shapes;

public class TriangularPrism
{
	double height;
	double side;
	
	public TriangularPrism(double height, double side)
	{
		this.height = height;
		this.side = side;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double calcBaseArea()
	{
		return Math.pow(side, 2) * Math.sqrt(3) / 4;
	}
	
	public double calcVolume()
	{
		return calcBaseArea() * height;
	}
	
	// To string method
	
	public String toString()
	{
		return "Triangular Prism: Height = " + height + ", Side = " + side;
	}
}
