package com.jameskbride.leastResistance;

public class PathFinder {

    public static final int RESISTANCE_THRESHHOLD = 50;
    public static final String PATH_NOT_FOUND = "no";
    public static final String PATH_FOUND = "yes";

    public PathResult findPath(int[][] map) {
        int pathResistance = 0;
        for (int columnIndex = 0; columnIndex < map[0].length; columnIndex++) {
            pathResistance += map[0][columnIndex];
        }

        PathResult pathResult = new PathResult();
        pathResult.setTotalResistance(pathResistance);

        String pathFound = (pathResistance > RESISTANCE_THRESHHOLD) ? PATH_NOT_FOUND : PATH_FOUND;
        pathResult.setPathFound(pathFound);

        return pathResult;
    }
}
