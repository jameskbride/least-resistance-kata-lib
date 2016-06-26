package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jameskbride.leastResistance.PathFinder.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeastResistanceTest {

    private PathFinder pathFinder;

    @Before
    public void setUp() {
        pathFinder = new PathFinder();
    }

    @Test
    public void givenAMapWithAMinimalValidPathWhenThePathIsCalculatedThenItShouldBeSuccessful() {
        int[][] map = new int[][] {
            {0, 0, 0, 0, 0}
        };

        String result = pathFinder.findPath(map).getPathFound();

        assertEquals(PATH_FOUND, result);
    }

    @Test
    public void givenAMapWithNoInitialValidLegsWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD + 1, 0, 0, 0, 0}
        };

        String result = pathFinder.findPath(map).getPathFound();

        assertEquals(PATH_NOT_FOUND, result);
    }

    @Test
    public void givenAMapWithWithAPartialValidPathWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD, 1, 0, 0, 0}
        };

        String result = pathFinder.findPath(map).getPathFound();

        assertEquals(PATH_NOT_FOUND, result);
    }

    @Test
    public void givenAMapWithAMinimalValidPathWhenThePathIsCalculatedThenItShouldReturnTheTotalResistance() {
        int expectedTotalResistance = 1;
        int[][] map = new int[][] {
                {expectedTotalResistance, 0, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(expectedTotalResistance, actualTotalResistance);
    }

    @Test
    public void givenAMapWithAMinimalInvalidPathWhenThePathIsCalculatedThenItShouldReturnTheTotalResistance() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD, 1, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(RESISTANCE_THRESHHOLD, actualTotalResistance);
    }

    @Test
    public void givenAMapThatIsTooResistantInTheFirstStepWhenThePathIsCalculatedThenItShouldReturnZero() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD + 1, 0, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(0, actualTotalResistance);
    }

    @Test
    public void givenAMapWithAMinimalValidPathWhenThePathIsCalculatedThenItShouldReturnThePath() {
        int[][] map = new int[][] {
                {0, 0, 0, 0, 0}
        };

        List<Integer> path = pathFinder.findPath(map).getPath();

        assertEquals(Arrays.asList(1, 1, 1, 1, 1), path);
    }

    @Test
    public void givenAMapThatIsTooResistantInTheFirstStepWhenThePathIsCalculatedThenPathContainsNoLegs() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD + 1, 0, 0, 0, 0}
        };

        List<Integer> path = pathFinder.findPath(map).getPath();

        assertTrue(path.isEmpty());
    }

    @Test
    public void givenAMapWithAMinimalInvalidPathWhenThePathIsCalculatedThenThePathShouldContainTheValidLegs() {
        int[][] map = new int[][] {
                {RESISTANCE_THRESHHOLD, 1, 0, 0, 0}
        };

        List<Integer> path = pathFinder.findPath(map).getPath();

        assertEquals(1, path.size());
        assertEquals(Integer.valueOf(1), path.get(0));
    }

    @Test
    public void givenAMapWithAComplexCompletePathWhenThePathIsCalculatedThenItShouldReturnThePath() {
        int[][] map = new int[][] {
                {50, 0, 0, 0, 0},
                {0, 50, 50, 50, 50}
        };

        List<Integer> expectedPath = Arrays.asList(2, 1, 1, 1, 1);

        assertEquals(expectedPath, pathFinder.findPath(map).getPath());
    }
}
