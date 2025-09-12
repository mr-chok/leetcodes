import java.util.Stack;

class CountSubmatrices {
    public int numSubmat2(int[][] matrix) {
        int counter = matrix[0][0];
        int[][] matrixHor = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            matrixHor[i] = matrix[i].clone();
        }
        int[][] matrixVert = matrix.clone();
        for (int i = 0; i < matrix.length; i++) {
            matrixVert[i] = matrix[i].clone();
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 1) {
                matrixVert[i][0] += matrixVert[i - 1][0];
                counter += matrixVert[i][0];
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 1) {
                matrixHor[0][i] += matrixHor[0][i - 1];
                counter += matrixHor[0][i];
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    int square = Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][j - 1], matrix[i][j - 1]));
                    matrix[i][j] += square;
                    counter += matrix[i][j];

                    matrixVert[i][j] += matrixVert[i - 1][j];
                    matrixHor[i][j] += matrixHor[i][j - 1];

                    int[] V = new int[square + 1];
                    V[0] = matrixHor[i][j];
                    for (int k = 1; k <= square; k++) {
                        V[k] = Math.min(matrixHor[i - k][j], V[k - 1]);
                    }

                    for (int l = 0; l <= square; l++) {
                        counter += V[l] - l - 1;
                    }

                    V[0] = matrixVert[i][j];
                    for (int k = 1; k <= square; k++) {
                        V[k] = Math.min(matrixVert[i][j - k], V[k - 1]);
                    }

                    for (int l = 0; l <= square; l++) {
                        counter += V[l] - l - 1;
                    }
                }
            }
        }
        return counter;
    }

    public int numSubmat(int[][] mat) {
            int n = mat[0].length;
            int[] heights = new int[n];
            int res = 0;
            int rowStart = -1;
        for (int[] row : mat) {
                for (int i = 0; i < n; i++) {
                    heights[i] = row[i] == 0 ? 0 : heights[i] + 1;
                }
                Stack<int[]> stack = new Stack<>();
                for (int i = 0; i < n; i++) {
                    if (row[i] == 0) {
                        stack.empty();
                        rowStart = i;
                    } else {
                        int h = heights[i];
                        while (!stack.isEmpty() && heights[stack.peek()[0]] >= h) {
                            stack.pop();
                        }
                        int cur = (i - rowStart) * heights[i];
                        if (stack.isEmpty()) {
                            stack.push(new int[]{i, cur});
                            res += cur;
                            continue;
                        }
                        int[] top = stack.peek();
                        int j = top[0];
                        int prev = top[1];
                        cur = prev + (i - j) * h;
                        stack.push(new int[]{i, cur});
                        res += cur;
                    }
                }
            }
            return res;
}
    public static void main(String[] args) {
        CountSubmatrices cs  = new CountSubmatrices();
        int[][] matrix = new int[][] {
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,1,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,1,1,1,1,1,0,0},
//                {0,0,1,1,1,1,0,0,0,0,0,0,0,0},
//                {0,0,1,1,1,1,0,0,0,0,0,0,0,0},
//                {0,0,0,0,1,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,1,1,1,1,1,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        System.out.println("mine: " + cs.numSubmat2(matrix));
        System.out.println("editorial: " + cs.numSubmat(matrix));
    }
}