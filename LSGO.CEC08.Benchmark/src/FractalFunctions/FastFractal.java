package FractalFunctions;

public class FastFractal {

    private UnitFunction1D unitFunction;
    private int dimensions;
    private FractalFunction1D ff;

    public FastFractal(String unitFunctionName, int fractalDepth, int density, long index, int dimensions)
            throws Exception {
        unitFunction = ((UnitFunction1D) Class.forName(unitFunctionName).newInstance());
        this.dimensions = dimensions;
        ff = new FractalFunction1D(unitFunction, fractalDepth, density, index);
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
            double dx = unitFunction.twist(x, lastx);
            depth += ff.evaluate(x + dx);
            lastx = x;
        }
        return depth;
    }

    public double[] evaluate(double[][] points) {
        if (points[0].length != dimensions) {
            throw new RuntimeException("Point does not have " + dimensions + " dimensions.");
        }
        double[] results = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            results[i] = evaluate(points[i]);
        }
        return results;
    }
}
