package com.jameskbride.leastResistance;

import java.util.ArrayList;
import java.util.List;

public class PathResult {
    private String pathFound;
    private int totalResistance;
    private List<Integer> path = new ArrayList<>();

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
}
