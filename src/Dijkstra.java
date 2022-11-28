public class Dijkstra {

    /**
     * Fonction qui implémente l'algorithme de Dijkstra single source
     *
     * @param graph : matrice d'adjacence des poids
     * @param src   : sommet de départ
     */
    public void findShortPath(int[][] graph, int src) {

        // Tableau contenant pour chaque sommet le poids total à parcourir pour y
        // accéder à partir de sommet src.
        int[] dist = new int[0];

        // sptSet[i] prend true, si le sommet i est traité
        boolean[] sptSet = new boolean[0];

        // Un tableau contenant pour chaque sommet son prédecesseur choisi.
        int[] pred = new int[0];

        /**
         * Phase d'initialisation (dist[], sptSet[] et pred[], dist [src])
         */
        for (int i = 0; i < graph[0].length - 1; i++) {
            dist[i] =
                    sptSet[i] =
                            pred[i] = -1;
        }
        dist[src] =

        /**
         * Module de recherche du plus court chemin pour tout les sommets
         */
        for (int count = 0; count < graph[0].length - 1; count++) {
            // Choisir le sommet de distance minimale dans l'ensemble des sommets non encore
            // traités.
            int u = minDistance(dist, sptSet);

            // marquer le sommet comme traité
            sptSet[u]

            for (int v = 0; v < graph[0].length - 1; v++) {
                // Mettre à jour dist[v] et pred[v] sous condition (il n'est pas traité + il y'a
                // une arrete de u vers v + la distance de src vers v à travers u est plus petite que la distance actuelle de v
                if (/*condition 1*/ &&graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && /*condition 4*/ ){
                    //instruction 1
                    //instruction 2
                }
            }

        }
        // Afficher la solution
        printSolution(dist);
    }

    /**
     * fonction qui permer de retourner le sommet avec la valeur de distance
     * minimale, à partir de l'ensemble des sommets non encore inclus dans l'arbre
     * du plus court chemin
     */
    public int minDistance(int dist[], boolean sptSet[]) {
        // initialiser la valeur minimale
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min =
                        min_index =
            }
        }
        return min_index;
    }

    /**
     * A utility function to print the constructed distance array
     *
     * @param dist
     */
    void printSolution(int dist[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    /**
     * La fonction main
     *
     * @param args
     */
    public static void main(String[] args) {

        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0}, {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2}, {0, 0, 7, 0, 9, 14, 0, 0, 0}, {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0}, {0, 0, 0, 0, 0, 2, 0, 1, 6}, {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};

        Dijkstra t = new Dijkstra();
        t.findShortPath(graph, 0);
    }

}