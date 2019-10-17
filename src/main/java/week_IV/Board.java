//package week_IV;
//
//import edu.princeton.cs.algs4.MinPQ;
//import edu.princeton.cs.algs4.StdOut;
//
//public class Board {
//
//    final MinPQ<Integer> minPQ = new MinPQ<>();
//final int[][] tiles;
//    // create a board from an n-by-n array of tiles,
//    // where tiles[row][col] = tile at (row, col)
//    public Board(int[][] tiles) {
//        this.tiles = tiles;
//    }
//
//    // string representation of this board
//    public String toString() {
//        final StringBuilder sb = new StringBuilder();
//        sb.append(tiles.length).append("\n");
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles.length; j++) {
//                sb.append(" " + tiles[i][j]);
//            }
//            sb.append("\n");
//        }
//
//        return sb.toString();
//    }
//
//    // board dimension n
//    public int dimension() {
//        return tiles.length;
//    }
//
//    // number of tiles out of place
//    public int hamming() {
//
//    }
//
//    // sum of Manhattan distances between tiles and goal
//    public int manhattan() {
//
//    }
//
//    // is this board the goal board?
//    public boolean isGoal() {
//
//    }
//
//    // does this board equal y?
//    public boolean equals(Object y) {
//
//    }
//
//    // all neighboring boards
//    public Iterable<Board> neighbors() {
//
//    }
//
//    // a board that is obtained by exchanging any pair of tiles
//    public Board twin() {
//
//    }
//
//    // unit testing (not graded)
//    public static void main(String[] args) {
//
//    }
//
//}
