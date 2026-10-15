import java.util.Arrays;

public class KruskalSimple {

    // Simple Find function with path compression
    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent, parent[i]);
    }

    public static void main(String[] args) {
        int V = 4; // Number of vertices

        // Each row represents an edge: {source, destination, weight}
        int[][] edges = {
            {0, 1, 10},
            {0, 2, 6},
            {0, 3, 5},
            {1, 3, 15},
            {2, 3, 4}
        };

        // Step 1: Sort edges based on their weight (3rd column)
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        // Parent array for cycle detection
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int minCost = 0;
        System.out.println("Edges in MST:");

        // Step 2: Pick edges one by one
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            // If roots are different, adding this edge won't form a cycle
            if (rootU != rootV) {
                System.out.println(u + " - " + v + " : " + weight);
                minCost += weight;
                parent[rootU] = rootV; // Union
            }
        }

        System.out.println("Minimum Cost Spanning Tree: " + minCost);
    }
}
