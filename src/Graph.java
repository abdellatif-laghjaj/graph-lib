import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    public HashMap<String, ArrayList<String>> graph;
    public boolean[][] disMat;

    public Graph() {
        graph = new HashMap<String, ArrayList<String>>();
    }

    public void addEdge(String start, String finish) {
        if (graph.containsKey(start)) {
            graph.get(start).add(finish);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(finish);
            graph.put(start, list);
        }

        //check if the finish is not inserted yet in the graph, then insert it and give it an empty array list
        if (!graph.containsKey(finish)) {
            graph.put(finish, new ArrayList<String>());
        }
    }

    public void displayGraph() {
        System.out.println(this.graph);
    }

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

    public void displayDistMat() {
        System.out.println("\n############\tDistance Matrix \t############");
        for (boolean[] booleans : disMat) {
            System.out.print("[\t");
            for (int j = 0; j < disMat.length; j++) {
                System.out.print(booleans[j] + "\t");
            }
            System.out.print("]\n");
        }
    }

    public ArrayList<String> getNeighbors(String node) {
        ArrayList<String> neighbors = new ArrayList<>();
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            String key = (String) graph.keySet().toArray()[i];
            if (graph.get(key).contains(node) || graph.get(node).contains(key)) {
                if (!neighbors.contains(key) && !node.equals(key)) {
                    neighbors.add(key);
                }
            }
        }
        return neighbors;
    }

    public void displayNeighbors(String node) {
        System.out.println("\n############\tNeighbors of node " + node + " \t############");
        System.out.println(getNeighbors(node));
    }

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
