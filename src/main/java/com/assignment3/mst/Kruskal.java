package com.assignment3.mst;

import java.util.*;

public class Kruskal {
    public static MSTResult run(Graph graph) {
        long start = System.nanoTime(); // start precise timer

        DisjointSet ds = new DisjointSet(graph.vertices);
        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        sortedEdges.sort(Comparator.comparingInt(e -> e.weight));

        List<Edge> mst = new ArrayList<>();
        int operations = 0;

        for (Edge e : sortedEdges) {
            int root1 = ds.find(e.src);
            int root2 = ds.find(e.dest);
            operations++;

            if (root1 != root2) {
                mst.add(e);
                ds.union(root1, root2);
            }

            // stop if MST complete
            if (mst.size() == graph.vertices - 1)
                break;
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0; // convert nanoseconds to milliseconds

        return new MSTResult("Kruskal", mst, timeMs, operations);
    }
}