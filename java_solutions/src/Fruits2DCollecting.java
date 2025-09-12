import java.util.HashMap;

class Fruits2DCollecting {
    public static int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int[] cur = new int[3];
        cur[0] = fruits[0][n-1];
        for(int i = 1; i < n-1; i++){
            int end = Math.min(i, n-2-i);
            int[] next = new int[end+3];
            next[0] = Math.max(cur[0], cur[1]) + fruits[i][n-1];
            for(int e = 1; e <= end; e++){
                next[e] = Math.max(cur[e-1], Math.max(cur[e], cur[e+1])) + fruits[i][n-1-e];
            }
            cur = next;
        }
        int v = cur[0];
        cur = new int[3];
        cur[0] = fruits[n-1][0];
        for(int i = 1; i < n-1; i++){
            int end = Math.min(i, n-2-i);
            int[] next = new int[end+3];
            next[0] = Math.max(cur[0], cur[1]) + fruits[n-1][i];
            for(int e = 1; e <= end; e++){
                next[e] = Math.max(cur[e-1], Math.max(cur[e], cur[e+1])) + fruits[n-1-e][i];
            }
            cur = next;
        }
        v += cur[0];
        for(int i = 0; i < n; i++){
            v+=fruits[i][i];
        }
        return v;
    }
    public static void main (String[] args) {
        System.out.print(maxCollectedFruits(new int[][] {{11,17,13,0,18},{13,12,10,12,19},{4,8,10,14,16},{2,13,12,7,16},{4,9,7,4,3}}));
    }
}