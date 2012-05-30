package FractalFunctions;

public abstract class UnitFunction2D {

    protected double[] centre = {0.0D, 0.0D};
    protected double scale = 1.0D;

    public UnitFunction2D() {
    }

    public UnitFunction2D(double[] centre, double scale) {
        setCentre(centre);
        setScale(scale);
    }

    public void setCentre(double[] centre) {
        this.centre = centre;
    }

    public double[] getCentre() {
        return centre;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public abstract double getValue(double[] paramArrayOfDouble);

    public String getName() {
        return getClass().getName();
    }
}
