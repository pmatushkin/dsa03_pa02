import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here

        // length of the adj array
        int n = adj.length;

        // the array of visited vertices
        int[] v = new int[n];

        // the array of 'removed' vertices;
        // removed vertices are the sink vertices
        // marked as removed during the DFS
        int[] r = new int[n];

        // while we have unexplored vertices...
        for (int i = 0; i < n; i++) {
            if (v[i] == 0) {
                // ... continue doing DFS
                if (explore(adj, v, r, i)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private static boolean explore(ArrayList<Integer>[] adj, int[] v, int[] r, int x) {
        boolean isCycle = false;

        // mark the current vertex as visited
        v[x] = 1;

        // get the list of edges connected to the current vertex
        ArrayList<Integer> edges = adj[x];

        for (int e : edges) {
            if (v[e] == 1) {
                // if the current vertex is visited ...
                if (r[e] == 1) {
                    // ... visited AND removed,
                    // continue to the next adjacent vertex
                    continue;
                } else {
                    // ... visited AND NOT removed,
                    // then it is a cycle; exit
                    return true;
                }
            } else {
                // if the current vertex is NOT visited,
                // begin exploring
                isCycle |= explore(adj, v, r, e);

                // if a cycle is found, no need to continue exploring
                if (isCycle) {
                    break;
                }
            }
        }

        // when all adjacent vertices are visited, remove the current vertex
        r[x] = 1;

        return isCycle;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

