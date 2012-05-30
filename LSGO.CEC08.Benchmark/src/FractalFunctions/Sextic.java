package FractalFunctions;

public class Sextic extends UnitFunction1D {

    public Sextic() {
    }

    public Sextic(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double depth = 0.0D;

        double x = (point - centre) / scale;
        if ((x > -1.0D) && (x < 1.0D)) {
            double xs = 4.0D * x * x;

            depth = (-96.0D * xs * xs * xs + 193.0D * xs * xs - 98.0D * xs + 1.0D) * scale;
        }

        return depth;
    }
}