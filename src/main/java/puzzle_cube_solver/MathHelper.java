package puzzle_cube_solver;

import java.util.List;

public class MathHelper {
    public static boolean isBitOne(byte b, int bit) {
        return (b >> bit & 1) == 1;
    }

    public static byte reverseBits(byte b) {
        byte r = 0;

        while (b != 0) {
            r <<= 1;
            r |= (b & 1);
            r >>= 1;
        }

        return r;
    }
}
