package week_III;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class BruteCollinearPointsTest {

    @Test
    public void shouldReturnCollinearPointsForOneMatchingLineSegment() {

        final Point expectedPoint1 = new Point(1, 1);
        final Point expectedPoint2 = new Point(4, 4);
        final Point[] points =
                new Point[]{
                        expectedPoint1,
                        new Point(2, 2),
                        new Point(3, 3),
                        expectedPoint2,
                        new Point(4, 5)
        };

       final BruteCollinearPointsII bruteCollinearPoints = new BruteCollinearPointsII(points);

       assertThat(bruteCollinearPoints.numberOfSegments(), is(1));

       assertThat(bruteCollinearPoints.segments()[0].toString(), equalTo(new LineSegment(expectedPoint1, expectedPoint2).toString()));
    }


    @Test
    public void shouldReturnCollinearPointsForTwoMatchingLineSegment() {

        final Point expectedPoint1 = new Point(1, 1);
        final Point expectedPoint2 = new Point(4, 4);
        final Point expectedPoint3 = new Point(1, 0);
        final Point expectedPoint4 = new Point(4, 3);

        final Point point1 = new Point(2, 2);
        final Point point2 = new Point(3, 3);
        final Point point3 = new Point(2, 1);
        final Point point4 = new Point(3, 2);
        final Point point5 = new Point(4, 9);

        final Point[] points =
                new Point[] {
                        expectedPoint1,
                        point1,
                        point2,
                        expectedPoint2,
                        expectedPoint3,
                        point3,
                        point4,
                        expectedPoint4,
                        point5
                };

        final BruteCollinearPointsII bruteCollinearPoints = new BruteCollinearPointsII(points);

        assertThat(bruteCollinearPoints.numberOfSegments(), is(2));

        final LineSegment lineSegment1 = new LineSegment(expectedPoint1, expectedPoint2);
        final LineSegment lineSegment2 = new LineSegment(expectedPoint3, expectedPoint4);
        assertThat(bruteCollinearPoints.segments()[0].toString(), equalTo(lineSegment1.toString()));
        assertThat(bruteCollinearPoints.segments()[1].toString(), equalTo(lineSegment2.toString()));
    }



    @Test
    public void shouldReturnCollinearPointsForTwoMatchingLineSegmentRandomSetUp() {

        final Point expectedPoint1 = new Point(1, 1);
        final Point expectedPoint2 = new Point(4, 4);
        final Point expectedPoint3 = new Point(1, 0);
        final Point expectedPoint4 = new Point(4, 3);

        final Point point1 = new Point(2, 2);
        final Point point2 = new Point(3, 3);
        final Point point3 = new Point(2, 1);
        final Point point4 = new Point(3, 2);
        final Point point5 = new Point(4, 9);

        final Point[] points =
                new Point[] {
                        point1,
                        expectedPoint1,
                        point2,
                        expectedPoint3,
                        expectedPoint2,
                        point3,
                        point4,
                        point5,
                        expectedPoint4
                };

        final BruteCollinearPointsII bruteCollinearPoints = new BruteCollinearPointsII(points);

        assertThat(bruteCollinearPoints.numberOfSegments(), is(2));

        final LineSegment lineSegment1 = new LineSegment(expectedPoint1, expectedPoint2);
        final LineSegment lineSegment2 = new LineSegment(expectedPoint3, expectedPoint4);
        final LineSegment[] segments = bruteCollinearPoints.segments();
        assertThat(segments[0].toString(), equalTo(lineSegment1.toString()));
        assertThat(segments[1].toString(), equalTo(lineSegment2.toString()));
    }

    @Test
    void name() {
        Point p = new Point(1, 1);
        Point p2 = new Point(1, 2);

        System.out.println(p.slopeTo(p2));
        System.out.println(new Point(1, 1).slopeTo(new Point(1, 1)));
    }

    @Test
    void shouldGetOriginalIndex() {

        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(new Point[] {new Point(1, 2)});
        final int[] original = new int[] {1, 2, 3, 4, 5, 6, 7};

        final int length = original.length;

        final int[] expected = new int[] {
//               0, 1,  2,  3,  4,  5
                12, 13, 14, 15, 16, 17,
//               6,  7, 8,  9,  10,
                23, 24, 25, 26, 27,
//              11, 12, 13, 14,
                34, 35, 36, 37,
//              15, 16, 17
                45, 46, 47,
//              18, 19
                56, 57,
//              20
                67
        };

//
//        assertThat(collinearPoints.getOriginalIndex(0, length), is(new int[] {0, 1}));
//        assertThat(collinearPoints.getOriginalIndex(1, length), is(new int[] {0, 2}));
//        assertThat(collinearPoints.getOriginalIndex(2, length), is(new int[] {0, 3}));
//        assertThat(collinearPoints.getOriginalIndex(3, length), is(new int[] {0, 4}));
//        assertThat(collinearPoints.getOriginalIndex(4, length), is(new int[] {0, 5}));
//        assertThat(collinearPoints.getOriginalIndex(5, length), is(new int[] {0, 6}));
//
//        assertThat(collinearPoints.getOriginalIndex(6, length), is(new int[] {1, 2}));
//        assertThat(collinearPoints.getOriginalIndex(7, length), is(new int[] {1, 3}));
//        assertThat(collinearPoints.getOriginalIndex(8, length), is(new int[] {1, 4}));
//        assertThat(collinearPoints.getOriginalIndex(9, length), is(new int[] {1, 5}));
//        assertThat(collinearPoints.getOriginalIndex(10, length), is(new int[] {1, 6}));
//
//        assertThat(collinearPoints.getOriginalIndex(11, length), is(new int[] {2, 3}));
//        assertThat(collinearPoints.getOriginalIndex(12, length), is(new int[] {2, 4}));
//        assertThat(collinearPoints.getOriginalIndex(13, length), is(new int[] {2, 5}));
//        assertThat(collinearPoints.getOriginalIndex(14, length), is(new int[] {2, 6}));
//
//        assertThat(collinearPoints.getOriginalIndex(15, length), is(new int[] {3, 4}));
//        assertThat(collinearPoints.getOriginalIndex(16, length), is(new int[] {3, 5}));
//        assertThat(collinearPoints.getOriginalIndex(17, length), is(new int[] {3, 6}));
//
//        assertThat(collinearPoints.getOriginalIndex(18, length), is(new int[] {4, 5}));
//        assertThat(collinearPoints.getOriginalIndex(19, length), is(new int[] {4, 6}));
//
//        assertThat(collinearPoints.getOriginalIndex(20, length), is(new int[] {5, 6}));

    }
}
