package FractalFunctions;

public class Cosine extends UnitFunction1D {

    public Cosine() {
    }

    public Cosine(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double distPiOn2 = scale / 2.0D;
        double distx = StrictMath.abs(point - centre);
        double value = 0.0D;
        if (distx < distPiOn2) {
            value = (-0.5D - 0.5D * StrictMath.cos(distx / distPiOn2 * 3.141592653589793D)) * scale;
        }
        return value;
    }
}
