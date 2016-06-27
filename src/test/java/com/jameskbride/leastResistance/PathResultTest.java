package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.jameskbride.leastResistance.PathResult.PATH_FOUND;
import static org.junit.Assert.assertEquals;

public class PathResultTest {

    private PathResult pathResult;

    @Before
    public void setUp() {
        pathResult = new PathResult();
    }

    @Test
    public void whenTheNextLegOfThePathIsAddedThenTheLastLegOfThePathIsTheNextLeg() {
        pathResult.addPathLeg(1);

        assertEquals(Integer.valueOf(1), pathResult.getPath().get(0));
    }

    @Test
    public void whenPathResultIsStringifiedThenPathFoundIsOutput() {
        pathResult = createPopulatedPathResult();

        String[] output = pathResult.toString().split("\n");

        assertEquals(PATH_FOUND, output[0]);
    }

    private PathResult createPopulatedPathResult() {
        return new PathResult(PATH_FOUND, 50, Arrays.asList(10, 10, 10, 10, 10));
    }

    @Test
    public void whenPathResultIsStringifiedThenTheTotalResistanceIsOutput() {
        pathResult = createPopulatedPathResult();

        String[] output = pathResult.toString().split("\n");

        assertEquals("50", output[1]);
    }

    @Test
    public void whenPathResultIsStringifiedThenThePathIsOutput() {
        pathResult = createPopulatedPathResult();

        String[] output = pathResult.toString().split("\n");

        assertEquals("10 10 10 10 10", output[2]);
    }

    @Test
    public void whenPathResultIsStringifiedThenTheEntireResultIsOutput() {
        pathResult = createPopulatedPathResult();

        assertEquals(PATH_FOUND + "\n" + 50 + "\n" + "10 10 10 10 10", pathResult.toString());
    }
}
