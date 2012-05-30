package FractalFunctions;

public class Sphere extends UnitFunction2D {

    public Sphere() {
    }

    public Sphere(double[] centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double[] point) {
        double radius = scale / 2.0D;
        double radsq = radius * radius;
        double distx = Math.abs(point[0] - centre[0]);
        double disty = Math.abs(point[1] - centre[1]);
        double distsq = distx * distx + disty * disty;
        double value = 0.0D;
        if (distsq < radsq) {
            value = -Math.sqrt(radsq - distsq);
        }
        return value;
    }
}