package com.assignment3.mst;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        // Input and output dataset pairs
        String[][] datasetFiles = {
                {"datasets/input_small.json", "output_small.json"},
                {"datasets/input_medium.json", "output_medium.json"},
                {"datasets/input_large.json", "output_large.json"},
                {"datasets/input_extra.json", "output_extra.json"}
        };

        // Prepare CSV summary file
        PrintWriter csv = new PrintWriter(new FileWriter("summary.csv"));
        csv.println("Dataset,GraphID,PrimCost,KruskalCost,PrimTimeMs,KruskalTimeMs,PrimOps,KruskalOps,SameCost");

        // Process each dataset
        for (String[] pair : datasetFiles) {
            String inputFile = pair[0];
            String outputFile = pair[1];
            System.out.println("Processing dataset: " + inputFile);

            // Read graphs from JSON
            List<Graph> graphs = GraphReader.readGraphs(inputFile);

            JSONArray resultsArray = new JSONArray();

            int graphId = 1;
            for (Graph g : graphs) {
                JSONObject graphJson = new JSONObject();
                graphJson.put("id", graphId);
                graphJson.put("vertices", g.vertices);
                graphJson.put("edges_count", g.edges.size());

                // Run both algorithms
                MSTResult prim = Prim.run(g);
                MSTResult kruskal = Kruskal.run(g);

                // Build JSON output for this graph
                JSONObject primJson = new JSONObject();
                primJson.put("cost", prim.totalCost());
                primJson.put("time_ms", prim.timeMs);
                primJson.put("operations", prim.operations);

                JSONObject kruskalJson = new JSONObject();
                kruskalJson.put("cost", kruskal.totalCost());
                kruskalJson.put("time_ms", kruskal.timeMs);
                kruskalJson.put("operations", kruskal.operations);

                graphJson.put("prim", primJson);
                graphJson.put("kruskal", kruskalJson);
                graphJson.put("same_cost", prim.totalCost() == kruskal.totalCost());

                resultsArray.put(graphJson);

                // Write one line in CSV
                csv.printf("%s,%d,%d,%d,%.3f,%.3f,%d,%d,%s%n",
                        inputFile, graphId,
                        prim.totalCost(), kruskal.totalCost(),
                        prim.timeMs, kruskal.timeMs,
                        prim.operations, kruskal.operations,
                        (prim.totalCost() == kruskal.totalCost()) ? "Yes" : "No");

                // Print concise console summary
                System.out.printf("Graph %d | Vertices: %d | Edges: %d%n", graphId, g.vertices, g.edges.size());
                System.out.printf("  Prim: cost=%d, time=%.3f ms, ops=%d%n", prim.totalCost(), prim.timeMs, prim.operations);
                System.out.printf("  Kruskal: cost=%d, time=%.3f ms, ops=%d%n", kruskal.totalCost(), kruskal.timeMs, kruskal.operations);
                System.out.printf("  Same MST cost? %s%n%n", (prim.totalCost() == kruskal.totalCost()) ? "Yes ✅" : "No ❌");

                graphId++;
            }

            // Save dataset output JSON
            JSONObject outputRoot = new JSONObject();
            outputRoot.put("dataset", inputFile);
            outputRoot.put("results", resultsArray);

            try (FileWriter file = new FileWriter(outputFile)) {
                file.write(outputRoot.toString(4)); // pretty print
            }

            System.out.println("✅ Results saved to " + outputFile);
        }

        csv.close();
        System.out.println("📊 Summary written to summary.csv");
    }
}