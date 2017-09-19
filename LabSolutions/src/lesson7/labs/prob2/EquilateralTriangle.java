package lesson7.labs.prob2;

public class EquilateralTriangle implements Polygon {

    private double sideLength;
    public EquilateralTriangle(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double[] getSides() {
        return new double[] {sideLength, sideLength, sideLength};
    }
}
