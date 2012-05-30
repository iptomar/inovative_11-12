package FractalFunctions;

public class RanQD1 {

    static final long MASK = 4294967295L;
    static final double MAX_INT = 4294967295.0D;
    static final long A = 1664525L;
    static final long C = 1013904223L;
    private long idum;

    public RanQD1(long seed) {
        idum = seed;
        nextLong();
    }

    public void setSeed(long seed) {
        idum = seed;
        nextLong();
    }

    public long nextLong() {
        idum = (1664525L * idum + 1013904223L & 0xFFFFFFFF);
        return idum;
    }

    public double nextDouble() {
        return nextLong() / 4294967295.0D;
    }

    public int nextInt(int min, int max) {
        return min + (int) Math.floor(nextDouble() * (max - min + 1));
    }
}
