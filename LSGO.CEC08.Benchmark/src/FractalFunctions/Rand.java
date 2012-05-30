package FractalFunctions;

public class Rand {

    static final int SIZEL = 8;
    static final int SIZE = 256;
    static final int MASK = 1020;
    int count;
    int[] rsl;
    private int[] mem;
    private int a;
    private int b;
    private int c;

    Rand() {
        mem = new int[256];
        rsl = new int[256];
        Init(false);
    }

    Rand(int[] seed) {
        mem = new int[256];
        rsl = new int[256];
        for (int i = 0; i < seed.length; i++) {
            rsl[i] = seed[i];
        }
        Init(true);
    }

    public final void Isaac() {
        b += ++c;
        int i = 0;
        for (int j = 128; i < 128;) {
            int x = mem[i];
            a ^= a << 13;
            a += mem[(j++)];
            int tmp101_100 = (mem[((x & 0x3FC) >> 2)] + a + b);
            int y = tmp101_100;
            mem[i] = tmp101_100;
            int tmp132_131 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp132_131;
            rsl[(i++)] = tmp132_131;

            x = mem[i];
            a ^= a >>> 6;
            a += mem[(j++)];
            int tmp205_204 = (mem[((x & 0x3FC) >> 2)] + a + b);
            y = tmp205_204;
            mem[i] = tmp205_204;
            int tmp236_235 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp236_235;
            rsl[(i++)] = tmp236_235;

            x = mem[i];
            a ^= a << 2;
            a += mem[(j++)];
            int tmp308_307 = (mem[((x & 0x3FC) >> 2)] + a + b);
            y = tmp308_307;
            mem[i] = tmp308_307;
            int tmp339_338 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp339_338;
            rsl[(i++)] = tmp339_338;

            x = mem[i];
            a ^= a >>> 16;
            a += mem[(j++)];
            int tmp412_411 = (mem[((x & 0x3FC) >> 2)] + a + b);
            y = tmp412_411;
            mem[i] = tmp412_411;
            int tmp443_442 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp443_442;
            rsl[(i++)] = tmp443_442;
        }

        for (int j = 0; j < 128;) {
            int x = mem[i];
            a ^= a << 13;
            a += mem[(j++)];
            int tmp528_527 = (mem[((x & 0x3FC) >> 2)] + a + b);
            int y = tmp528_527;
            mem[i] = tmp528_527;
            int tmp559_558 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp559_558;
            rsl[(i++)] = tmp559_558;

            x = mem[i];
            a ^= a >>> 6;
            a += mem[(j++)];
            int tmp632_631 = (mem[((x & 0x3FC) >> 2)] + a + b);
            y = tmp632_631;
            mem[i] = tmp632_631;
            int tmp663_662 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp663_662;
            rsl[(i++)] = tmp663_662;

            x = mem[i];
            a ^= a << 2;
            a += mem[(j++)];
            int tmp735_734 = (mem[((x & 0x3FC) >> 2)] + a + b);
            y = tmp735_734;
            mem[i] = tmp735_734;
            int tmp766_765 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp766_765;
            rsl[(i++)] = tmp766_765;

            x = mem[i];
            a ^= a >>> 16;
            a += mem[(j++)];
            int tmp839_838 = (mem[((x & 0x3FC) >> 2)] + a + b);
            y = tmp839_838;
            mem[i] = tmp839_838;
            int tmp870_869 = (mem[((y >> 8 & 0x3FC) >> 2)] + x);
            b = tmp870_869;
            rsl[(i++)] = tmp870_869;
        }
    }

    public final void Init(boolean flag) {
        int h;
        int g;
        int f;
        int e;
        int d;
        int c;
        int b;
        int a = b = c = d = e = f = g = h = -1640531527;

        for (int i = 0; i < 4; i++) {
            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            f += c;
            d += e;
            d ^= e >>> 16;
            g += d;
            e += f;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            b += g;
            h += a;
            h ^= a >>> 9;
            c += h;
            a += b;
        }

        for (int i = 0; i < 256; i += 8) {
            if (flag) {
                a += rsl[i];
                b += rsl[(i + 1)];
                c += rsl[(i + 2)];
                d += rsl[(i + 3)];
                e += rsl[(i + 4)];
                f += rsl[(i + 5)];
                g += rsl[(i + 6)];
                h += rsl[(i + 7)];
            }
            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            f += c;
            d += e;
            d ^= e >>> 16;
            g += d;
            e += f;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            b += g;
            h += a;
            h ^= a >>> 9;
            c += h;
            a += b;
            mem[i] = a;
            mem[(i + 1)] = b;
            mem[(i + 2)] = c;
            mem[(i + 3)] = d;
            mem[(i + 4)] = e;
            mem[(i + 5)] = f;
            mem[(i + 6)] = g;
            mem[(i + 7)] = h;
        }

        if (flag) {
            for (int i = 0; i < 256; i += 8) {
                a += mem[i];
                b += mem[(i + 1)];
                c += mem[(i + 2)];
                d += mem[(i + 3)];
                e += mem[(i + 4)];
                f += mem[(i + 5)];
                g += mem[(i + 6)];
                h += mem[(i + 7)];
                a ^= b << 11;
                d += a;
                b += c;
                b ^= c >>> 2;
                e += b;
                c += d;
                c ^= d << 8;
                f += c;
                d += e;
                d ^= e >>> 16;
                g += d;
                e += f;
                e ^= f << 10;
                h += e;
                f += g;
                f ^= g >>> 4;
                a += f;
                g += h;
                g ^= h << 8;
                b += g;
                h += a;
                h ^= a >>> 9;
                c += h;
                a += b;
                mem[i] = a;
                mem[(i + 1)] = b;
                mem[(i + 2)] = c;
                mem[(i + 3)] = d;
                mem[(i + 4)] = e;
                mem[(i + 5)] = f;
                mem[(i + 6)] = g;
                mem[(i + 7)] = h;
            }
        }

        Isaac();
        count = 256;
    }

    public final int val() {
        if (0 == count--) {
            Isaac();
            count = 255;
        }
        return rsl[count];
    }

    public static void main(String[] args) {
        int[] seed = new int[256];
        Rand x = new Rand(seed);
        for (int i = 0; i < 2; i++) {
            x.Isaac();
            for (int j = 0; j < 256; j++) {
                String z = Integer.toHexString(x.rsl[j]);
                while (z.length() < 8) {
                    z = "0" + z;
                }
                System.out.print(z);
                if ((j & 0x7) != 7) {
                    continue;
                }
                System.out.println("");
            }
        }
    }
}