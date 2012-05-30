package FractalFunctions;

public class SmoothCylinder extends UnitFunction2D {

    private double dropPercent = 10.0D;

    public SmoothCylinder() {
    }

    public SmoothCylinder(double dropPercent) {
        this.dropPercent = dropPercent;
    }

    public SmoothCylinder(double[] centre, double scale) {
        super(centre, scale);
    }

    public SmoothCylinder(double[] centre, double scale, double dropPercent) {
        super(centre, scale);
        this.dropPercent = dropPercent;
    }

    public double getValue(double[] point) {
        double outerRadius = scale / 2.0D;
        double innerRadius = (100.0D - dropPercent) / 100.0D * outerRadius;
        double distx = StrictMath.abs(point[0] - centre[0]);
        double disty = StrictMath.abs(point[1] - centre[1]);
        double dist = StrictMath.sqrt(distx * distx + disty * disty);
        double value;
        if (dist >= outerRadius) {
            value = 0.0D;
        } else {
            if (dist <= innerRadius) {
                value = -1.0D;
            } else {
                value = -(0.5D + 0.5D * StrictMath.cos((dist - innerRadius) / (outerRadius - innerRadius) * 3.141592653589793D));
            }
        }
        return value;
    }
}