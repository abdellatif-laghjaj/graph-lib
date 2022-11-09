import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    public HashMap<String, ArrayList<String>> graph;
    public boolean[][] disMat;

    //initialize the graph
    public Graph() {
        graph = new HashMap<String, ArrayList<String>>();
    }

    /*
     * addEdge method adds an edge between two vertices
     * if the vertices are not present in the graph, it adds them
     * else it adds the edge between the vertices
     * */
    public void addEdge(String start, String finish) {
        if (graph.containsKey(start)) {
            graph.get(start).add(finish);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(finish);
            graph.put(start, list);
        }

        /*
            check if the finish is not inserted yet in the graph,
            then insert it and give it an empty array list
        */
        if (!graph.containsKey(finish)) {
            graph.put(finish, new ArrayList<>());
        }
    }

    //display the graph to the console
    public void displayGraph() {
        System.out.println(this.graph);
    }

    /*
     * crateDistMat method creates a distance matrix
     * */
    public void crateDistMat(HashMap<String, ArrayList<String>> graph) {
        int size = graph.size();
        disMat = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String key = (String) graph.keySet().toArray()[j];
                String val = (String) graph.keySet().toArray()[i];
                disMat[i][j] = graph.get(key).contains(val);
            }
        }
    }

    //display the distance matrix to the console
    public void displayDistMat() {
        System.out.println("\n############ Distance Matrix ############");
        for (boolean[] booleans : disMat) {
            System.out.print("[");
            for (int j = 0; j < disMat.length; j++) {
                System.out.print("\t" + booleans[j] + " ");
            }
            System.out.print("]\n");
        }
    }

    /*
     method that returns the neighbors of a node
     */
    public ArrayList<String> getNeighbors(String node) {
        ArrayList<String> neighbors = new ArrayList<>();
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            String key = (String) graph.keySet().toArray()[i];
            if (graph.get(key).contains(node) || graph.get(node).contains(key)) {
                neighbors.add(key);
            }
        }
        return neighbors;
    }

    //display the neighbors of a node to the console
    public void displayNeighbors(String node) {
        System.out.println("\n############ Neighbors of node " + node + " ############");
        System.out.println(getNeighbors(node));
    }

    //method that returns the degree of a node
    public int getDegree(String node) {
        int degree = 0;
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            String key = (String) graph.keySet().toArray()[i];
            if (graph.get(key).contains(node) || graph.get(node).contains(key)) {
                degree++;
            }
        }
        return degree;
    }
}
