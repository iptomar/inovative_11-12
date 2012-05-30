package FractalFunctions;

public class Rastrigin2D extends UnitFunction2D {

    public Rastrigin2D() {
    }

    public Rastrigin2D(double[] centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double[] point) {
        double halfWidth = scale / 2.0D;
        double distx = Math.abs(point[0] - centre[0]);
        double disty = Math.abs(point[1] - centre[1]);

        double[] scaledPoint = {distx / halfWidth * 5.12D, disty / halfWidth * 5.12D};

        double rast = (-6.4239D + OriginalRastrigin2D.getValue(scaledPoint) / 10.24D) * scale;
        double atten = new SmoothCylinder(centre, scale, 10.0D).getValue(point);
        return -atten * rast;
    }
}