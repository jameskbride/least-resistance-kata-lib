package com.jameskbride.leastResistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jameskbride.leastResistance.PathResult.PATH_NOT_FOUND;

public class PathFinder {

    public static final int MINIMUM_ROW_COUNT = 1;
    public static final int MINIMUM_COLUMN_COUNT = 5;
    public static final int MAXIMUM_ROW_COUNT = 10;
    public static final int MAXIMUM_COLUMN_COUNT = 100;

    public PathResult findPath(int[][] map) {
        checkForValidMapSize(map);

        List<PathResult> pathResults = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            PathResult pathResult = new PathResult();
            getPathResult(map, new Coord(0, rowIndex), pathResult, pathResults);
        }

        Collections.sort(pathResults, new PathResultComparator());

        return pathResults.isEmpty() ? new PathResult(PATH_NOT_FOUND, 0, new ArrayList<Integer>()) : pathResults.get(0);
    }

    private void checkForValidMapSize(int[][] map) {
        if (map.length < MINIMUM_ROW_COUNT) {
            throw new IllegalArgumentException("There must be at least " + MINIMUM_ROW_COUNT + " rows.");
        }

        if (map[0].length < MINIMUM_COLUMN_COUNT) {
            throw new IllegalArgumentException("There must be at least " + MINIMUM_COLUMN_COUNT + " columns.");
        }

        if (map.length > MAXIMUM_ROW_COUNT) {
            throw new IllegalArgumentException("There can be no more than " + MAXIMUM_ROW_COUNT + " rows.");
        }

        if (map[0].length > MAXIMUM_COLUMN_COUNT) {
            throw new IllegalArgumentException("There can be no more than " + MAXIMUM_COLUMN_COUNT + " columns.");
        }
    }

    private void getPathResult(int[][] map, Coord startingCoords, PathResult pathResult, List<PathResult> possiblePaths) {
        int columnIndex = startingCoords.x;

        int existingPathResistance = pathResult.getTotalResistance();
        int newPathResistance = existingPathResistance + map[startingCoords.y][columnIndex];
        if (newPathResistance > PathResult.RESISTANCE_THRESHOLD) {
            return;
        } else {
            pathResult.addPathLeg(startingCoords.y + 1);
            pathResult.setTotalResistance(newPathResistance);
            possiblePaths.add(pathResult);

            int nextColumnIndex = startingCoords.x + 1;
            if (nextColumnIndex < map[0].length) {
                int centerRowIndex = startingCoords.y;
                getPathResultByCoordinates(map, pathResult, possiblePaths, nextColumnIndex, centerRowIndex);

                int topRowIndex = startingCoords.y - 1;
                getPathResultByCoordinates(map, pathResult, possiblePaths, nextColumnIndex, topRowIndex);

                int bottomRowIndex = startingCoords.y + 1;
                getPathResultByCoordinates(map, pathResult, possiblePaths, nextColumnIndex, bottomRowIndex);
            }

            if (pathResult.getPath().size() == map[0].length) {
                pathResult.setPathFound(PathResult.PATH_FOUND);
            }
        }

    }

    private void getPathResultByCoordinates(int[][] map, PathResult pathResult, List<PathResult> possiblePaths, int columnIndex, int rowIndex) {
        rowIndex = wrapRowsIfNecessary(map, rowIndex);

        PathResult topPathResult = new PathResult(pathResult.getPathFound(), pathResult.getTotalResistance(), new ArrayList<>(pathResult.getPath()));
        getPathResult(map, new Coord(columnIndex, rowIndex), topPathResult, possiblePaths);
    }

    private int wrapRowsIfNecessary(int[][] map, int rowIndex) {
        if (rowIndex < 0) {
            rowIndex = map.length - 1;
        } else if (rowIndex == map.length) {
            rowIndex = 0;
        }

        return rowIndex;
    }

    private class Coord {
        int x;
        int y;

        public Coord(int columnIndex, int rowIndex) {
            this.x = columnIndex;
            this.y = rowIndex;
        }
    }
}
