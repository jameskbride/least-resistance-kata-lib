package com.jameskbride.leastResistance;

import java.util.Comparator;

import static com.jameskbride.leastResistance.PathFinder.PATH_FOUND;

public class PathResultComparator implements Comparator<PathResult>{

    @Override
    public int compare(PathResult path1, PathResult path2) {
        if (path1 == null && path2 == null) {
            return 0;
        }

        if (path1 != null && path2 == null) {
            return 1;
        } else if (path1 == null && path2 != null){
            return -1;
        }

        if (PATH_FOUND.equals(path1.getPathFound()) && !PATH_FOUND.equals(path2.getPathFound())) {
            return 1;
        } else if (!PATH_FOUND.equals(path1.getPathFound()) && PATH_FOUND.equals(path2.getPathFound())) {
            return -1;
        }

        if (path1.getTotalResistance() < path2.getTotalResistance()) {
            return 1;
        } else if (path1.getTotalResistance() > path2.getTotalResistance()) {
            return -1;
        }

        if (path1.getPath().size() < path2.getPath().size()) {
            return 1;
        } else if (path1.getPath().size() > path2.getPath().size()) {
            return -1;
        }

        return 0;
    }
}
