package com.assignment3.mst;

public class Edge {
    public int src;
    public int dest;
    public int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + src + " - " + dest + " : " + weight + ")";
    }
}