import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
//
//        graph.addEdge("A", "B");
//        graph.addEdge("A", "C");
//        graph.addEdge("A", "D");
//        graph.addEdge("B", "D");
//        graph.addEdge("A", "E");
//        graph.addEdge("C", "E");
//        graph.addEdge("C", "F");
//        graph.addEdge("E", "F");
//        graph.addEdge("E", "D");
//        graph.addEdge("E", "G");
//        graph.addEdge("D", "G");
//        graph.addEdge("G", "K");
//        graph.addEdge("F", "H");
//        graph.addEdge("E", "H");
//        graph.addEdge("H", "I");
//        graph.addEdge("I", "K");
//        graph.addEdge("I", "J");
//        graph.addEdge("H", "J");
//        graph.addEdge("J", "K");
//        graph.addEdge("J", "G");
//
//        graph.addEdge("X", "Y");
//        graph.addEdge("X", "Y");
//        graph.addEdge("Y", "X");
//        graph.addEdge("Y", "Y");

//        graph.displayGraph();
//        graph.crateDistMat(graph.graph);
//        graph.displayDistMat();
//
//        graph.displayNeighbors("X");
//        System.out.println(graph.getDegree("X"));
//
//        graph.displayNeighbors("Y");
//        System.out.println(graph.getDegree("Y"));

//        graph.displayNeighbors("A");
//        System.out.println(graph.getDegree("A"));

        boolean[][] matrix = new boolean[3][3];

        matrix[0][0] = false;
        matrix[0][1] = true;
        matrix[0][2] = false;
        matrix[1][0] = true;
        matrix[1][1] = true;
        matrix[1][2] = false;
        matrix[2][0] = false;
        matrix[2][1] = true;
        matrix[2][2] = false;

        System.out.println("########\t Matrix A \t########");
        for (boolean[] booleans : matrix) {
            System.out.print("[\t");
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(booleans[j] + "\t");
            }
            System.out.print("]\n");
        }

        System.out.println("########\t Matrix A to power 2 \t########");
        boolean[][] matToPower2 = Helper.matrixToPower(matrix, 2);
        for (boolean[] booleans : matToPower2) {
            System.out.print("[\t");
            for (int j = 0; j < matToPower2.length; j++) {
                System.out.print(booleans[j] + "\t");
            }
            System.out.print("]\n");
        }

//        System.out.println("There is a path between A & D in 5 steps?");
//        System.out.println("Answer: " + graph.findPath("A", "D", 5));

    }
}