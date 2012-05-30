package FractalFunctions;

public class Quadratic extends UnitFunction1D {

    public Quadratic() {
    }

    public Quadratic(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double depth = 0.0D;
        double radius = scale / 2.0D;
        double distx = Math.abs(point - centre);
        if (distx < radius) {
            double ratio = distx / radius;
            depth = (ratio * ratio - 1.0D) * scale;
        }
        return depth;
    }
}
