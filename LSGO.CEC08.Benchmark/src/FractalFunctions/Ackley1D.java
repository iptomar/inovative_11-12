package FractalFunctions;

public class Ackley1D extends UnitFunction1D {

    public Ackley1D() {
    }

    public Ackley1D(double centre, double scale) {
        super(centre, scale);
    }

    public double getValue(double point) {
        double value = 0.0D;
        double distx = Math.abs(point - centre);
        double halfWidth = scale / 2.0D;
        if (distx < halfWidth) {
            double[] scaledPoint = {distx / halfWidth * 32.0D};
            double ack = (-0.34865122100307D + OriginalAckley.getValue(scaledPoint) / 64.0D) * scale;
            double atten = new SmoothRectangle(centre, scale, 40.0D).getValue(point);
            value = -atten * ack;
        }
        return value;
    }
}
