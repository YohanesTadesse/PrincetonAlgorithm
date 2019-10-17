package week_III;

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (null == that) throw new IllegalArgumentException();

        final int numerator = that.y - this.y;
        final int denominator = that.x - this.x;

        // degenerate == p1 == p2
        if (denominator == 0 && numerator == 0)
            return Double.NEGATIVE_INFINITY;
        // vertical line -- undefined
        if (denominator == 0)
            return Double.POSITIVE_INFINITY;
        if (numerator == 0)
            return 0.0;
        return (double) numerator / denominator;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (null == that) throw new IllegalArgumentException();

        final int result = compareY(that);
        if (result == 0)
           return Double.compare(this.x, that.x);
        return result;
    }

    private int compareY(final Point that) {
        return Double.compare(this.y, that.y);
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */

        return new Comparator<Point>() {
            @Override
            public int compare(final Point o1, final Point o2) {
                if (null == o1 || null == o2) throw new IllegalArgumentException();

                final double slope1 = slopeTo(o1);
                final double slope2 = slopeTo(o2);

                return Double.compare(slope1, slope2);
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */

//        // compare two points
//        final Point p1 = new Point(1, 2);
//        final Point p2 = new Point(4, 2);
//        final Point p3 = new Point(1, 5);
//        final Point p4 = new Point(3, 8);
//
//        System.out.println(p1.compareTo(p2));
//        System.out.println(p2.compareTo(p1));
//
//
//        System.out.println(p2.slopeTo(p2));
//        System.out.println(p2.slopeTo(p4));
//        System.out.println(p1.slopeTo(p3));
//
//
//        System.out.println(p1.slopeTo(p4));
//        System.out.println(p1.slopeTo(p2));
//
//        System.out.println(p2.slopeTo(p3));
//
//        System.out.println(p3.slopeTo(p4));


        final Point p12 = new Point(8, 9);
        final Point p13 = new Point(3, 3);
        final double v = p12.slopeTo(p13);
        for (int i = 0; i < 100000; i++) {
            final double x = p12.slopeTo(p13);
            if (Double.compare(x, v) != 0)
            System.out.println(v + " : " + x);
        }

        final Point p16 = new Point(30836, 23979);
        final Point p17 = new Point(8363, 29470);
        for (int i = 0; i < 100; i++) {
            final double x = p16.slopeTo(p17);
        }

        final Point p18 = new Point(30836, 23979);
        final Point p19 = new Point(8363, 23979);
        for (int i = 0; i < 100; i++) {
            final double x = p18.slopeTo(p19);
        }


//        final Point p14 = new Point(410, 361);
//        final Point p15 = new Point(410, 361);
//
//        for (int i = 0; i < 10; i++) {
//
//            System.out.println(p14.slopeTo(p15));
//        }
//
//
//        final Point pp1 = new Point(1, 1);
//        final Point pp2 = new Point(2, 2);
//
//        System.out.println(pp1.slopeTo(pp2));
    }
}
