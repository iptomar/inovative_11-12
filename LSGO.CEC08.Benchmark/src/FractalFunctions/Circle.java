package FractalFunctions;

public class Circle extends UnitFunction1D {

    public Circle() {
    }

    public Circle(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double radius = scale / 2.0D;
        double radsq = radius * radius;
        double distx = Math.abs(point - centre);
        double distxSq = distx * distx;
        double value = 0.0D;
        if (distxSq < radsq) {
            value = -Math.sqrt(radsq - distxSq);
        }
        return value;
    }
}
