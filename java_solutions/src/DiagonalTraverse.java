class DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] mat) {
        int[] res = new int[mat.length * mat[0].length];
        int counter = 0;
        for (int i = 0; i < mat[0].length + mat.length - 1; i++) {
            if (i % 2 == 1) {
                int start = i < mat[0].length ? 0 : i - mat[0].length + 1;
                for (int j = start; j <= Math.min(i, mat.length - 1); j++) {
                    res[counter++] = mat[j][i - j];
                }
            } else {
                int start = i < mat.length ? i : mat.length - 1;
                for (int j = start; j >= 0 && (i - j < mat[0].length); j--) {
                    res[counter++] = mat[j][i - j];
                }
            }
        }
        return res;
    }
    public static void main (String[] args) {
        String f = findDiagonalOrder(new int[][] {
                {1,2,3,4},{5,6,7,8},{9,10, 11, 12}}).toString();
        System.out.println(f);
    }
}