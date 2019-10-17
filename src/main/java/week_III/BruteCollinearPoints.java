package week_III;


/** *****************************************************************************
 *  Name: Yohanes Tadesse
 *  Date: September 04, 2019
 *  Description: Assignment Three - BruteCollinearPoints
 **************************************************************************** */

public class BruteCollinearPoints {
    private int segments;
    private final LineSegment[] lineSegments;


    public BruteCollinearPoints(final Point[] points) {
        checkForIllegalArgument(points);
        if (points.length < 4) {
            lineSegments = new LineSegment[0];
            segments = 0;
            return;
        }
        final Point[] deDupPoints = deDupPoints(points);
        final int length = deDupPoints.length;
        lineSegments = new LineSegment[length];
        segments = 0;
        findSegments(deDupPoints, length);
    }

    private Point[] deDupPoints(final Point[] points) {

        int end = points.length;

        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    int shiftLeft = j;
                    for (int k = j+1; k < end; k++, shiftLeft++) {
                        points[shiftLeft] = points[k];
                    }
                    end--;
                    j--;
                }
            }
        }

        final Point[] whitelist = new Point[end];
        for(int i = 0; i < end; i++){
            whitelist[i] = points[i];
        }
        return whitelist;
    }

    private void findSegments(final Point[] points, final int length) {
        // 4segments only for n inputs

        final boolean[] flag = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (flag[i]) {
                continue;
            }
            boolean exit = false;
            final Point p1 = points[i];

            for (int j = i + 1; j < length; j++) {
                final Point p2 = points[j];
                for (int k = j + 1; k < length; k++) {
                    final Point p3 = points[k];
                    for (int m = k + 1; m < length; m++) {
                        final Point p4 = points[m];
                        if (p1.slopeTo(p2) == p3.slopeTo(p4)) {
                            final LineSegment newLineSegment = new LineSegment(p1, p4);
                            lineSegments[segments] = newLineSegment;
                            segments++;
                            flag[i] = true;
                            flag[j] = true;
                            flag[k] = true;
                            flag[m] = true;
                            exit = true;
                        }
                        if (exit) break;
                    }
                    if (exit) break;
                }
                if (exit) break;
            }
        }
    }

    private void checkForIllegalArgument(final Point[] points) {
        if (null == points)
            throw new IllegalArgumentException();

        for (final Point point : points) {
            if (null == point) throw new IllegalArgumentException();
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return segments;
    }

    public LineSegment[] segments() {
        final LineSegment[] results = new LineSegment[segments];
        for (int i = 0; i < segments; i++) {
            results[i] = lineSegments[i];
        }
        return results;
    }

}
