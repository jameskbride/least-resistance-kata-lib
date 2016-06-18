package com.jameskbride.leastResistance;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeastResistanceTest {

    @Test
    public void givenAMinimalValidStraightPathWhenThePathIsCalculatedThenItShouldBeSuccessful() {
        int[][] map = new int[][] {
            {1, 1, 1, 1, 1}
        };
        PathFinder pathFinder = new PathFinder();

        String result = pathFinder.findPath(map);

        assertEquals("yes", result);
    }
}
