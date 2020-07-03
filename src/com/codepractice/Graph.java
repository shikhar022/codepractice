package edu.codepractice;

import java.util.*;

class Graph {
    int numOfVertices;
    Map<Integer, LinkedList<Integer>> adjMap;

    public Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        adjMap = new HashMap<>();
        for (int i = 0; i < this.numOfVertices; i++) {
            adjMap.put(i + 1, new LinkedList<>());
        }
    }

    public void addEdge(int vertex, int edge) throws GraphException {
        LinkedList<Integer> adjVtx = this.adjMap.get(vertex);
        if (vertex > this.numOfVertices || edge > this.numOfVertices) {
            throw new GraphException("Cannot add edge for greater than::: " + this.numOfVertices);
        }
        adjVtx.add(edge);
        this.adjMap.put(vertex, adjVtx);
    }

    public void dfs(int v) throws GraphException {
        if (v > this.numOfVertices) {
            throw new GraphException("Search vertex should be less than::: " + this.numOfVertices);
        }
        boolean[] visited = new boolean[this.numOfVertices + 1];
        Stack<Integer> stack = new Stack<>();

        stack.push(v); // init stack with v

        while (!stack.isEmpty()) {
            int vtx = stack.pop();
            if (!visited[vtx]) {
                visited[vtx] = true;
                for (Integer child : adjMap.get(vtx)) {
                    stack.push(child);
                }
                System.out.print(vtx + " ");
            }
        }
    }

    public void bfs(int v) throws GraphException {
        if (v > this.numOfVertices) {
            throw new GraphException("Search vertex should be less than::: " + this.numOfVertices);
        }

        boolean[] visited = new boolean[this.numOfVertices + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(v); // init queue with v

        while (!queue.isEmpty()) {
            int vtx = queue.poll();
            if (!visited[vtx]) {
                visited[vtx] = true;
                queue.addAll(this.adjMap.get(vtx));
                System.out.print(vtx + " ");
            }
        }
    }

    public static void main(String[] args) throws GraphException {
        Graph g = new Graph(4);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 1);

        g.bfs(4);
        g.dfs(2);
    }

    private class GraphException extends Exception {
        public GraphException(String message) {
            super(message);
        }
    }
}

