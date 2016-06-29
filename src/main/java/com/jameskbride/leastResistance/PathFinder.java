package com.jameskbride.leastResistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathFinder {

    public PathResult findPath(int[][] map) {
        if (map[0].length < 5) {
            throw new IllegalArgumentException("There must be at least 5 columns.");
        }
        List<PathResult> pathResults = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            PathResult pathResult = new PathResult();
            pathResult = getPathResult(map, new Coord(0, rowIndex), pathResult);
            pathResults.add(pathResult);
        }

        Collections.sort(pathResults, new PathResultComparator());

        PathResult bestPath = pathResults.get(0);

        return bestPath;
    }

    private PathResult getPathResult(int[][] map, Coord startingCoords, PathResult pathResult) {
        int columnIndex = startingCoords.x;

        int existingPathResistance = pathResult.getTotalResistance();
        int newPathResistance = existingPathResistance + map[startingCoords.y][columnIndex];
        if (newPathResistance > PathResult.RESISTANCE_THRESHOLD) {
            pathResult.setPathFound(PathResult.PATH_NOT_FOUND);

            return pathResult;
        }

        List<PathResult> possiblePaths = new ArrayList<>();
        pathResult.setTotalResistance(newPathResistance);
        pathResult.setPathFound(PathResult.PATH_FOUND);
        pathResult.addPathLeg(startingCoords.y + 1);

        int nextColumnIndex = startingCoords.x + 1;
        if (nextColumnIndex < map[0].length) {
            int centerRowIndex = startingCoords.y;
            getPathResultByCoordinates(map, pathResult, possiblePaths, nextColumnIndex, centerRowIndex);

            int topRowIndex = startingCoords.y - 1;
            getPathResultByCoordinates(map, pathResult, possiblePaths, nextColumnIndex, topRowIndex);

            int bottomRowIndex = startingCoords.y + 1;
            getPathResultByCoordinates(map, pathResult, possiblePaths, nextColumnIndex, bottomRowIndex);
        }

        Collections.sort(possiblePaths, new PathResultComparator());
        if (!possiblePaths.isEmpty()) {
            return possiblePaths.get(0);
        }

        if (pathResult.getPath().size() < map[0].length) {
            pathResult.setPathFound(PathResult.PATH_NOT_FOUND);
        }

        return pathResult;

    }

    private void getPathResultByCoordinates(int[][] map, PathResult pathResult, List<PathResult> possiblePaths, int columnIndex, int rowIndex) {
        rowIndex = wrapRowsIfNecessary(map, rowIndex);

        PathResult topPathResult = new PathResult(pathResult.getPathFound(), pathResult.getTotalResistance(), new ArrayList<>(pathResult.getPath()));
        topPathResult = getPathResult(map, new Coord(columnIndex, rowIndex), topPathResult);
        if (PathResult.PATH_FOUND.equals(topPathResult.getPathFound())) {
            possiblePaths.add(topPathResult);
        }
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
