package FractalFunctions;

import java.util.Random;

public final class MT {

    public static final int MAX_INT = 2147483647;
    private int mt_index;
    private int[] mt_buffer = new int[624];

    public MT(long seed) {
        Random r = new Random(seed);
        for (int i = 0; i < 624; i++) {
            mt_buffer[i] = r.nextInt();
        }
        mt_index = 0;
    }

    public int random() {
        if (mt_index == 624) {
            mt_index = 0;
            int i = 0;

            for (; i < 227; i++) {
                int s = mt_buffer[i] & 0x80000000 | mt_buffer[(i + 1)] & 0x7FFFFFFF;
                mt_buffer[i] = (mt_buffer[(i + 397)] ^ s >> 1 ^ (s & 0x1) * -1727483681);
            }
            for (; i < 623; i++) {
                int s = mt_buffer[i] & 0x80000000 | mt_buffer[(i + 1)] & 0x7FFFFFFF;
                mt_buffer[i] = (mt_buffer[(i - 227)] ^ s >> 1 ^ (s & 0x1) * -1727483681);
            }

            int s = mt_buffer[623] & 0x80000000 | mt_buffer[0] & 0x7FFFFFFF;
            mt_buffer[623] = (mt_buffer[396] ^ s >> 1 ^ (s & 0x1) * -1727483681);
        }
        return mt_buffer[(mt_index++)];
    }

    public double nextDouble() {
        return random() / 2147483647;
    }
}
