package com.codepractice;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ShortestPathBFS {

    public static void main(String[] args) throws GraphException {

        Graph g = new Graph();

        //add vertices
        for(int i=1; i <= 4;i++){
            g.addVertex(i);
        }

        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,4);
        g.addEdge(3,4);
        g.addEdge(3,2);

        List<Integer> path = g.findPath(4,1);

        for(int i=0;i<path.size();i++){
            String out = path.get(i) + (i==path.size()-1 ? "" : " -> ");
            System.out.print(out);
        }
    }
}

class Graph{

    Map<Integer, GraphNode> vertices;

    public Graph(){
        vertices = new HashMap<>();
    }

    public void addVertex(int v){
        vertices.put(v, new GraphNode(v));
    }

    public void addEdge(int v1, int v2) throws GraphException {
        GraphNode from = vertices.get(v1);
        GraphNode to = vertices.get(v2);

        if(from==null){
            throw new GraphException("Invalid source vertex");
        }

        if(to==null){
            throw new GraphException("Invalid destination vertex");
        }

        vertices.get(v1).getNeighbours().add(to);
        vertices.get(v2).getNeighbours().add(from);
    }

    public List<Integer> findPath(int start, int end) throws GraphException {
        Queue<GraphNode> queue = new ArrayDeque<>();
        Map<Integer, Integer> trackedTillNow = new HashMap<>();

        GraphNode source = vertices.get(start);
        GraphNode destination = vertices.get(end);

        if(source==null){
            throw new GraphException("Invalid source vertex");
        }

        if(destination==null){
            throw new GraphException("Invalid destination vertex");
        }


        queue.add(source);
        trackedTillNow.put(source.v, null);

        while (!queue.isEmpty()){
            GraphNode currentNode = queue.poll();

            if(currentNode.equals(destination)){
                return constructPath(trackedTillNow, start, end);
            }

            for(GraphNode neighbour : currentNode.getNeighbours()){
                if(!trackedTillNow.containsKey(neighbour.v)){
                    queue.add(neighbour);
                    trackedTillNow.put(neighbour.v, currentNode.v);
                }
            }

        }
        throw new GraphException(String.format("No path exists from %d to %d", start, end));
    }

    private List<Integer> constructPath(Map<Integer, Integer> trackedTillNow, Integer start, Integer end) {
        LinkedList<Integer> path = new LinkedList<>();
        Integer current = end;
        path.add(current);
        while(!current.equals(start)){
            Integer node = trackedTillNow.get(current);
            path.add(node);
            current = node;
        }
        Collections.reverse(path);
        return path;
    }

}

class GraphException extends Exception {
    public GraphException(String message) {
        super(message);
    }
}

class GraphNode{
    int v;
    Set<GraphNode> neighbours;

    public GraphNode(int v){
        this.v = v;
        this.neighbours = new HashSet<>();
    }

    public Set<GraphNode> getNeighbours(){
        return this.neighbours;
    }
}
