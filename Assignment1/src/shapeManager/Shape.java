package shapeManager;

public abstract class Shape implements Comparable<Shape>
{
	// Abstract methods to calculate base area, volume, and get height
	
	public abstract double calcBaseArea();
	public abstract double calcVolume();
	public abstract double getHeight();
	
	// Implement compareTo method for Comparable interface: compare by height
	@Override
	public int compareTo(Shape other) {
	    return Double.compare(this.getHeight(), other.getHeight());
	}
}


