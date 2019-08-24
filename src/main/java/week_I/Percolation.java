package week_I;

/** *****************************************************************************
 *  Name: Yohanes Tadesse
 *  Date: August 04, 2019
 *  Description: Assignment One - Percolation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int openCount = 0;
    private boolean[][] openCells;
    private final int n;
    private final int top;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException();
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        this.openCells = new boolean[n][n];
        this.n = n;
        this.top = n * n;
        this.bottom = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        row = getCellNumber(row);
        col = getCellNumber(col);
        openCells[row][col] = true;
        union(row, col);
        openCount++;
    }

    private void union(int row, int col) {
        Integer[] arrays = getNeighbours(row, col, n);

        int element = getWeightedQuickUnionUFIndex(row, col, n);
        if (row == 0) {
            weightedQuickUnionUF.union(top, element);
        }

        if (row == n - 1) {
            weightedQuickUnionUF.union(bottom, element);
        }

        for (int i = 0; i < 7; i += 2) {
            if (null == arrays[i]) {
                continue;
            }

            int row1 = arrays[i];
            int col1 = arrays[i+1];
            int element2 = getWeightedQuickUnionUFIndex(row1, col1, n);
            if (openCells[row1][col1] && !weightedQuickUnionUF.connected(element, element2)) {
                weightedQuickUnionUF.union(element2, element);
                union(row1, col1);
            }
        }

    }

    private int getCellNumber(final int num) {
        return num - 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row = getCellNumber(row);
        col = getCellNumber(col);
        isAccessValid(row, col);
        return openCells[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row = getCellNumber(row);
        col = getCellNumber(col);
        isAccessValid(row, col);
        int index = getWeightedQuickUnionUFIndex(row, col, n);

        return weightedQuickUnionUF.connected(top, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(top, bottom);
    }

    private void isAccessValid(int row, int column) {
        if (row < 0 || column < 0 || row > (n - 1) || column > (n - 1))
            throw new IllegalArgumentException();
    }


    private Integer[] getNeighbours(int row, int col, int size) {
        Integer[] neighbourCell = new Integer[8];

        if (row > 0) {
            neighbourCell[0] = row - 1;
            neighbourCell[1] = col;
        }
        if (row < size - 1) {
            neighbourCell[2] = row + 1;
            neighbourCell[3] = col;
        }
        if (col > 0) {
            neighbourCell[4] = row;
            neighbourCell[5] = col - 1;
        }
        if (col < size - 1) {
            neighbourCell[6] = row;
            neighbourCell[7] = col + 1;
        }

        return neighbourCell;
    }

    private int getWeightedQuickUnionUFIndex(int row, int col, int size) {
        return row * size + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(6);
        p.open(1, 6);
        StdOut.println(p.isFull(1, 6));
        StdOut.println(p.isOpen(1, 6));
        p.percolates();

        p.open(2, 6);
        StdOut.println(p.isOpen(2, 6));

        p.open(3, 6);
        StdOut.println(p.isOpen(3, 6));

        p.open(4, 6);
        StdOut.println(p.isOpen(4, 6));

        p.open(5, 6);
        StdOut.println(p.isOpen(5, 6));

        p.open(5, 5);
        StdOut.println(p.isOpen(5, 5));

        p.open(4, 4);
        StdOut.println(p.isOpen(4, 4));

        p.open(3, 4);
        StdOut.println(p.isOpen(3, 4));

        p.open(2, 4);
        p.open(2, 3);
        p.open(2, 2);
        p.open(2, 1);
        p.open(3, 1);
        p.open(4, 1);
        p.open(5, 1);
        p.open(5, 2);
        p.open(6, 2);
        p.open(5, 4);

        // int[][] ints = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}};


    }
}
