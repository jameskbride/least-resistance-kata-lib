package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

import static com.jameskbride.leastResistance.PathFinder.PATH_FOUND;
import static com.jameskbride.leastResistance.PathFinder.PATH_NOT_FOUND;
import static com.jameskbride.leastResistance.PathFinder.RESISTANCE_THRESHHOLD;
import static org.junit.Assert.assertEquals;

public class LeastResistanceTest {

    private PathFinder pathFinder;

    @Before
    public void setUp() {
        pathFinder = new PathFinder();
    }

    @Test
    public void givenAMinimalValidStraightPathWhenThePathIsCalculatedThenItShouldBeSuccessful() {
        int[][] map = new int[][] {
            {0, 0, 0, 0, 0}
        };

        String result = pathFinder.findPath(map);

        assertEquals(PATH_FOUND, result);
    }

    @Test
    public void givenAMinimalInvalidPathWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD, 1, 0, 0, 0}
        };

        String result = pathFinder.findPath(map);

        assertEquals(PATH_NOT_FOUND, result);
    }
}
