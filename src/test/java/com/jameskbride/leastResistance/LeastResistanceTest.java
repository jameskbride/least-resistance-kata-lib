package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void givenAMinimalValidPathWhenThePathIsCalculatedThenItShouldBeSuccessful() {
        int[][] map = new int[][] {
            {0, 0, 0, 0, 0}
        };

        String result = pathFinder.findPath(map).getPathFound();

        assertEquals(PATH_FOUND, result);
    }

    @Test
    public void givenAMinimalInvalidPathWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD + 1, 0, 0, 0, 0}
        };

        String result = pathFinder.findPath(map).getPathFound();

        assertEquals(PATH_NOT_FOUND, result);
    }

    @Test
    public void givenAMinimalValidPathWhenThePathIsCalculatedThenItShouldReturnTheTotalResistance() {
        int expectedTotalResistance = 1;
        int[][] map = new int[][] {
                {expectedTotalResistance, 0, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(expectedTotalResistance, actualTotalResistance);
    }

    @Test
    public void givenAMinimalInvalidPathWhenThePathIsCalculatedThenItShouldReturnTheTotalResistance() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD, 1, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(RESISTANCE_THRESHHOLD, actualTotalResistance);
    }

    @Test
    public void givenAPathThatIsTooResistantInTheFirstStepWhenThePathIsCalculatedThenItShouldReturnZero() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD + 1, 0, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(0, actualTotalResistance);
    }

    @Test
    public void givenAMinimalValidPathWhenThePathIsCalculatedThenItShouldReturnThePath() {
        int[][] map = new int[][] {
                {0, 0, 0, 0, 0}
        };

        List<Integer> path = pathFinder.findPath(map).getPath();

        assertEquals(Arrays.asList(1, 1, 1, 1, 1), path);
    }
}
