package shapes;

import appDomain.Shape;

public class PentagonalPrism extends Shape
{
	double height;
	double side;

	public PentagonalPrism(double height, double side)
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
		return 5 * Math.pow(side, 2) * Math.tan(Math.toRadians(54));
	}

	public double calcVolume()
	{
		return calcBaseArea() * height;
	}

	// To string method

	public String toString()
	{
		return "Pentagonal Prism: Height = " + height + ", Side = " + side;
	}
}
