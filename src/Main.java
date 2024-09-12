import java.util.*;

class Graph {
    private int V;
    private LinkedList<Edge>[] adjList;

    class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    Graph(int V) {
        this.V = V;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adjList[i] = new LinkedList<>();
    }

    void addEdge(int u, int v, int w) {
        adjList[u].add(new Edge(v, w));
    }

    void dijkstra(int src) {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        for (int i = 0; i < V; i++)
          dist[i] = Integer.MAX_VALUE;

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;


            for (Edge v : adjList[u]) {
                if (!sptSet[v.dest] && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + v.weight < dist[v.dest])
                    dist[v.dest] = dist[u] + v.weight;
            }
        }

        printSolution(dist, src);
    }

    int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }

        return minIndex;
    }

    void printSolution(int[] dist,
                       int src) {
        System.out.println("Shortest distance from node " + src);
        for (int i = 0; i < V; i++)
            if (i != src)
                System.out.println("To node " + i + " is " + dist[i]);
    }
}

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 4, 5);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 1, 1);
        g.addEdge(4, 3, 3);

        g.dijkstra(0);
    }
}