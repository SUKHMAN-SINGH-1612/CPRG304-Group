package shapes;

import shapeManager.Shape;

public class OctagonalPrism extends Shape
{
	double height;
	double side;

	public OctagonalPrism(double height, double side)
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
		return 2 * (1 + Math.sqrt(2)) * Math.pow(side, 2);
	}

	public double calcVolume()
	{
		return calcBaseArea() * height;
	}

	// To string method

	public String toString()
	{
		return "Octagonal Prism: Height = " + height + ", Side = " + side;
	}
}
