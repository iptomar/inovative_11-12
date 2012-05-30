package FractalFunctions;

public class SmoothRectangle extends UnitFunction1D {

    private double dropPercent = 10.0D;

    public SmoothRectangle() {
    }

    public SmoothRectangle(double dropPercent) {
        this.dropPercent = dropPercent;
    }

    public SmoothRectangle(double centre, double scale) {
        super(centre, scale);
    }

    public SmoothRectangle(double centre, double scale, double dropPercent) {
        super(centre, scale);
        this.dropPercent = dropPercent;
    }

    public double getValue(double point) {
        double outerRadius = scale / 2.0D;
        double innerRadius = (100.0D - dropPercent) / 100.0D * outerRadius;
        double dist = StrictMath.abs(point - centre);
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
