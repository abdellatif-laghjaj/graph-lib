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
            graph.put(finish, new ArrayList<String>());
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
        System.out.println("\n############\tDistance Matrix \t############");
        for (boolean[] booleans : disMat) {
            System.out.print("[\t");
            for (int j = 0; j < disMat.length; j++) {
                System.out.print(booleans[j] + "\t");
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
                if (!neighbors.contains(key) && !node.equals(key)) {
                    neighbors.add(key);
                }
            }
        }
        return neighbors;
    }

    //display the neighbors of a node to the console
    public void displayNeighbors(String node) {
        System.out.println("\n############\tNeighbors of node " + node + " \t############");
        System.out.println(getNeighbors(node));
    }

    //method that returns the degree of a node
    public int getDegree(String node) {
        int degree = graph.get(node).size();
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            String key = (String) graph.keySet().toArray()[i];
            if (graph.get(key).contains(node)) {
                degree++;
            }
        }
        return degree;
    }

    public boolean findPath(String start, String finish, int length) {
        int indexOfStart = indexOfNode(start);
        int indexOfFinish = indexOfNode(finish);
        boolean[][] distMatToPower = Helper.matrixToPower(disMat, length);
        return distMatToPower[indexOfFinish][indexOfStart];
    }

    public int indexOfNode(String node) {
        int index = -1;
        Object[] keys = graph.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            if (node.equals(keys[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
    // if (length == 0) {
    //     return start.equals(finish);
    // } else {
    //     for (String neighbor : getNeighbors(start)) {
    //         if (findPath(neighbor, finish, length - 1)) {
    //             return true;
    //         }
    //     }
    // }
    // return false;

    public boolean findPath(String start, String finish) {
        return findPath(start, finish, graph.size());
    }

    public boolean isHamilton(ArrayList<String> path) {
        if (path.size() != graph.size()) {
            return false;
        }
        for (int i = 0; i < path.size() - 1; i++) {
            if (!graph.get(path.get(i)).contains(path.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEuler(ArrayList<String> path) {
        if (path.size() != graph.size() + 1) {
            return false;
        }
        for (int i = 0; i < path.size() - 1; i++) {
            if (!graph.get(path.get(i)).contains(path.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
}
