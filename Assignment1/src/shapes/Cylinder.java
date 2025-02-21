package shapes;

import appDomain.Shape;

public class Cylinder extends Shape
{
	double height;
	double radius;

	public Cylinder(double height, double radius)
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
		return Math.PI * Math.pow(radius, 2) * height;
	}

	public double calcBaseArea()
	{
		return Math.PI * Math.pow(radius, 2);
	}

	// To String method

	public String toString()
	{
		return "Cylinder with height " + height + " and radius " + radius;
	}
}
