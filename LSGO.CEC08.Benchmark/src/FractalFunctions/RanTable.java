package FractalFunctions;

public class RanTable {

    private double[] doubleTable;
    private int doubleTableIndex = 0;
    private int doubleTableSize;
    private int[] intTable;
    private int intTableIndex = 0;
    private int intTableSize;
    RanQD1 ran;

    public RanTable(int doubleTableSize, int intTableSize, int aveInt, long index) {
        this.doubleTableSize = doubleTableSize;
        this.intTableSize = intTableSize;
        ran = new RanQD1(index);

        doubleTable = new double[doubleTableSize];
        for (int i = 0; i < doubleTableSize; i++) {
            doubleTable[i] = ran.nextDouble();
        }
        ran.setSeed(index);

        intTable = new int[intTableSize];
        for (int i = 0; i < intTableSize; i++) {
            intTable[i] = ran.nextInt(0, 2 * aveInt);
        }
        ran.setSeed(index);
    }

    public void setSeed(long seed) {
        doubleTableIndex = (int) (seed & doubleTableSize - 1);
        intTableIndex = (int) (seed & intTableSize - 1);
    }

    public double nextDouble() {
        doubleTableIndex = (doubleTableIndex + 1 & doubleTableSize - 1);
        return doubleTable[doubleTableIndex];
    }

    public int nextInteger() {
        intTableIndex = (intTableIndex + 1 & intTableSize - 1);
        return intTable[intTableIndex];
    }
}
