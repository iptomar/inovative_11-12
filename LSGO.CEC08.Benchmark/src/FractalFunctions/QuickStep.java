package FractalFunctions;

public class QuickStep extends UnitFunction1D {

    public QuickStep() {
    }

    public QuickStep(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        if ((point > centre) && (point - centre < scale / 4.0D)) {
            return -scale;
        }
        return 0.0D;
    }

    public double twist(double x, double y) {
        double dx = 0.0D;
        double mody = y % 1.0D;
        if ((mody > 0.5D) && (mody <= 0.8D)) {
            dx = -0.3D;
        } else if ((mody <= 0.5D) && (mody > 0.3D)) {
            dx = 0.4D;
        }
        return dx;
    }
}