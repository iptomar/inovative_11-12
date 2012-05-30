package FractalFunctions;

public class OriginalRastrigin {

    public static double getValue(double[] point) {
        int dimensions = point.length;
        double value = 0.0D;
        for (int i = 0; i < dimensions; i++) {
            value += point[i] * point[i] - 10.0D * StrictMath.cos(6.283185307179586D * point[i]) + 10.0D;
        }

        return value;
    }
}