
package com.assignment3.mst;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.*;
import java.util.*;

public class GraphReader {
    public static List<Graph> readGraphs(String path) throws Exception {
        String content = Files.readString(Path.of(path));
        JSONObject json = new JSONObject(content);
        JSONArray graphsArray = json.getJSONArray("graphs");

        List<Graph> graphs = new ArrayList<>();
        for (int i = 0; i < graphsArray.length(); i++) {
            JSONObject g = graphsArray.getJSONObject(i);
            int id = g.getInt("id");
            JSONArray nodes = g.getJSONArray("nodes");
            JSONArray edges = g.getJSONArray("edges");

            List<Edge> edgeList = new ArrayList<>();
            for (int j = 0; j < edges.length(); j++) {
                JSONObject e = edges.getJSONObject(j);
                edgeList.add(new Edge(e.getInt("from"), e.getInt("to"), e.getInt("weight")));
            }
            graphs.add(new Graph(id, nodes.length(), edgeList));
        }
        return graphs;
    }
}
