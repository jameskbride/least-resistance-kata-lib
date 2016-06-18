package com.jameskbride.leastResistance;

import org.junit.Before;
import org.junit.Test;

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
}
