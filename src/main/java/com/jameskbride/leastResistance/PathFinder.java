package com.jameskbride.leastResistance;

public class PathFinder {

    public static final int RESISTANCE_THRESHHOLD = 50;
    public static final String PATH_NOT_FOUND = "no";
    public static final String PATH_FOUND = "yes";

    public PathResult findPath(int[][] map) {
        int pathResistance = 0;
        int rowIndex = 0;

        PathResult pathResult = new PathResult();

        for (int columnIndex = 0; columnIndex < map[rowIndex].length; columnIndex++) {
            int existingPathResistance = pathResistance;
            int newPathResistance = existingPathResistance + map[0][columnIndex];
            if (newPathResistance > RESISTANCE_THRESHHOLD) {
                pathResult.setPathFound(PATH_NOT_FOUND);
                
                break;
            } else {
                pathResistance = newPathResistance;
                pathResult.setTotalResistance(pathResistance);
                pathResult.setPathFound(PATH_FOUND);
                pathResult.addPathLeg(rowIndex + 1);
            }
        }

        return pathResult;
    }
}
