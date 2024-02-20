package Question3;


import java.util.*;

public class MinHeap {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Constructor for Edge objects
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class DisjointSet {
        int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);

            if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else {
                parent[rooty] = rootx;
                rank[rootx]++;
            }
        }
    }

    static void kruskalMST(List<Edge> edges, int V) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge edge : edges) {
            pq.add(edge);
        }

        DisjointSet ds = new DisjointSet(V);

        int e = 0;
        int totalWeight = 0;

        while (!pq.isEmpty() && e < V - 1) {
            Edge nextEdge = pq.poll();
            int u = nextEdge.src;
            int v = nextEdge.dest;

            int setU = ds.find(u);
            int setV = ds.find(v);

            if (setU != setV) {
                // Add edge to MST
                System.out.println(u + " -- " + v + " == " + nextEdge.weight);
                totalWeight += nextEdge.weight;
                e++;

                ds.union(setU, setV);
            }
        }

        System.out.println("Total weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 4; // Number of vertices

        // Create an array of Edge objects
        Edge[] edges = new Edge[] {
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 2, 3),
                new Edge(2, 3, 8),
                new Edge(1, 3, 15)
        };

        // Convert the array to an ArrayList using Arrays.asList() and a new ArrayList:
        List<Edge> edgeList = new ArrayList<>(Arrays.asList(edges));

        kruskalMST(edgeList, V);
    }
}