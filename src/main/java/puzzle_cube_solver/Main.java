package puzzle_cube_solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        //app.run();
        generatePermutations(1,7);
    }
    public static void generatePermutations(int start, int end) {
        List<Integer> edges = new ArrayList<>();
        for (int i = start; i < end; i++) {
            edges.add(i);
            //System.out.println(Integer.toBinaryString(i));
        }
        List<Piece> pieces = new ArrayList<>();
        heapPermutation(pieces, edges, edges.size());
        System.out.println(pieces.size());
        for (Piece piece : pieces) {
            System.out.print(Integer.toBinaryString(piece.north) + " ");
            System.out.print(Integer.toBinaryString(piece.east) + " ");
            System.out.print(Integer.toBinaryString(piece.south) + " ");
            System.out.print(Integer.toBinaryString(piece.west) + " ");
            System.out.println(" ");
        }

    }
    public static void heapPermutation(List<Piece> pieces, List<Integer> edges, int size)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1){

            pieces.add(new Piece(edges.get(0), edges.get(1), edges.get(2), edges.get(3)));
        }

        for (int i = 0; i < size; i++) {

            heapPermutation(pieces, edges, size - 1);
            // if size is odd, swap 0th i.e (first) and
            // (size-1)th i.e (last) element
            if (size % 2 == 1) {
                int temp = edges.get(0);
                edges.set(0, edges.get(size - 1));
                edges.set(size - 1, temp);
            }

            // If size is even, swap ith
            // and (size-1)th i.e last element
            else {
                int temp = edges.get(i);
                edges.set(i, edges.get(size -1));
                edges.set(size - 1, temp);
            }

        }

    }
    public static  void fillCorners(List<Piece> pieces) {

    }
}
