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

        //region Premiére étape
        if (graph.containsKey(start)) {
            graph.get(start).add(finish);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(finish);
            graph.put(start, list);
        }
        //endregion


        //region Deuxiéme étape
        if (!graph.containsKey(finish)) {
            graph.put(finish, new ArrayList<String>());
        }
        //endregion
    }

    /*
     * Question b: Affiche la liste des successeurs associée au graphe.
     * */
    public void displayGraph() {
        System.out.println(this.graph);
    }

    /*
     * Question c: Prend en argument la liste des successeurs puis la transforme en matrice d’adjacence.
     * Premiére étape:
     *   Définire le nombre de sommets du graphe qui sera la dimmension
     *   du matrice d'adjacence.
     *
     * Deuxiéme étape:
     *   Le compteur i indique les lignes, et j indique colonnes.
     *   On boucle ligne par ligne (i), et on verifie que chaque sommet est un successeur
     *   du sommet de la colone j. Si ça le cas, on affecte la valeur <<true>> a l'élément
     *   ayant l'indice i et j, et on affecte la valeur <<false>> sinon.
     *
     * */
    public void createDistMat(HashMap<String, ArrayList<String>> graph) {
        //region Premiére étape
        int size = graph.size();
        disMat = new boolean[size][size];
        //endregion

        //region Deuxiéme étape
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String key = (String) graph.keySet().toArray()[j];
                String val = (String) graph.keySet().toArray()[i];
                disMat[i][j] = graph.get(key).contains(val);
            }
        }
        //endregion
    }

    /*
     * Question d: Affiche la matrice d’adjacence.
     * */
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
     * Question e:  Retourne la liste du voisinage d’un nœud.
     *
     * Selon la définition, le voisinage d'un sommet est l'ensemble de tous
     * ses adjacents (successeurs et prédécesseurs).
     *
     * Pour obtenir la liste du voisinage d’un nœud, on boucle sur toute les
     * nœuds du graphe, si quelq'un d'eux est un successeur ou prédécesseur
     * du nœud principale, on l'ajoute au liste du voisinage.
     *
     * */
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

    /*
     * Affiche la liste du voisinage d'un nœud.
     * */
    public void displayNeighbors(String node) {
        System.out.println("\n############\tNeighbors of node " + node + " \t############");
        System.out.println(getNeighbors(node));
    }

    /*
     * Fonctionne qui calcule le degré sortant d'un nœud.
     * */
    public int getDegreeOut(String node) {
        return graph.get(node).size();
    }

    /*
     * Fonctionne qui calcule le degré entrant d'un nœud.
     * */
    public int getDegreeIn(String node) {
        int degree = 0;
        for (Object key : graph.keySet().toArray()) {
            if (graph.get(key).contains(node)) {
                degree++;
            }
        }
        return degree;
    }

    /*
     * Question f: Retourne le degré d’un nœud.
     *
     * Selon la définition: Soit Xi un sommet du graphe G.
     *   - Le degré sortant de Xi est le nombre d'arcs ayant Xi comme extrémité initiale.
     *   - Le degré entrant de Xi est le nombre d'arcs ayant Xi comme extrémité finale.
     *   - Le degré de Xi est la somme de son degré sortant et son degré entrant.
     *
     * */
    public int getDegree(String node) {
        return getDegreeOut(node) + getDegreeIn(node);
    }

    /*
     * Fonction permet d'élever une matrice à une puissance données.
     * */
    public static boolean[][] matrixToPower(boolean[][] matrix, int power) {
        if (power == 1) {
            return matrix;
        } else if (power > matrix.length) {
            return matrixToPower(matrix, matrix.length);
        } else {
            boolean temp[][] = new boolean[matrix.length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                temp[i] = new boolean[matrix.length];
                for (int j = 0; j < matrix[i].length; j++) {
                    boolean sum = false;
                    for (int k = 0; k < matrix.length; k++) {
                        sum = sum || (matrix[i][k] && matrix[k][j]);
                    }
                    temp[i][j] = sum;
                }
            }
            return temp;
        }
    }

    /*
     * Fonction permet de déterminer l'index d'un nœud dans le graphe.
     * */
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

    /*
     * Question g: Détermine si un chemin d’une longueur donnée existe entre le sommet de départ et d’arrivé.
     *
     * Selon le cours:
     *   Soit G un 1-graphe d'ordre n et M sa matrice d'adjacence.
     *   - La matrice M^p (p <= n) permet de déterminer l'existence d'un chemin de longueur p d'un sommet Xi
     *   à un sommet Xj: L'élément Aij de la matrice M^p vaut, <<false>> s'il n'existe pas de chemin de longueur
     *   p de Xi vers Xj, et <<true>> sinon.
     *
     * Alors, pour déterminer si un chemin d'une longueur donnée existe, on obtient la valeur d'élément du matrice
     * d'adjacents du graphe à la puissance 'length', qui correspond à l'intersection du colone du nœud de départ
     * et la ligne du nœud d'arrivée.
     *
     * */
    public boolean findPath(String start, String finish, int length) {
        int indexOfStart = indexOfNode(start);
        int indexOfFinish = indexOfNode(finish);
        boolean[][] distMatToPowerLength = matrixToPower(disMat, length);
        return distMatToPowerLength[indexOfFinish][indexOfStart];
    }

    /*
     * Question h: Détermine si un chemin existe entre le sommet de départ et d’arrivé.
     *
     *
     * */
    public boolean findPath(String start, String finish) {
        for (int i = 0; i <= ((int) graph.size() / 2); i++) {
            if (findPath(start, finish, i)) {
                return true;
            } else if (findPath(start, finish, graph.size() - i)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Question i: détermine si un chemin est Hamiltonien.
     *
     * Selon la définition: on appelle chemin Hamiltonien un chemin passant une fois,
     *   et une seule fois, par chacun des sommets de ce chemin.
     *
     * Alors pour déterminer si un chemin est Hamiltonien, on détermine d'abord le dgrée
     *   entrant et sortant de chacune de ses nœud, puis on vérifie si quelq'une a un
     *   degree sortant ou entrant nulle ou superieur à 1, Si ça le cas alors ce chemin
     *   n'est pas Hamiltonien.
     *
     * */
    public boolean isHamilton(ArrayList<String> path) {
        for (int i = 1; i < path.size() - 1; i++) {
            int degreeIn = 0;
            int degreeOut = 0;
            for (String node: path) {
                if (graph.get(node).contains(path.get(i)))
                    degreeIn++;
                if (graph.get(path.get(i)).contains(node))
                    degreeOut++;
            }
            if (degreeIn != 1 || degreeOut != 1) {
                return false;
            }
        }
        return true;
    }

    /*
    *
    * Question j: détermine si un chemin est Eulérien.
    *
    * Selon la définition: un chemin est dit Eulérien si, et seulement si, tout sommet sauf sommet
    *   de départ et sommet d'arrivée, le demi-degré entrant est égal au demi-degré sortant (1), et
    *   pour le sommet de départ le degré entrant est égal au degré sortant moins 1 (2), et pour le
    *   sommet d'arrivé le degré entrant est égal au degré sortant plus 1 (3).
    *
    * Donc pour détermine si un chemin est Eulérien, on détermine, d'abord, le degré entrant et sortant
    *   pour le sommet de départ et le sommet d'arrivé pour vérifier les deux conditions (2) et (3).
    *   Puis, pour chaque autre sommet pour vérifier la condition (1).
    *
    * */
    public boolean isEuler(ArrayList<String> path) {
        //region conditions (2) et (3).
        int degreeInStart = 0;
        int degreeOutStart = 0;
        int degreeInEnd = 0;
        int degreeOutEnd = 0;
        for (String node : path) {
            if (graph.get(node).contains(path.get(0)))
                degreeInStart++;
            if (graph.get(path.get(0)).contains(node))
                degreeOutStart++;
            if (graph.get(node).contains(path.get(path.size() - 1)))
                degreeInEnd++;
            if (graph.get(path.get(path.size() - 1)).contains(node))
                degreeOutEnd++;
        }
        if (degreeInStart != degreeOutStart - 1 || degreeInEnd != degreeOutEnd + 1) {
            return false;
        }
        //endregion
        //region condition(1)
        else {
            for (int i = 1; i < path.size() - 1; i++) {
                int degreeIn = 0;
                int degreeOut = 0;
                for (String node : path) {
                    if (graph.get(node).contains(path.get(i)))
                        degreeIn++;
                    if (graph.get(path.get(i)).contains(node))
                        degreeOut++;
                }
                if (degreeIn != degreeOut) {
                    return false;
                }
            }
            return true;
        }
        //endregion
    }

}
