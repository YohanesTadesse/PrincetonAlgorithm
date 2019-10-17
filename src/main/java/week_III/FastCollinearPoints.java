package week_III;


import javax.sound.sampled.Line;
import java.util.Comparator;

/** *****************************************************************************
 *  Name: Yohanes Tadesse
 *  Date: September 04, 2019
 *  Description: Assignment Three - FastCollinearPoints
 **************************************************************************** */

public class FastCollinearPoints {

    private LineSegment[] lineSegments;
    private int segmentCounter = 0;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        checkForIllegalArgument(points);
        compute(points);
    }

    private void compute(final Point[] points) {
        final int length = points.length;
        if (length < 4) {
            lineSegments = new LineSegment[0];
            return;
        }
        final int maxPossibleSegmentsSize = length/4;
        lineSegments = new LineSegment[maxPossibleSegmentsSize];

        for (int i = 0; i < length - 3; i++) {
            final Point[] aux = new Point[length];
            final Point currentPoint = points[i];
            sort(points, aux, i + 1, length - 1, currentPoint);
            final Comparator<Point> currentPointComparator = currentPoint.slopeOrder();

            for (int j = i; j < length - 3; j++) {
                if (currentPointComparator.compare(points[j + 1], points[j + 3]) == 0) {
                    lineSegments[segmentCounter] = new LineSegment(currentPoint, points[i + 3]);
                    segmentCounter++;
                    break;
                }
            }
        }
    }

    private void sort(Point[] a, Point[] aux, int lo, int hi, Point p) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/ 2;
        sort (aux, a, lo, mid, p);
        sort (aux, a, mid + 1, hi, p);
        merge(a, aux, lo, mid, hi, p);
    }

    private void merge(Point[] a, Point[] aux, int lo, int mid, int hi, Point p) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i >  mid) a[k] = aux[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (less(a[j], a[i], p)) aux[k] = a[j++];
            else aux[k] = a[i++];
        }
    }

    private boolean less(final Point first, final Point second, final Point p) {
        final Comparator<Point> comparator = p.slopeOrder();
        return  (comparator.compare(first, second) < 0);
    }

    public int numberOfSegments() {
        return segmentCounter;
    }

    public LineSegment[] segments() {
        final LineSegment[] actualSegments = new LineSegment[segmentCounter];
        for (int i = 0; i < segmentCounter; i++) {
            actualSegments[i] = lineSegments[i];
        }
        return actualSegments;
    }


    private void checkForIllegalArgument(final Point[] points) {
        if (null == points)
            throw new IllegalArgumentException();

        for (final Point point : points) {
            if (null == point) throw new IllegalArgumentException();
        }
    }
}
