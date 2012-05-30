package FractalFunctions;

public class OriginalRastrigin2D {

    public static double getValue(double[] point) {
        double value = 0.0D;
        for (int i = 0; i < 2; i++) {
            value += point[i] * point[i] - 10.0D * StrictMath.cos(6.283185307179586D * point[i]) + 10.0D;
        }

        return value;
    }
}
