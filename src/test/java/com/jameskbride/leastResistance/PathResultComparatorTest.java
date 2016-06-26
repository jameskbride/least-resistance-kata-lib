package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.jameskbride.leastResistance.PathFinder.PATH_FOUND;
import static com.jameskbride.leastResistance.PathFinder.PATH_NOT_FOUND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PathResultComparatorTest {

    private PathResultComparator comparator;

    @Before
    public void setUp() {
        comparator = new PathResultComparator();
    }

    @Test
    public void aNonNullPathResultIsLessThanANullPath() {
        PathResult pathResult = new PathResult();

        assertTrue(comparator.compare(pathResult, null) < 0);
    }

    @Test
    public void aNullPathResultIsGreaterThanANonNullPath() {
        PathResult pathResult = new PathResult();

        assertTrue(comparator.compare(null, pathResult) > 0);
    }

    @Test
    public void twoNullPathsAreEqual() {
        assertEquals(0, comparator.compare(null, null));
    }

    @Test
    public void aCompletePathResultIsLessThanAnIncompletePath() {
        PathResult pathResult = new PathResult();
        pathResult.setPathFound(PATH_FOUND);

        PathResult inCompletePath = new PathResult();

        assertTrue(comparator.compare(pathResult, inCompletePath) < 0);
    }

    @Test
    public void anIncompletePathIsGreaterThanACompleteResult() {
        PathResult pathResult = new PathResult();
        pathResult.setPathFound(PATH_FOUND);

        PathResult inCompletePath = new PathResult();

        assertTrue(comparator.compare(inCompletePath, pathResult) > 0);
    }

    @Test
    public void anIncompletePathWithEqualResistanceAndAShorterPathIsLessThanAnIncompletePathWithALongerPath() {
        PathResult shorterPathResult = new PathResult(PATH_NOT_FOUND, 0, Arrays.asList(1));
        PathResult longerPathResult = new PathResult(PATH_NOT_FOUND, 0, Arrays.asList(1, 2));

        assertTrue(comparator.compare(shorterPathResult, longerPathResult) < 0);
    }

    @Test
    public void anIncompletePathWithEqualResistanceAndALongerPathIsGreaterThanAnIncompletePathWithAShorterPath() {
        PathResult shorterPathResult = new PathResult(PATH_NOT_FOUND, 0, Arrays.asList(1, 2));
        PathResult longerPathResult = new PathResult(PATH_NOT_FOUND, 0, Arrays.asList(1));

        assertTrue(comparator.compare(shorterPathResult, longerPathResult) > 0);
    }

    @Test
    public void anIncompletePathWithLessResistanceAndAnEqualPathIsLessThanAnIncompletePathWithMoreResistance() {
        PathResult leastResistancePath = new PathResult(PATH_NOT_FOUND, 0, Arrays.asList(1, 2));
        PathResult moreResistancePath = new PathResult(PATH_NOT_FOUND, 1, Arrays.asList(1));

        assertTrue(comparator.compare(leastResistancePath, moreResistancePath) < 0);
    }

    @Test
    public void anIncompletePathWithMoreResistanceAndAnEqualPathIsGreaterThanAnIncompletePathWithAndAnEqualPath() {
        PathResult moreResistancePath = new PathResult(PATH_NOT_FOUND, 1, Arrays.asList(1, 2));
        PathResult leastResistancePath = new PathResult(PATH_NOT_FOUND, 0, Arrays.asList(1, 2));

        assertTrue(comparator.compare(moreResistancePath, leastResistancePath) > 0);
    }

    @Test
    public void twoIncompletePathsWithEqualResistanceAndPathLengthAreEqual() {
        PathResult path1 = new PathResult(PATH_NOT_FOUND, 0, new ArrayList<Integer>());
        PathResult path2 = new PathResult(PATH_NOT_FOUND, 0, new ArrayList<Integer>());

        assertEquals(0, comparator.compare(path1, path2));
    }

    @Test
    public void aCompletePathWithEqualResistanceAndAShorterPathIsLessThanACompletePathWithEqualResistance() {
        PathResult shorterPathResult = new PathResult(PATH_FOUND, 0, Arrays.asList(1));
        PathResult longerPathResult = new PathResult(PATH_FOUND, 0, Arrays.asList(1, 2));

        assertTrue(comparator.compare(shorterPathResult, longerPathResult) < 0);
    }

    @Test
    public void aCompletePathWithEqualResistanceAndALongerPathIsGreaterThanACompletePathWithEqualResistance() {
        PathResult shorterPathResult = new PathResult(PATH_FOUND, 0, Arrays.asList(1));
        PathResult longerPathResult = new PathResult(PATH_FOUND, 0, Arrays.asList(1, 2));

        assertTrue(comparator.compare(longerPathResult, shorterPathResult) > 0);
    }

    @Test
    public void aCompletePathWithLessResistanceAndAnEqualPathLengthIsLessThanACompletePathWithAnEqualPathLength() {
        PathResult leastResistantPath = new PathResult(PATH_FOUND, 0, Arrays.asList(1, 2));
        PathResult moreResistantPath = new PathResult(PATH_FOUND, 1, Arrays.asList(1, 2));

        assertTrue(comparator.compare(leastResistantPath, moreResistantPath) < 0);
    }

    @Test
    public void aCompletePathWithMoreResistanceAndAnEqualPathLengthIsGreaterThanACompletePathWithAnEqualPathLength() {
        PathResult leastResistantPath = new PathResult(PATH_FOUND, 1, Arrays.asList(1, 2));
        PathResult moreResistantPath = new PathResult(PATH_FOUND, 0, Arrays.asList(1, 2));

        assertTrue(comparator.compare(leastResistantPath, moreResistantPath) > 0);
    }

    @Test
    public void twoCompletePathWithEqualResistanceAndPathLengthsAreEqual() {
        PathResult path1 = new PathResult(PATH_FOUND, 0, new ArrayList<Integer>());
        PathResult path2 = new PathResult(PATH_FOUND, 0, new ArrayList<Integer>());

        assertEquals(0, comparator.compare(path1, path2));
    }
}
