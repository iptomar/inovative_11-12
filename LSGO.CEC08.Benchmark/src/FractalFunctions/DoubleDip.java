package FractalFunctions;

public class DoubleDip extends UnitFunction1D {

    public DoubleDip() {
    }

    public DoubleDip(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double depth = 0.0D;

        double x = (point - centre) / scale;
        if ((x > -0.5D) && (x < 0.5D)) {
            double xs = 4.0D * x * x;
            depth = (-96.0D * xs * xs * xs + 193.0D * xs * xs - 98.0D * xs + 1.0D) * scale;
        }
        return depth;
    }

    public double twist(double x, double y) {
        double dx = 0.0D;
        y %= 1.0D;
        double ys = y * y;

        dx = 4.0D * (ys * ys - 2.0D * ys * y + ys);
        return dx;
    }
}
