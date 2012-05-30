package FractalFunctions;

public class FractalFunction1D {

    static final int DOUBLE_TABLE_SIZE = 16384;
    static final int INT_TABLE_SIZE = 256;
    private RanTable ranTable;
    private int fractalDepth = 3;
    private int density = 1;
    private long index = 1L;
    private UnitFunction1D unitFunction;

    public FractalFunction1D(UnitFunction1D unitFunction, int fractalDepth, int density, long index) {
        this.unitFunction = unitFunction;
        this.fractalDepth = fractalDepth;
        this.density = density;
        this.index = index;
        ranTable = new RanTable(16384, 256, density, index);
    }

    public FractalFunction1D(UnitFunction1D unitFunction, int density, long index) {
        this.unitFunction = unitFunction;
        this.density = density;
        this.index = index;
    }

    public FractalFunction1D(UnitFunction1D unitFunction, long index) {
        this.unitFunction = unitFunction;
        this.index = index;
    }

    public FractalFunction1D(UnitFunction1D unitFunction) {
        this.unitFunction = unitFunction;
    }

    public FractalFunction1D() {
        unitFunction = new DoubleDip();
    }

    public void setIndex(long index) {
        this.index = index;
        ranTable.setSeed(index);
    }

    public double evaluate(double x) {
        x %= 1.0D;

        if (x <= 0.0D) {
            x += 1.0D;
        }
        if (fractalDepth < 1) {
            return 0.0D;
        }
        return getDepthLocal(x, 1, index, 1L);
    }

    private double getDepthLocal(double x, int recDepth, long seed, long span) {
        double depth = 0.0D;
        double scale = 1.0D / span;
        long square = (long) Math.ceil(x * span);

        for (int offset = -1; offset < 2; offset++) {
            double x1 = x;
            long square1 = square + offset;
            if (square1 == 0L) {
                square1 = span;
                x1 += 1.0D;
            } else if (square1 > span) {
                square1 = 1L;
                x1 -= 1.0D;
            }
            depth += getDepthWRTSquare(x1, square1, recDepth, seed, span, scale);
        }

        if (recDepth < fractalDepth) {
            long newSeed = span + seed & 0x3FFF;
            long newSpan = span << 1;
            depth += getDepthLocal(x, recDepth + 1, newSeed, newSpan);
        }
        return depth;
    }

    private double getDepthWRTSquare(double x, long square, int recDepth, long seed, long span, double scale) {
        double depth = 0.0D;
        long squareSeed = square - 1L;
        long localSeed = seed + squareSeed & 0x3FFF;
        ranTable.setSeed(localSeed);
        int numUnits = ranTable.nextInteger();
        for (int i = 1; i <= numUnits; i++) {
            double diameter = 1.0D / (2.0D - ranTable.nextDouble()) * scale;
            double centre = (square - ranTable.nextDouble()) * scale;
            if ((x - centre) * (x - centre) < diameter * diameter / 4.0D) {
                unitFunction.setCentre(centre);
                unitFunction.setScale(diameter);
                depth += unitFunction.getValue(x);
            }
        }
        return depth;
    }
}
