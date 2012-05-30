package FractalFunctions;

public abstract class UnitFunction1D {

    protected double centre = 0.0D;
    protected double scale = 1.0D;

    public UnitFunction1D() {
    }

    public UnitFunction1D(double centre, double scale) {
        setCentre(centre);
        setScale(scale);
    }

    public void setParams(double centre, double scale) {
        this.centre = centre;
        this.scale = scale;
    }

    public void setCentre(double centre) {
        this.centre = centre;
    }

    public double getCentre() {
        return centre;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public abstract double getValue(double paramDouble);

    public double twist(double x, double y) {
        return 0.0D;
    }

    public String getName() {
        return getClass().getName();
    }
}