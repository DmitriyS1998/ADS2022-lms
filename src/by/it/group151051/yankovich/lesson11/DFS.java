package by.it.group151051.yankovich.lesson11;

import java.util.*;

public class DFS {
    public char firstVertex;
    public StringBuilder result;
    public List<List<Character>> edges;
    public Map<List<Character>, String> classification;

    public DFS(char firstVertex){
        this.firstVertex = firstVertex;
        result = new StringBuilder();
        edges = Graph.edges;
        classification = new HashMap<>();
    }
    public void runDFS(Vertex start, Graph graph){
        start.visited = true;
        result.append(start.data+"-");
        for (char neighbor: graph.adjVertices.get(start.data)) {
            int pos = neighbor - 65;
            if (!graph.vertices.get(pos).visited){
                runDFS(graph.vertices.get(pos), graph);
            }
            if (start.data == firstVertex){
                if (findUnvisited(graph) != null){
                    runDFS(findUnvisited(graph), graph);
                }
                else {
                    result.deleteCharAt(result.length()-1);
                }
            }
        }
    }
    public Vertex findUnvisited(Graph graph){
        for (Vertex vertex: graph.vertices) {
            if (!vertex.visited) return vertex;
        }
        return null;
    }

    public void classifyEdges(){
        initClassification();
        for (int i=0; i<result.length()-2; i+=2){
            List edge = new ArrayList();
            if (result.charAt(i)<result.charAt(i+2)) {
                edge.add(result.charAt(i));
                edge.add(result.charAt(i + 2));
            }
            else {
                edge.add(result.charAt(i+2));
                edge.add(result.charAt(i));
            }
            if (classification.containsKey(edge)) {
                classification.put(edge, ": tree edge");
            }
        }
        printClassification();
    }

    public void initClassification(){
        for (List edge: edges){
            classification.put(edge, ": back edge");
        }
    }

    public void printClassification(){
        Set<List<Character>> keys = classification.keySet();
        for (List key: keys) {
            System.out.println(key.get(0) + " " + key.get(1) + classification.get(key));
        }
    }
}
