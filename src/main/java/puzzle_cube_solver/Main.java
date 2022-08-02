package puzzle_cube_solver;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        //app.run();
        generatePermutations((byte) 1, (byte) 7);
    }
    public static void generatePermutations(byte start, byte end) {
        List<Byte> edges = new ArrayList<>();
        for (byte i = start; i < end; i++) {
            edges.add(i);
            //System.out.println(Integer.toBinaryString(i));
        }
        List<Piece> pers = new ArrayList<>();
        heapPermutation(pers, edges, edges.size());
        List<Piece> pieces = new ArrayList<>();
        fillCorners(pers, pieces);
        System.out.println(pers.size());
        for (Piece piece : pers) {
            System.out.print(Integer.toBinaryString(piece.north) + " ");
            System.out.print(Integer.toBinaryString(piece.east) + " ");
            System.out.print(Integer.toBinaryString(piece.south) + " ");
            System.out.print(Integer.toBinaryString(piece.west) + " ");
            System.out.println(" ");
        }

    }
    public static void heapPermutation(List<Piece> pers, List<Byte> edges, int size)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1){
            pers.add(new Piece(edges.get(0), edges.get(1), edges.get(2), edges.get(3)));
        }

        for (int i = 0; i < size; i++) {

            heapPermutation(pers, edges, size - 1);
            // if size is odd, swap 0th i.e (first) and
            // (size-1)th i.e (last) element
            if (size % 2 == 1) {
                byte temp = edges.get(0);
                edges.set(0, edges.get(size - 1));
                edges.set(size - 1, temp);
            }

            // If size is even, swap ith
            // and (size-1)th i.e last element
            else {
                byte temp = edges.get(i);
                edges.set(i, edges.get(size -1));
                edges.set(size - 1, temp);
            }

        }

    }
    public static  void fillCorners(List<Piece> pers, List<Piece> pieces) {
        for(Piece piece : pers) {
            pieces.add(new Piece((byte) (piece.north << 1), (byte) (piece.east << 1), (byte) (piece.south << 1), (byte) (piece.west << 1)));
        }
        for(Piece piece : pieces) {
            boolean uL, uR, bR, bL = false;
            if(MathHelper.isBitOne(piece.west, 2) || MathHelper.isBitOne(piece.north, 4)) {
                uL = true;
            }
            if(MathHelper.isBitOne(piece.north, 2) || MathHelper.isBitOne(piece.east, 4)) {
                uR = true;
            }
            if(MathHelper.isBitOne(piece.east, 2) || MathHelper.isBitOne(piece.south, 4)) {
                bR = true;
            }
            if(MathHelper.isBitOne(piece.south, 2) || MathHelper.isBitOne(piece.west, 4)) {
                bL = true;
            }
        }
    }
}
