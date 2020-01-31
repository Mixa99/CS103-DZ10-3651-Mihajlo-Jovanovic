/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadatak4;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Mixa
 */
public class Graph {

    /**
     * @param args the command line arguments 
     *4. Napisati funkciju koja proverava da li je graf cikličan. 
     *Napisati program koji testira ovu funkciju.
     */
    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();

            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    Boolean isCyclic() {
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                if (isCyclicUtil(u, visited, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String args[]) {
        Graph g1 = new Graph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        System.out.print("Graf g1 ");
        if (g1.isCyclic()) {
            System.out.println("ima kruzni put");
        } else {
            System.out.println("nema kruzni put");
        }

        Graph g2 = new Graph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        System.out.print("Graf g2 ");
        if (g2.isCyclic()) {
            System.out.println("ima kruzni put");
        } else {
            System.out.println("nema kruzni put");
        }
    }
}
