package FractalFunctions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PlotUtilities {

    File dir;
    PrintWriter out;

    public PlotUtilities(String dataDir) {
        dir = new File(dataDir);
        System.out.println("Plot utility created for directory: " + dir.getPath());
    }

    public PlotUtilities() {
        dir = new File(".");
        System.out.println("Writing to " + dir.getPath());
    }

    private void setPrintWriter(String filename) throws IOException {
        File target = new File(dir, filename);
        target.createNewFile();
        out = new PrintWriter(new BufferedWriter(new FileWriter(target)));
    }

    public void plotUnitFunction1D(String unitFunctionName, int intervals)
            throws Exception {
        plotUnitFunction1D(unitFunctionName, 1, intervals);
    }

    public void plotUnitFunction1D(String unitFunctionName, int magnification, int intervals)
            throws Exception {
        UnitFunction1D unitFunction = (UnitFunction1D) Class.forName(unitFunctionName).newInstance();
        double scale = 1.0D / magnification;
        double centre = 0.5D * scale;
        unitFunction.setCentre(centre);
        unitFunction.setScale(scale);
        int totalProbes = intervals + 1;
        String filename = unitFunction.getName() + "-" + totalProbes + ".txt";
        setPrintWriter(filename);
        System.out.println("Writing " + filename);
        double step = 1.0D * scale / intervals;
        for (int x = 0; x <= intervals; x++) {
            double pos = x * step;
            double depth = unitFunction.getValue(pos);
            out.print(depth + " ");
        }
        out.println();
        out.close();
    }

    public void plotUnitFunction2D(String unitFunctionName, int intervals)
            throws Exception {
        plotUnitFunction2D(unitFunctionName, 1, intervals);
    }

    public void plotUnitFunction2D(String unitFunctionName, int magnification, int intervals)
            throws Exception {
        UnitFunction2D unitFunction = (UnitFunction2D) Class.forName(unitFunctionName).newInstance();
        double scale = 1.0D / magnification;
        double[] centre = {0.5D * scale, 0.5D * scale};
        unitFunction.setCentre(centre);
        unitFunction.setScale(scale);
        int totalProbes = intervals + 1;
        String filename = unitFunction.getName() + "-" + totalProbes + "x" + totalProbes + ".txt";
        setPrintWriter(filename);
        System.out.println("Writing " + filename);
        double step = 1.0D * scale / intervals;
        for (int y = 0; y <= intervals; y++) {
            for (int x = 0; x <= intervals; x++) {
                double[] pos = {x * step, y * step};
                double depth = unitFunction.getValue(pos);
                out.print(depth + " ");
            }
            out.println();
        }
        out.close();
    }

    public void plotFractal1D(String unitFunctionName, int fractalDepth, int density, long first, long last, int intervals)
            throws Exception {
        for (long index = first; index <= last; index += 1L) {
            plotFractal1D(unitFunctionName, fractalDepth, density, index, 1, 0.0D, intervals);
        }
    }

    public void plotFractal1D(String unitFunctionName, int fractalDepth, int density, long index, int magnification, double xOffset, int intervals)
            throws Exception {
        UnitFunction1D unitFunction = (UnitFunction1D) Class.forName(unitFunctionName).newInstance();
        int totalProbes = intervals + 1;
        FractalFunction1D ff = new FractalFunction1D(unitFunction, fractalDepth, density, index);
        String name = "Fractal_" + unitFunction.getName() + "_F" + fractalDepth + "D" + density + "N" + index;
        String filename = name + "x" + magnification + "-" + totalProbes + ".txt";
        setPrintWriter(filename);
        System.out.print("Writing " + filename + " ");
        double step = 1.0D / magnification / intervals;
        for (int x = 0; x <= intervals; x++) {
            double pos = xOffset + x * step;
            double depth = ff.evaluate(pos);
            out.print(depth + " ");
        }
        out.println();
        out.close();
        System.out.println("done.");
    }

    public void plotFractal2D(String unitFunctionName, int fractalDepth, int density, long first, long last, int intervals)
            throws Exception {
        for (long index = first; index <= last; index += 1L) {
            plotFractal2D(unitFunctionName, fractalDepth, density, index, 1, 0.0D, 0.0D, intervals);
        }
    }

    public void plotFractal2D(String unitFunctionName, int fractalDepth, int density, long index, int magnification, double xOffset, double yOffset, int intervals)
            throws Exception {
        UnitFunction2D unitFunction = (UnitFunction2D) Class.forName(unitFunctionName).newInstance();
        int totalProbes = intervals + 1;
        int bips = Math.round(intervals / 10);
        FractalFunction2D ff = new FractalFunction2D(unitFunction, fractalDepth, density, index);
        String name = "Fractal_" + unitFunction.getName() + "_F" + fractalDepth + "D" + density + "N" + index;
        String filename = name + "x" + magnification + "-" + totalProbes + "x" + totalProbes + ".txt";
        setPrintWriter(filename);
        System.out.print("Writing " + filename + " ");
        double step = 1.0D / magnification / intervals;
        for (int y = 0; y <= intervals; y++) {
            for (int x = 0; x <= intervals; x++) {
                double[] pos = {xOffset + x * step, yOffset + y * step};
                double depth = ff.evaluate(pos);
                out.print(depth + " ");
            }
            out.println();
            if ((bips <= 0) || (y % bips != 0)) {
                continue;
            }
            System.out.print(".");
        }
        out.close();
        System.out.println("done.");
    }

    public void plotFastFractal(String unitFunctionName, int fractalDepth, int density, long first, long last, int intervals)
            throws Exception {
        for (long index = first; index <= last; index += 1L) {
            plotFastFractal(unitFunctionName, fractalDepth, density, index, 1, 0.0D, 0.0D, intervals);
        }
    }

    public void plotFastFractal(String unitFunctionName, int fractalDepth, int density, long index, int magnification, double xOffset, double yOffset, int intervals)
            throws Exception {
        int dimension = 2;
        int totalProbes = intervals + 1;
        FastFractal ff = new FastFractal(unitFunctionName, fractalDepth, density, index, dimension);
        String name = "FastFractal_" + unitFunctionName + "_F" + fractalDepth + "D" + density + "N" + index;
        String filename = name + "x" + magnification + "-" + totalProbes + "x" + totalProbes + ".txt";
        setPrintWriter(filename);
        System.out.print("Writing " + filename + " ");
        double step = 1.0D / magnification / intervals;
        for (int y = 0; y <= intervals; y++) {
            for (int x = 0; x <= intervals; x++) {
                double[] pos = {xOffset + x * step, yOffset + y * step};
                double depth = ff.evaluate(pos);
                out.print(depth + " ");
            }
            out.println();
        }
        out.close();
        System.out.println("done.");
    }
}