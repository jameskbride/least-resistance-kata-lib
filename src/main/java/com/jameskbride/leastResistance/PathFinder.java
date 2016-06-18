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

        if (pathResistance > RESISTANCE_THRESHHOLD) {
            PathResult pathResult = new PathResult();
            pathResult.setPathFound(PATH_NOT_FOUND);
            pathResult.setTotalResistance(pathResistance);

            return  pathResult;
        }

        PathResult pathResult = new PathResult();
        pathResult.setPathFound(PATH_FOUND);
        pathResult.setTotalResistance(pathResistance);
        return pathResult;
    }
}
