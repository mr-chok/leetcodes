import java.util.HashMap;

class OneFilledMatrices {
    public int countSquares2(int[][] matrix) {
        int squareLength = 1;
        int maxLength = Math.min(matrix.length, matrix[0].length);
        HashMap<Integer, Integer> maxSquares = new HashMap<>();
        int counter = 0;
        boolean withinBounds;
        while (squareLength <= maxLength) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 1) {
                        int pureness = 1;
                        withinBounds = i + squareLength - 1 < matrix.length &&  j + squareLength - 1 < matrix[0].length;
                        if (!withinBounds) continue;
                        int spoiler = maxSquares.getOrDefault(encodePoint(i, j), 0);
                        if (spoiler == squareLength - 1) {
                            for (int k = i; k < i + squareLength - 1; k++) {
                                pureness &= matrix[k][j + spoiler];
                                if (pureness != 1) break;
                            }
                            for (int l = j; l < j + squareLength; l++) {
                                pureness &= matrix[i + spoiler][l];
                                if (pureness != 1) break;
                            }
                            if (pureness == 1) {
                                maxSquares.put(encodePoint(i, j), squareLength);
                                counter++;
                            }
                        }
                    }
                }
            }
            squareLength++;
        }
        return counter;
    }
    private int encodePoint(int i, int j) {
        return 1000 * i + j;
    }
    private int[] decodePoint(int x) {
        return new int[] {x / 1000, x % 1000};
    }

    public int countSquares(int[][] matrix) {
            int row = matrix.length, col = matrix[0].length;
            int[][] dp = new int[row + 1][col + 1];
            int ans = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 1) {
                        dp[i + 1][j + 1] =
                                Math.min(
                                        Math.min(dp[i][j + 1], dp[i + 1][j]),
                                        dp[i][j]
                                ) +
                                        1;
                        ans += dp[i + 1][j + 1];
                    }
                }
            }
            return ans;
        }
    public static void main(String[] args) {
        OneFilledMatrices obj = new OneFilledMatrices();
        int[][] matrix = new int[][] {
                {0,0,0,0,0,0,1,1,0,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0,0},
                {0,0,0,1,1,1,1,1,1,0,0,0},
                {1,1,1,1,1,1,1,1,1,1,0,0},
                {1,1,1,1,1,1,1,1,1,1,0,0},
                {1,1,1,1,1,1,1,1,1,0,0,0},
                {0,0,0,1,1,1,1,1,1,0,0,0},
                {0,0,0,1,1,1,1,1,1,0,0,0},
        };
        System.out.println(obj.countSquares2(matrix));
        System.out.println(obj.countSquares(matrix));
    }
}