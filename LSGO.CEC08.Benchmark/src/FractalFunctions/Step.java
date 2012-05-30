package FractalFunctions;

public class Step extends UnitFunction1D {

    public Step() {
    }

    public Step(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double halfWidth = scale / 2.0D;
        double distx = point - centre;
        double value = 0.0D;
        if ((distx < halfWidth) && (distx > 0.0D)) {
            value = -halfWidth;
        }
        return value;
    }
}