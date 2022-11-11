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

    public void createDistMat(HashMap<String, ArrayList<String>> graph) {
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

    public int getDegreeOut(String node) {
        return graph.get(node).size();
    }

    public int getDegreeIn(String node) {
        int degree = 0;
        for (Object key: graph.keySet().toArray()) {
            if (graph.get(key).contains(node)) {
                degree++;
            }
        }
        return degree;
    }

    public int getDegree(String node) {
        return getDegreeOut(node) + getDegreeIn(node);
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

    public boolean findPath(String start, String finish) {
        boolean isFound = false;
        for (int i = graph.size(); i >= 0; i--) {
            if (findPath(start, finish, i)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public boolean isHamilton(ArrayList<String> path) {
        boolean isHamilton = true;
        for (int i = 0; i < path.size(); i++) {
            if (!graph.get(path.get(i + 1)).contains(path.get(i)) || !graph.get(path.get(i)).contains(path.get(i + 1))) {
                isHamilton = false;
                break;
            }
        }
        return isHamilton;
    }

    public boolean isEuler(ArrayList<String> path) {
        if (getDegreeIn(path.get(0)) != getDegreeOut(path.get(0)) - 1 || getDegreeIn(path.get(path.size() - 1)) != getDegreeOut(path.get(path.size() - 1)) + 1 ) {
            return false;
        } else {
            for (int i = 1; i < path.size() - 1; i++) {
                if (getDegreeIn(path.get(i)) != getDegreeOut(path.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

}
