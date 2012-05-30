package FractalFunctions;

public class Cylinder extends UnitFunction2D {

    public Cylinder() {
    }

    public Cylinder(double[] centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double[] point) {
        double radius = scale / 2.0D;
        double radiusSq = radius * radius;
        double distx = StrictMath.abs(point[0] - centre[0]);
        double disty = StrictMath.abs(point[1] - centre[1]);
        double distSq = distx * distx + disty * disty;
        double value = 0.0D;
        if (distSq < radiusSq) {
            value = -1.0D;
        }
        return value;
    }
}
