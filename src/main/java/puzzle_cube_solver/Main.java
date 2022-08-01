package puzzle_cube_solver;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
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
        Random rand = new Random();
        //create north edge
        byte north = edges[(byte)rand.nextInt(9)];
        //if north has right corner
        if(north % 2 == 1){
            //check if it is connected, then east must have a left corner
            if(((north >>> 1) & 1) != 0) byte east = left[(byte)rand.nextInt(5)];
            //else, east must have a left corner and must be connected
            else byte east = left_conn[(byte)rand.nextInt(5)];
        }
        //else it can be anything without left corner
        else byte east = not_left[(byte)rand.nextInt(5)];

        if(east % 2 == 1){
            if(((east >>> 1) & 1) != 0) byte south = left[(byte)rand.nextInt(5)];
            else byte south = left_conn[(byte)rand.nextInt(5)];
        }
        else byte south = not_left[(byte)rand.nextInt(5)];

        if(south % 2 == 1){
            if(((south >>> 1) & 1) != 0) byte west = left[(byte)rand.nextInt(5)];
            else byte west = left_conn[(byte)rand.nextInt(5)];
        }
        else byte west = not_left[(byte)rand.nextInt(5)];

        /*if(north % 2 == 1) {
            if(((north >>> 1) & 1) != 0 || ((east >>> 3) & 1) != 0) {
                east += 16;
            }
            else {
                north -= 1;
            }
        }
        if(east % 2 == 1) {
            if(((east >>> 1) & 1) != 0 || ((south >>> 3) & 1) != 0) {
                south += 16;
            }
            else {
                east -= 1;
            }
        }
        if(south % 2 == 1) {
            if(((south >>> 1) & 1) != 0 || ((west >>> 3) & 1) != 0) {
                west += 16;
            }
            else {
                south -= 1;
            }
        }
        if(west % 2 == 1) {
            if(((west >>> 1) & 1) != 0 || ((north >>> 3) & 1) != 0) {
                north += 16;
            }
            else {
                west -= 1;
            }
        }
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
        System.out.println();*/
        System.out.println(convertByteToBinary(north));
        System.out.println(convertByteToBinary(east));
        System.out.println(convertByteToBinary(south));
        System.out.println(convertByteToBinary(west));

    }
}
