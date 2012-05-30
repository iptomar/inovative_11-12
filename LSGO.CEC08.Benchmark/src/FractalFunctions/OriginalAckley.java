package FractalFunctions;

public class OriginalAckley {

    public static double getValue(double[] point) {
        int dim = point.length;
        double firstSum = 0.0D;
        for (int i = 0; i < dim; i++) {
            firstSum += point[i] * point[i];
        }
        double secondSum = 0.0D;
        for (int i = 0; i < dim; i++) {
            secondSum += Math.cos(6.283185307179586D * point[i]);
        }
        return -20.0D * Math.exp(-0.2D * Math.sqrt(1.0D / dim * firstSum)) - Math.exp(1.0D / dim * secondSum) + 20.0D + 2.718281828459045D;
    }
}
