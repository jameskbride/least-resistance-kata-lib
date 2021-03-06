package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeastResistanceTest {

    private PathFinder pathFinder;

    @Before
    public void setUp() {
        pathFinder = new PathFinder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAMapWithLessThanFiveColumnsWhenThePathIsCalculatedThenAnExceptionIsThrown() {
        int[][] map = new int [][] {
                {0, 0, 0 ,0}
        };

        pathFinder.findPath(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAMapWithNoRowsWhenThePathIsCalculatedThenAnExceptionIsThrown() {
        int[][] map = new int[0][5];

        pathFinder.findPath(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAMapWithMoreThan10RowsWhenThePathIsCalculatedThenAnExceptionIsThrown() {
        int[][] map = new int[11][5];

        pathFinder.findPath(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAMapWithMoreThan100ColumnsWhenThePathIsCalculatedThenAnExceptionIsThrown() {
        int[][] map = new int[10][101];

        pathFinder.findPath(map);
    }

    @Test
    public void givenAMapWithAMinimalValidPathWhenThePathIsCalculatedThenItShouldBeSuccessful() {
        int[][] map = new int[][] {
            {0, 0, 0, 0, 0}
        };

        PathResult pathResult = pathFinder.findPath(map);
        String result = pathResult.getPathFound();
        System.out.println(pathResult.toString());

        assertEquals(PathResult.PATH_FOUND, result);
    }

    @Test
    public void givenAMapWithNoInitialValidLegsWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {PathResult.RESISTANCE_THRESHOLD + 1, 0, 0, 0, 0}
        };

        String result = pathFinder.findPath(map).getPathFound();

        assertEquals(PathResult.PATH_NOT_FOUND, result);
    }

    @Test
    public void givenAMapWithWithAPartialValidPathWhenThePathIsCalculatedThenItShouldNotBeSuccessful() {
        int[][] map = new int[][] {
                {PathResult.RESISTANCE_THRESHOLD, 1, 0, 0, 0}
        };

        PathResult pathResult = pathFinder.findPath(map);
        String result = pathResult.getPathFound();

        assertEquals(Arrays.asList(1), pathResult.getPath());
        assertEquals(PathResult.PATH_NOT_FOUND, result);
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
                {PathResult.RESISTANCE_THRESHOLD, 1, 0, 0, 0}
        };

        int actualTotalResistance = pathFinder.findPath(map).getTotalResistance();

        assertEquals(PathResult.RESISTANCE_THRESHOLD, actualTotalResistance);
    }

    @Test
    public void givenAMapThatIsTooResistantInTheFirstStepWhenThePathIsCalculatedThenItShouldReturnZero() {
        int[][] map = new int[][] {
                {PathResult.RESISTANCE_THRESHOLD + 1, 0, 0, 0, 0}
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
                {PathResult.RESISTANCE_THRESHOLD + 1, 0, 0, 0, 0}
        };

        List<Integer> path = pathFinder.findPath(map).getPath();

        assertTrue(path.isEmpty());
    }

    @Test
    public void givenAMapWithAMinimalInvalidPathWhenThePathIsCalculatedThenThePathShouldContainTheValidLegs() {
        int[][] map = new int[][] {
                {PathResult.RESISTANCE_THRESHOLD, 1, 0, 0, 0}
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

    @Test
    public void givenAMapWithAComplexCompletePathWhichWrapsFromTheTopToTheBottomWhenThePathIsCalculatedThenItShouldReturnThePath() {
        int[][] map = new int[][] {
                {25, 25, 50, 50, 50},
                {50, 50, 50, 50, 50},
                {50, 50, 0, 0, 0}
        };

        List<Integer> expectedPath = Arrays.asList(1, 1, 3, 3, 3);

        PathResult pathResult = pathFinder.findPath(map);
        assertTrue(PathResult.PATH_FOUND.equals(pathResult.getPathFound()));
        assertEquals(expectedPath, pathResult.getPath());
    }

    @Test
    public void givenAMapWithAComplexCompletePathWhichWrapsFromTheBottomToTheTopWhenThePathIsCalculatedThenItShouldReturnThePath() {
        int[][] map = new int[][] {
                {100, 50, 0, 0, 0},
                {100, 50, 50, 50, 50},
                {50, 0, 50, 50, 50}
        };

        List<Integer> expectedPath = Arrays.asList(3, 3, 1, 1, 1);

        PathResult pathResult = pathFinder.findPath(map);
        assertTrue(PathResult.PATH_FOUND.equals(pathResult.getPathFound()));
        assertEquals(expectedPath, pathResult.getPath());
    }

    @Test
    public void givenAMapWithHighResistanceAndNoPathWhenThePathIsCalculatedThenItShouldReturnThePath() {
        int[][] map = new int[][] {
                {19, 10, 19, 10, 19},
                {21, 23, 20, 19, 12},
                {20, 12, 20, 11, 10}
        };

        List<Integer> expectedPath = Arrays.asList(1, 1, 1);

        PathResult pathResult = pathFinder.findPath(map);
        assertEquals(48, pathResult.getTotalResistance());
        assertTrue(PathResult.PATH_NOT_FOUND.equals(pathResult.getPathFound()));
        assertEquals(expectedPath, pathResult.getPath());
    }
}
