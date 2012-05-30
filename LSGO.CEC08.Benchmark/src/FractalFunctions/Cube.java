package FractalFunctions;

public class Cube extends UnitFunction2D {

    public Cube() {
    }

    public Cube(double[] centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double[] point) {
        double halfWidth = scale / 2.0D;
        double distx = Math.abs(point[0] - centre[0]);
        double disty = Math.abs(point[1] - centre[1]);
        double value = 0.0D;
        if ((distx < halfWidth) && (disty < halfWidth)) {
            value = -halfWidth;
        }
        return value;
    }
}
