package puzzle_cube_solver;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
        //generatePiece();
    }

    public static String convertByteToBinary(byte n) {
        if (n == 0) {
            return "00000";
        }
        StringBuilder binaryNumber = new StringBuilder();
        while (n > 0) {
            int remainder = n % 2;
            binaryNumber.append(remainder);
            n /= 2;
        }
        while(binaryNumber.length() < 5)
        {
            binaryNumber.append("0");
        }
        return binaryNumber.reverse().toString();
    }
    public static String convertByteToHashTag(byte n) {
        if (n == 0) {
            return "     ";
        }
        StringBuilder binaryNumber = new StringBuilder();
        while (n > 0) {
            int remainder = n % 2;
            if(remainder != 1) binaryNumber.append(" ");
            else binaryNumber.append("#");
            n /= 2;
        }
        while(binaryNumber.length() < 5)
        {
            binaryNumber.append(" ");
        }
        return binaryNumber.reverse().toString();
    }
    public static String reverseString(String str) {
        char ch;
        String nstr = "";
        for (int i=0; i<str.length(); i++)
        {
            ch = str.charAt(i); //extracts each character
            nstr = ch + nstr; //adds each character in front of the existing string
        }
        return nstr;
    }
    public static void generatePiece() {
        //             only right               both            nothing         only left
        byte[] edges = {0b00101,0b01011,0b00111,0b10101,0b11011,0b00100,0b01010,0b10100,0b11010,0b11100};
        //             both            only left
        byte[] left = {0b10101,0b11011,0b10100,0b11010,0b11100};
        byte[] left_conn = {0b11011,0b11010,0b11100};
        //                 only right              nothing
        byte[] not_left = {0b00101,0b01011,0b00111,0b00100,0b01010,};
        byte[] right_only = {0b00101,0b01011,0b00111};
        byte[] left_only = {0b10100,0b11010,0b11100};
        byte[] both = {0b10101,0b11011};
        byte[] nothing = {0b00100,0b01010};
        byte north, east, south, west;
        Random rand = new Random();
        //create north edge
        north = edges[(byte)rand.nextInt(9)];
        //if north has right corner
        if(north % 2 == 1){
            //check if it is connected, then east must have a left corner
            if(((north >>> 1) & 1) != 0) east = left[(byte)rand.nextInt(5)];
            //else, east must have a left corner and must be connected
            else east = left_conn[(byte)rand.nextInt(5)];
        }
        //else it can be anything without left corner
        else east = not_left[(byte)rand.nextInt(5)];

        if(east % 2 == 1){
            if(((east >>> 1) & 1) != 0) south = left[(byte)rand.nextInt(5)];
            else south = left_conn[(byte)rand.nextInt(5)];
        }
        else south = not_left[(byte)rand.nextInt(5)];

        if(south % 2 == 1 && ((north >>> 4) & 1) != 0){
            west = both[(byte)rand.nextInt(1)];
        }
        else if(south % 2 == 1 ){
            west = left_only[(byte)rand.nextInt(2)];
        }
        else if(((north >>> 4) & 1) != 0){
            west = right_only[(byte)rand.nextInt(2)];
        }
        else west = nothing[(byte)rand.nextInt(1)];

        System.out.println(convertByteToBinary(north));
        System.out.println(convertByteToBinary(east));
        System.out.println(convertByteToBinary(south));
        System.out.println(convertByteToBinary(west));
        System.out.println();

        System.out.println(convertByteToHashTag(north));
        for(int row = 1; row < 4; row++)  {
            for(int col = 0; col < 5; col++)  {
                if(col == 0) {
                    System.out.print(convertByteToHashTag(east).charAt(row));
                    System.out.print("###");
                }
                if(col == 4) {
                    System.out.print(reverseString(convertByteToHashTag(west)).charAt(row));
                }
            }
            System.out.println();
        }
        System.out.println(reverseString(convertByteToHashTag(south)));
    }
}
