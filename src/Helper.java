public class Helper {
    public static boolean[][] matrixToPower(boolean[][] matrix, int power) {
        if (power > 1 && power >= matrix.length) {
            boolean temp[][] = new boolean[matrix.length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                temp[i] = new boolean[matrix.length];
                for (int j = 0; j < matrix[i].length; j++) {
                    boolean sum = false ;
                    for (int k = 0; k < matrix.length; k++) {
                        sum = sum || (matrix[i][k] && matrix[k][j]);
                    }
                    temp[i][j] = sum ;
                }
            }
            return temp;
        } else
            return matrix;
    }
}
