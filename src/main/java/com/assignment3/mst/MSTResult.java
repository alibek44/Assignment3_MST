
package com.assignment3.mst;

import java.util.List;

public class MSTResult {
    String algorithm;
    List<Edge> mstEdges;
    double timeMs;
    int operations;
    public MSTResult(String algo, List<Edge> edges, double t, int ops) {
        algorithm = algo;
        mstEdges = edges;
        timeMs = t;
        operations = ops;
    }
    int totalCost() {
        return mstEdges.stream().mapToInt(e -> e.weight).sum();
    }
}
