package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by maria on 27.02.2016.
 */
public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square(6);
        Assert.assertEquals(s.area(), 36.0);
    }
}