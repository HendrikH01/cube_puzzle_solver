class Piece {
    public byte north;
    public byte east;
    public byte south;
    public byte west;
    public int size;

    public Piece(int size, byte north, byte east, byte south, byte west) {
        this.size = size;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
}