
package com.assignment3.mst;

public class DisjointSet {
    int[] parent;
    public DisjointSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
    void union(int a, int b) { parent[find(a)] = find(b); }
}
