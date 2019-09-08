package week_III;


/** *****************************************************************************
 *  Name: Yohanes Tadesse
 *  Date: September 04, 2019
 *  Description: Assignment Three - BruteCollinearPoints
 **************************************************************************** */

public class BruteCollinearPointsII {
    private int segments;
    private LineSegment[] lineSegments;


    public BruteCollinearPointsII(Point[] points) {
        checkForIllegalArgument(points);

        // 4segments only for n inputs
        final int length = points.length;
        lineSegments = new LineSegment[length];
        segments = 0;

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
                    for (int l = k + 1; l < length; l++) {
                        final Point p4 = points[l];
                        if (p1.slopeTo(p2) == p3.slopeTo(p4)) {
                            lineSegments[segments] = new LineSegment(p1, p4);
                            segments++;
                            flag[i] = true;
                            flag[j] = true;
                            flag[k] = true;
                            flag[l] = true;
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
            results[i] =lineSegments[i];
        }
        return results;
    }

}
