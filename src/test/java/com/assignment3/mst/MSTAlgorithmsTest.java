package com.assignment3.mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MSTAlgorithmsTest {

    @Test
    public void testMSTCorrectnessOnSmallDataset() throws Exception {
        // Load test dataset
        List<Graph> graphs = GraphReader.readGraphs("datasets/input_small.json");

        for (Graph g : graphs) {
            MSTResult kruskal = Kruskal.run(g);
            MSTResult prim = Prim.run(g);

            // ✅ Same total MST cost
            assertEquals(kruskal.totalCost(), prim.totalCost(),
                    "MST total costs should be identical for both algorithms.");

            // ✅ Correct number of edges = V - 1
            assertEquals(g.vertices - 1, kruskal.mstEdges.size(),
                    "Kruskal MST should have V-1 edges.");
            assertEquals(g.vertices - 1, prim.mstEdges.size(),
                    "Prim MST should have V-1 edges.");

            // ✅ Non-negative execution time
            assertTrue(kruskal.timeMs >= 0 && prim.timeMs >= 0,
                    "Execution time must be non-negative.");

            // ✅ Non-negative operation count
            assertTrue(kruskal.operations >= 0 && prim.operations >= 0,
                    "Operation counts must be non-negative.");

            // ✅ Connected and no cycles
            assertTrue(isConnected(g.vertices, kruskal.mstEdges),
                    "Kruskal MST must be connected.");
            assertTrue(isConnected(g.vertices, prim.mstEdges),
                    "Prim MST must be connected.");
        }
    }

    private boolean isConnected(int vertices, List<Edge> edges) {
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) parent[i] = i;

        // Find function
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] == x) return x;
                return parent[x] = applyAsInt(parent[x]);
            }
        };

        // Union edges
        for (Edge e : edges) {
            int a = find.applyAsInt(e.src);
            int b = find.applyAsInt(e.dest);
            parent[a] = b;
        }

        // Check all vertices belong to one component
        int root = find.applyAsInt(0);
        for (int i = 1; i < vertices; i++) {
            if (find.applyAsInt(i) != root) return false;
        }
        return true;
    }
}