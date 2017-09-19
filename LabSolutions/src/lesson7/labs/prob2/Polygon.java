package lesson7.labs.prob2;

import java.util.Arrays;

public interface Polygon extends ClosedCurve{

    double[] getSides();

    default double computePerimeter() {
        return Arrays.stream(getSides()).sum();
    };
}
