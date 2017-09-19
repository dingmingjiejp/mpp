package lesson7.labs.prob2;

public class Ellipse implements ClosedCurve {
    private double semiMajorAxis;
    private double eccentricity;

    public Ellipse(double semiMajorAxis, double eccentricity) {
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    @Override
    public double computePerimeter() {
        return 4 * this.eccentricity * this.semiMajorAxis;
    }
}
