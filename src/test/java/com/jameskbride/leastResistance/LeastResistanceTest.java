package com.jameskbride.leastResistance;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeastResistanceTest {

    @Test
    public void givenAMinimalValidStraightPathWhenThePathIsCalculatedThenItShouldBeSuccessful() {
        int[][] map = new int[][] {
            {0, 0, 0, 0, 0}
        };
        PathFinder pathFinder = new PathFinder();

        String result = pathFinder.findPath(map);

        assertEquals("yes", result);
    }

    @Test
    public void givenAMinimalInvalidPathWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {50, 1, 0, 0, 0}
        };
        PathFinder pathFinder = new PathFinder();

        String result = pathFinder.findPath(map);

        assertEquals("no", result);
    }
}
