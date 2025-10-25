package com.assignment3.mst;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int id;
    public int vertices;
    public List<Edge> edges;

    public Graph(int id, int vertices) {
        this.id = id;
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public Graph(int id, int vertices, List<Edge> edges) {
        this.id = id;
        this.vertices = vertices;
        this.edges = edges;
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public void printGraph() {
        System.out.println("Graph " + id + " (" + vertices + " vertices):");
        for (Edge e : edges) {
            System.out.println("  " + e);
        }
    }
}