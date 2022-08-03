package puzzle_cube_solver;

import java.util.ArrayList;
import java.util.List;

public class CubeSolver {

    public static boolean checkFit(byte s1, byte s2) {
        final int b = (1 << Main.PIECE_SIZE - 2) - 1;
        if((s1 & s2) != 0) return false;
        if((((s1 | s2) >> 1) & b) == b) return true;
        return false;
    }

    public static List<Connection> findConnections(Piece first, Piece second) {
        List<Connection> list = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            Piece rotatedFirst = first.rotate(i);
            for(int j = 0; j < 4; j++) {
                Piece rotatedSecond = second.rotate(j);
                //check north / north
                boolean fits = checkFit(rotatedFirst.north, MathHelper.reverseBits(rotatedSecond.north));
                if(fits) list.add(new Connection(first, i, second, j));
            }
        }

        return list;
    }

    public record Connection(Piece p1, int r1, Piece p2, int r2) {

    }
}
