
package com.assignment3.mst;

import java.util.*;

public class Prim {
    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int n = graph.vertices;
        boolean[] inMST = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        int operations = 0;
        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, inMST);
            inMST[u] = true;

            for (Edge e : graph.edges) {
                if ((e.src == u || e.dest == u)) {
                    int v = (e.src == u) ? e.dest : e.src;
                    if (!inMST[v] && e.weight < key[v]) {
                        key[v] = e.weight;
                        parent[v] = u;
                    }
                }
                operations++;
            }
        }

        List<Edge> mst = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            mst.add(new Edge(parent[i], i, key[i]));
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0; // convert nanoseconds → milliseconds
        return new MSTResult("Prim", mst, timeMs, operations);
    }

    private static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < key.length; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        return minIndex;
    }
}
