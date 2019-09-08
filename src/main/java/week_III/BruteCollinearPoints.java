package week_III;


/** *****************************************************************************
 *  Name: Yohanes Tadesse
 *  Date: September 04, 2019
 *  Description: Assignment Three - BruteCollinearPoints
 **************************************************************************** */

public class BruteCollinearPoints {
    private LineSegment[] lineSegment;
    private int numberOfSegments = 0;

    public BruteCollinearPoints(Point[] points) {
        if (null == points)
            throw new IllegalArgumentException();

        computeLineSegments(points);
    }

    private void computeLineSegments(final Point[] points) {
        int length = points.length;
        int allSlopesSize = calculateSize(length - 1);
        final double[] slopes = new double[allSlopesSize];
        lineSegment = new LineSegment[allSlopesSize/4];
        int slopeCounter = 0;


        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                slopes[slopeCounter] = points[i].slopeTo(points[j]);
                slopeCounter++;

            }
        }

        final int[] duplicateSlopeIndexes = new int[slopeCounter];
        int duplicateCounter = 0;
        for (int i = 0; i < slopeCounter; i++) {

            boolean duplicate = false;
            for (int j = 0; j < duplicateCounter; j++) {
                if (i == duplicateSlopeIndexes[j]) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate)
                continue;

            final double slopeI = slopes[i];
            int equalityCounter = 0;

            for (int j = i + 1; j < slopeCounter; j++) {
                final double slopeJ = slopes[j];
                if (slopeI == slopeJ) {
                    equalityCounter++;
                    duplicateSlopeIndexes[duplicateCounter] = j;
                    duplicateCounter++;
                }
                if (equalityCounter >= 4) {
                    if(createAndAddSegments(i, j, points))
                    break;
                }

            }

        }

    }

    private boolean createAndAddSegments(final int i, final int j, final Point[] points) {
        final int length = points.length;
        final int[] first = getOriginalIndex(i, length);
        final int[] second = getOriginalIndex(j, length);

        final Point pointOne = points[first[0]];
        final Point pointTwo = points[first[1]];
        final Point pointThree = points[second[0]];
        final Point pointFour = points[second[1]];

        if (pointOne.compareTo(pointThree) == 0
                || pointOne.compareTo(pointFour) == 0
                || pointTwo.compareTo(pointThree) == 0
                || pointTwo.compareTo(pointFour) == 0)
            return false;

        final LineSegment segment = new LineSegment(pointOne, pointFour);
        lineSegment[numberOfSegments] = segment;
        numberOfSegments++;
        return true;
    }



    private int[] getOriginalIndex(final int fakeIndex, final int size) {

        int index = fakeIndex;
        int n = size -1;

        int i = 0;
        int postFix = 1;

        while(index >= n) {
            index -= n;
            n--;
            i++;
            postFix++;
        }
        int j = postFix + index;

        return new int[] {i, j};
    }

    private int calculateSize(final int n) {
        if (n == 1) return 1;
        return n + calculateSize(n - 1);
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        final LineSegment[] result = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            result[i] = lineSegment[i];
        }
        return result;
    }

}
