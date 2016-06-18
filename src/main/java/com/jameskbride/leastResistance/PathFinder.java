package com.jameskbride.leastResistance;

public class PathFinder {
    public String findPath(int[][] map) {
        int pathResistance = 0;
        for (int columnIndex = 0; columnIndex < map[0].length; columnIndex++) {
            pathResistance += map[0][columnIndex];
        }

        if (pathResistance > 50) {
            return "no";
        }

        return "yes";
    }
}
