package puzzle_cube_solver;

class Piece {
    public int north;
    public int east;
    public int south;
    public int west;
    public Piece(int north, int east, int south, int west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
}