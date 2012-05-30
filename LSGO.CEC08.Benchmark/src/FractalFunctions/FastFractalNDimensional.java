package FractalFunctions;

public class FastFractalNDimensional {

    private UnitFunction1D unitFunction;
    private int dimensions;
    private long index;
    private FractalFunction1D ff;

    public FastFractalNDimensional(String unitFunctionName, int dimensions, int maxRecDepth, int density, long index)
            throws Exception {
        unitFunction = ((UnitFunction1D) Class.forName(unitFunctionName).newInstance());
        this.dimensions = dimensions;
        this.index = index;

        ff = new FractalFunction1D(unitFunction, maxRecDepth, density, index);
    }

    public double evaluate(double[] point) {
        if (point.length != dimensions) {
            throw new RuntimeException("Point does not have " + dimensions + " dimensions.");
        }
        double depth = 0.0D;

        ff.setIndex(6 * dimensions - 1 + 1);
        double lastx = point[(dimensions - 1)];
        for (int i = 0; i < dimensions; i++) {
            ff.setIndex(6 * i + 1);
            double x = point[i];
            double dx = 4.0D * (lastx * lastx * lastx * lastx - 2.0D * lastx * lastx * lastx + lastx * lastx);
            depth += ff.evaluate(x + dx);
            lastx = x;
        }
        return depth;
    }
}
