package FractalFunctions;

public class FractalFunction2D {

    static final long MASK = 4294967295L;
    static final long SCATTER = 16807L;
    private int fractalDepth = 48;
    private int density = 4;
    private long index = 1L;
    private UnitFunction2D unitFunction;
    private RanQD1 ran;
    double[] pos1 = new double[2];
    long[] square1 = new long[2];
    int[] offset = new int[2];

    public FractalFunction2D(UnitFunction2D unitFunction, int fractalDepth, int density, long index) {
        this.unitFunction = unitFunction;
        this.fractalDepth = fractalDepth;
        this.density = density;
        this.index = index;
        ran = new RanQD1(index);
    }

    public FractalFunction2D(String unitFunctionName, int fractalDepth, int density, long index)
            throws Exception {
        unitFunction = ((UnitFunction2D) Class.forName(unitFunctionName).newInstance());
        this.fractalDepth = fractalDepth;
        this.density = density;
        this.index = index;
        ran = new RanQD1(index);
    }

    public FractalFunction2D(UnitFunction2D unitFunction, int density, long index) {
        this.unitFunction = unitFunction;
        this.density = density;
        this.index = index;
        ran = new RanQD1(index);
    }

    public FractalFunction2D(String unitFunctionName, int density, long index)
            throws Exception {
        unitFunction = ((UnitFunction2D) Class.forName(unitFunctionName).newInstance());
        this.density = density;
        this.index = index;
        ran = new RanQD1(index);
    }

    public double evaluate(double[] point) {
        point[0] %= 1.0D;
        point[1] %= 1.0D;
        if (point[0] <= 0.0D) {
            point[0] += 1.0D;
        }
        if (point[1] <= 0.0D) {
            point[1] += 1.0D;
        }
        return getDepthLocal(point, 1, index, 1L);
    }

    public double evaluate(double x, double y) {
        double[] point = {x, y};
        return evaluate(point);
    }

    private double getDepthLocal(double[] pos, int recDepth, long seed, long span) {
        double depth = 0.0D;
        double scale = 1.0D / span;
        long[] square = {(long) Math.ceil(pos[0] * span), (long) Math.ceil(pos[1] * span)};

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    pos1[k] = pos[k];
                    square1[k] = square[k];
                }
                offset[0] = i;
                offset[1] = j;
                translate(pos1, square1, offset, span);
                depth += getDepthWRTSquare(pos1, square1, recDepth, seed, span, scale);
            }
        }

        if (recDepth < fractalDepth) {
            long newSeed;
            if (recDepth < 16) {
                newSeed = seed + span * span & 0xFFFFFFFF;
            } else {
                newSeed = seed * 16807L & 0xFFFFFFFF;
            }
            long newSpan = span << 1;
            depth += getDepthLocal(pos, recDepth + 1, newSeed, newSpan);
        }
        return depth;
    }

    private void translate(double[] pos, long[] square, int[] offset, long span) {
        for (int i = 0; i <= 1; i++) {
            square[i] += offset[i];
            if (square[i] == 0L) {
                square[i] = span;
                pos[i] += 1.0D;
            } else if (square[i] > span) {
                square[i] = 1L;
                pos[i] -= 1.0D;
            }
        }
    }

    private double getDepthWRTSquare(double[] pos, long[] square, int recDepth, long seed, long span, double scale) {
        double depth = 0.0D;
        long squareSeed;
        if (recDepth <= 16) {
            squareSeed = (square[0] - 1L) * span + (square[1] - 1L);
        } else {
            squareSeed = (square[0] - 1L) * 16807L + (square[1] - 1L) & 0xFFFFFFFF;
        }
        long localSeed = seed + squareSeed;
        ran.setSeed(localSeed);
        int numUnits = ran.nextInt(0, 2 * density);
        for (int i = 1; i <= numUnits; i++) {
            double diameter = 1.0D / (2.0D - ran.nextDouble()) * scale;
            double[] centre = {(square[0] - ran.nextDouble()) * scale, (square[1] - ran.nextDouble()) * scale};
            if ((Math.abs(pos[0] - centre[0]) < diameter / 2.0D) && (Math.abs(pos[1] - centre[1]) < diameter / 2.0D)) {
                unitFunction.setScale(diameter);
                unitFunction.setCentre(centre);
                depth += unitFunction.getValue(pos);
            }
        }
        return depth;
    }
}
