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
        byte[] edges = {4,5,6,10,11,12,20,21,24,26,27};
        byte[] left_set = {20,21,24,26,27};
        byte[] both_set = {5,11,}
        Random rand = new Random();
        byte size = (byte)(rand.nextInt(2) + 3);
        byte north = edges[(byte)rand.nextInt(10)];

        if(north > 12) byte east = left_set[(byte)rand.nextInt(4)];
        else byte east = edges[(byte)rand.nextInt(10)];

        if(east > 12) byte south = left_set[(byte)rand.nextInt(4)];
        else byte south = edges[(byte)rand.nextInt(10)];

        if(south > 12 && north > 12) byte west = both_set[(byte)rand.nextInt(4)];
        else byte west = edges[(byte)rand.nextInt(10)];

        Piece A = new Piece(size, north, east, south, west);

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
