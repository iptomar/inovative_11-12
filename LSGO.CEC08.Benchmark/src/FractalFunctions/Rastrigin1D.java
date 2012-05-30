package FractalFunctions;

public class Rastrigin1D extends UnitFunction1D {

    public Rastrigin1D() {
    }

    public Rastrigin1D(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double value = 0.0D;
        double distx = Math.abs(point - centre);
        double halfWidth = scale / 2.0D;
        if (distx < halfWidth) {
            double[] scaledPoint = {distx / halfWidth * 4.522994688D};
            value = (-3.940750995492086D + OriginalRastrigin.getValue(scaledPoint) / 10.24D) * scale;
        }
        return value;
    }

    public double twist(double x, double y) {
        double dx = 0.0D;
        y %= 1.0D;
        double ys = y * y;
        dx = 2.0D * (32.0D * ys * ys * ys - 96.0D * ys * ys * y + 105.0D * ys * ys - 50.0D * ys * y + 9.0D * ys);
        return dx;
    }
}
