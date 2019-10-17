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
                        new Point(4, 5),
                        expectedPoint2,

                        new Point(2, 2),
                        new Point(3, 3),
                        expectedPoint1,
                        new Point(4, 5)
                };

        final BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);

        assertThat(bruteCollinearPoints.numberOfSegments(), is(1));

        assertThat(bruteCollinearPoints.segments()[0].toString(), equalTo(new LineSegment(expectedPoint1, expectedPoint2).toString()));
    }


}
