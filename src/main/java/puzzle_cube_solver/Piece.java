package puzzle_cube_solver;

class Piece {
    public final byte north;
    public final byte east;
    public final byte south;
    public final byte west;

    public Piece(byte north, byte east, byte south, byte west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public byte[] getSides() {
        return new byte[] {north, east, south, west};
    }

    /**
     * Rotates piece clockwise by 90 degrees r times.
     * @param r
     * @return
     */
    public Piece rotate(int r) {
        return switch(r % 4) {
            case 1 -> new Piece(west, north, east, south);
            case 2 -> new Piece(south, west, north, east);
            case 3 -> new Piece(east, south, west, north);
            default -> this;
        };
    }
}