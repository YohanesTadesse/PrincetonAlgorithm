package week_I;

/** *****************************************************************************
 *  Name: Yohanes Tadesse
 *  Date: August 04, 2019
 *  Description: Assignment one - Percolation Stats
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) throw new IllegalArgumentException();
        Percolation percolation = new Percolation(n);

        calculateStats(n, trials, percolation);
    }

    private void calculateStats(int n, int trials, Percolation percolation) {
        double[] treshold = new double[trials];
        for (int i = 0; i < trials; i++) {

            while (!percolation.percolates()) {
                int randomRowIndex = getRandomIndex(n);
                int randomColumnIndex = getRandomIndex(n);
                percolation.open(randomRowIndex, randomColumnIndex);
            }
            treshold[i] = percolation.numberOfOpenSites() / (double) n;
        }
        mean = StdStats.mean(treshold);
        stddev = StdStats.stddev(treshold);
        double confidenceFraction = (1.96 * stddev()) / Math.sqrt(trials);
        confidenceLo = mean - confidenceFraction;
        confidenceHi = mean + confidenceFraction;
    }

    private int getRandomIndex(int n) {
        return StdRandom.uniform(n) + 1;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        final int n = StdIn.readInt();
        final int trial = StdIn.readInt();
        System.out.println("Percolation stats for grid: " + n + " trials: " + trial);
        PercolationStats stats = new PercolationStats(n, trial);
        System.out.println("mean: \t\t\t\t\t\t" + stats.mean());
        System.out.println("stddev: \t\t\t\t\t" + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");

    }
}

