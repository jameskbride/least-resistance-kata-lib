package com.jameskbride.leastResistance;

public class PathFinder {

    public static final int RESISTANCE_THRESHHOLD = 50;
    public static final String PATH_NOT_FOUND = "no";
    public static final String PATH_FOUND = "yes";

    public PathResult findPath(int[][] map) {
        int pathResistance = 0;
        boolean pathFound = true;
        for (int columnIndex = 0; columnIndex < map[0].length; columnIndex++) {
            int existingPathResistance = pathResistance;
            int newPathResistance = existingPathResistance + map[0][columnIndex];
            if (newPathResistance > RESISTANCE_THRESHHOLD) {
                pathFound = false;
                break;
            } else {
                pathResistance = newPathResistance;
            }
        }

        PathResult pathResult = new PathResult();
        pathResult.setTotalResistance(pathResistance);

        String pathFoundString = (pathFound) ? PATH_FOUND: PATH_NOT_FOUND;
        pathResult.setPathFound(pathFoundString);

        return pathResult;
    }
}
