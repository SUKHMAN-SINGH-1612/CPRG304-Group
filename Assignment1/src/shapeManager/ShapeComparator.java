package shapeManager;

import java.util.Comparator;
import shapeManager.Shape;

public class ShapeComparator implements Comparator<Shape> {
    private String comparisonType;

    public ShapeComparator(String comparisonType) {
        this.comparisonType = comparisonType.toLowerCase();
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        switch (comparisonType) {
            case "a": // Base area (descending)
                return Double.compare(shape2.calcBaseArea(), shape1.calcBaseArea());
            case "v": // Volume (descending)
                return Double.compare(shape2.calcVolume(), shape1.calcVolume());
            case "h": // Height (delegate to Comparable)
                return shape1.compareTo(shape2); // Uses Shape's compareTo()
            default:
                throw new IllegalArgumentException("Invalid comparison type: " + comparisonType);
        }
    }
}