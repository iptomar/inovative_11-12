package FractalFunctions;

public class Ran0 {

    private static final long IA_DEFAULT_BENCHMARKING = 69621L;
    private static final long IA_DEFAULT_TRAINING = 16807L;
    private static final long IA_ALTERNATIVE = 48271L;
    private static final long IM = 2147483647L;
    private double AM = 4.656612875245797E-010D;
    private long IA;
    private long idum;
    private boolean gaussAvail = false;
    private double gaussVal = 0.0D;

    public Ran0(long seed) {
        if ((seed <= 0L) || (seed >= 2147483647L)) {
            throw new RuntimeException("Ran0 Exception: seed must be in range 1..2^31-2");
        }
        IA = 69621L;
        idum = (seed * 48271L % 2147483647L);
    }

    public Ran0(long IA, long seed) {
        if ((seed <= 0L) || (seed >= 2147483647L)) {
            throw new RuntimeException("Ran0 Exception: seed must be in range 1..2^31-2. Yours was: " + seed);
        }
        this.IA = IA;
        idum = (seed * 48271L % 2147483647L);
    }

    public void setSeed(long seed) {
        if ((seed <= 0L) || (seed >= 2147483647L)) {
            throw new RuntimeException("Ran0 Exception: seed must be in range 1..2^31-2. Yours was: " + seed);
        }
        idum = (seed * 48271L % 2147483647L);
    }

    public double uniform() {
        idum = (IA * idum % 2147483647L);

        return AM * idum;
    }

    public double uniform(double lower, double upper) {
        return lower + uniform() * (upper - lower);
    }

    public long nextLong() {
        idum = (IA * idum % 2147483647L);
        return idum;
    }

    public double gauss() {
        double gdev = 0.0D;
        if (!gaussAvail) {
            while (!gaussAvail) {
                double v1 = 2.0D * uniform() - 1.0D;
                double v2 = 2.0D * uniform() - 1.0D;
                double rsq = v1 * v1 + v2 * v2;
                if ((rsq < 1.0D) && (rsq != 0.0D)) {
                    double fac = Math.sqrt(-2.0D * Math.log(rsq) / rsq);
                    gaussVal = (v1 * fac);
                    gaussAvail = true;
                    gdev = v2 * fac;
                }
            }
        }

        gaussAvail = false;
        gdev = gaussVal;

        return gdev;
    }

    public double gauss(double mean, double stdev) {
        return mean + stdev * gauss();
    }
}