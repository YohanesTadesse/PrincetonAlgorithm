package week_II;


/** *****************************************************************************
 *  Name:   Yohanes Tadesse
 *  Date:   Aug 11, 2019
 *  Description:    Randomized Queue - Assignment II
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        final RandomizedQueue<String> strs = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            strs.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            StdOut.println(strs.dequeue());
        }
    }
}

