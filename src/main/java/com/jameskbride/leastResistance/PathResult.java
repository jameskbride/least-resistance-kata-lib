package com.jameskbride.leastResistance;

import java.util.ArrayList;
import java.util.List;

public class PathResult {
    public static final String PATH_NOT_FOUND = "No";
    public static final String PATH_FOUND = "Yes";
    public static final int RESISTANCE_THRESHOLD = 50;

    private String pathFound;
    private int totalResistance;
    private List<Integer> path = new ArrayList<>();

    public PathResult() {
    }

    public PathResult(String pathFound, int totalResistance, List<Integer> path) {
        this.pathFound = pathFound;
        this.totalResistance = totalResistance;
        this.path = path;
    }

    public void setPathFound(String pathFound) {
        this.pathFound = pathFound;
    }

    public String getPathFound() {
        return pathFound;
    }

    public int getTotalResistance() {
        return totalResistance;
    }

    public void setTotalResistance(int totalResistance) {
        this.totalResistance = totalResistance;
    }

    public List<Integer> getPath() {
        return path;
    }

    public void addPathLeg(int nextLeg) {
        path.add(nextLeg);
    }

    @Override
    public String toString() {
        return pathFound + "\n" + totalResistance + "\n" + stringifyPath();
    }

    private String stringifyPath() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer pathLeg : path) {
            stringBuffer.append(pathLeg);
            stringBuffer.append(" ");
        }
        String pathStrTemp = stringBuffer.toString();
        return pathStrTemp.substring(0, pathStrTemp.length() - 1);
    }
}
