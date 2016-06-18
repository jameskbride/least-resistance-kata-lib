package com.jameskbride.leastResistance;

public class PathFinder {

    public static final int RESISTANCE_THRESHHOLD = 50;
    public static final String PATH_NOT_FOUND = "no";
    public static final String PATH_FOUND = "yes";

    public PathResult findPath(int[][] map) {

        Coord startingCoords = getStartingCoordinates(map);

        PathResult pathResult = new PathResult();

        return getPathResult(map, startingCoords, pathResult);
    }

    private PathResult getPathResult(int[][] map, Coord startingCoords, PathResult pathResult) {
        int columnIndex = startingCoords.x;

        int existingPathResistance = pathResult.getTotalResistance();
        int newPathResistance = existingPathResistance + map[0][columnIndex];
        if (newPathResistance > RESISTANCE_THRESHHOLD) {
            pathResult.setPathFound(PATH_NOT_FOUND);

            return pathResult;
        }

        pathResult.setTotalResistance(newPathResistance);
        pathResult.setPathFound(PATH_FOUND);
        pathResult.addPathLeg(startingCoords.y + 1);

        int nextColumnIndex = startingCoords.x + 1;
        if (nextColumnIndex < map[0].length) {
            return getPathResult(map, new Coord(startingCoords.x + 1, startingCoords.y), pathResult);
        }

        return pathResult;
    }

    private Coord getStartingCoordinates(int[][] map) {
        int leastResistantRowIndex = 0;
        int leastResistance = Integer.MAX_VALUE;
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            if (map[rowIndex][0] < leastResistance) {
                leastResistantRowIndex = rowIndex;
                leastResistance = map[rowIndex][0];
            }
        }

        return new Coord(0, leastResistantRowIndex);
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
