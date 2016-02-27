package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by maria on 27.02.2016.
 */
public class PointTests {
    @Test
    public void testDistance() {
        Point p1 = new Point(5, 4);
        Point p2 = new Point(2, 3);
        Assert.assertEquals(Point.distance(p1,p2), 3.1622776601683795);
    }
}
