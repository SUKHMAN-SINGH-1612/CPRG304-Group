package appDomain;

import java.util.Comparator;

public class ShapeComparator implements Comparator<Shape> {
    private String comparisonType;

    public ShapeComparator(String comparisonType) {
        this.comparisonType = comparisonType;
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        switch (comparisonType) {
            case "a": // Compare by base area
                return Double.compare(shape1.calcBaseArea(), shape2.calcBaseArea());
            case "v": // Compare by volume
                return Double.compare(shape1.calcVolume(), shape2.calcVolume());
            default:
                throw new IllegalArgumentException("Invalid comparison type");
        }
    }
}
