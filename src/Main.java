import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "D");
        graph.addEdge("A", "E");
        graph.addEdge("C", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "F");
        graph.addEdge("E", "D");
        graph.addEdge("E", "G");
        graph.addEdge("D", "G");
        graph.addEdge("G", "K");
        graph.addEdge("F", "H");
        graph.addEdge("E", "H");
        graph.addEdge("H", "I");
        graph.addEdge("I", "K");
        graph.addEdge("I", "J");
        graph.addEdge("H", "J");
        graph.addEdge("J", "K");
        graph.addEdge("J", "G");
//        graph.addEdge("K", null);

        graph.displayGraph();
        graph.crateDistMat(graph.graph);
        graph.displayDistMat();

        graph.displayNeighbors("K");
        System.out.println(graph.getDegree("E"));
    }
}