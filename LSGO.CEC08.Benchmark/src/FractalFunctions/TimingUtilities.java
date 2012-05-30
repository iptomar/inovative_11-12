package FractalFunctions;

import java.util.Arrays;

public class TimingUtilities {

    long start;
    long stop;

    public void timeFractal1D(String unitFunctionName, int fractalDepth, int density, int index)
            throws Exception {
        UnitFunction1D unitFunction = (UnitFunction1D) Class.forName(unitFunctionName).newInstance();
        FractalFunction1D ff = new FractalFunction1D(unitFunction, fractalDepth, density, index);
        int intervals = 1000000;
        System.out.println("Timing " + unitFunctionName + "_F" + fractalDepth + "D" + density + "N" + index + ": " + intervals + " evalutations.");
        double step = 1.0D / intervals;
        start = System.currentTimeMillis();
        double depth;
        for (int x = 0; x < intervals; x++) {
            double pos = x * step;
            depth = ff.evaluate(pos);
        }
        stop = System.currentTimeMillis();
        System.out.println("Time per evaluation: " + 1.0D * (stop - start) / intervals + " milliseconds.\n");
    }

    public void timeFractal2D(String unitFunctionName, int fractalDepth, int density, int index) throws Exception {
        UnitFunction2D unitFunction = (UnitFunction2D) Class.forName(unitFunctionName).newInstance();
        FractalFunction2D ff = new FractalFunction2D(unitFunction, fractalDepth, density, index);
        int intervals = 100;
        int totalEvals = intervals * intervals;
        System.out.println("Timing " + unitFunctionName + "_F" + fractalDepth + "D" + density + "N" + index + ": " + totalEvals + " evalutations.");
        double step = 1.0D / intervals;
        start = System.currentTimeMillis();
        for (int y = 0; y < intervals; y++) {
            double depth;
            for (int x = 0; x < intervals; x++) {
                double[] pos = {x * step, y * step};
                depth = ff.evaluate(pos);
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Time per evaluation: " + 1.0D * (stop - start) / totalEvals + " milliseconds.\n");
    }

    public void timeFastFractal(String unitFunctionName, int fractalDepth, int density, long index, int dimensions) throws Exception {
        FastFractal ff = new FastFractal(unitFunctionName, fractalDepth, density, index, dimensions);
        int intervals = 1000;
        double step = 1.0D / intervals;
        double[][] points = new double[intervals][dimensions];
        for (int i = 0; i < intervals; i++) {
            Arrays.fill(points[i], i * step);
        }
        System.out.println("Timing " + unitFunctionName + "_F" + fractalDepth + "D" + density + "N" + index + ": " + dimensions + " dimensions, " + intervals + " evalutations.");
        start = System.currentTimeMillis();
        double depth;
        for (int x = 0; x < intervals; x++) {
            depth = ff.evaluate(points[x]);
        }
        stop = System.currentTimeMillis();
        System.out.println("Time per evaluation: " + 1.0D * (stop - start) / intervals + " milliseconds.\n");
    }
}