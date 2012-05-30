package FractalFunctions;

public class DoubleCosine extends UnitFunction1D {

    public DoubleCosine() {
    }

    public DoubleCosine(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double distPi = scale / 2.0D;
        double distx = StrictMath.abs(point - centre);
        double value = 0.0D;
        if (distx < distPi) {
            value = (-0.5D + 0.5D * StrictMath.cos(distx / distPi * 2.0D * 3.141592653589793D)) * scale;
        }
        return value;
    }
}
