import java.util.Arrays;

class SoupServings {
    private static int[][] operations = new int[][] {
            {25, 75},
            {50, 50},
            {75, 25},
            {100, 0}
    };
    private static double[][] dp;

    public static double soupServings(int qty) {
        if (qty > 5000) return 1;
        int servings = (qty / 25) + (qty % 25 > 0 ? 1 : 0) + 1;
        dp = new double[servings][servings];
        for (int i = 0; i < servings; i++) {
            Arrays.fill(dp[i], -1);
        }
        double serv1 = computeProbability(qty - 25, qty - 75, 1, 3);
        double serv2 = computeProbability(qty - 50, qty - 50, 2, 2);
        double serv3 = computeProbability(qty - 75, qty - 25, 3, 1);
        double serv4 = computeProbability(qty - 100, qty, 4, 0);
        return 0.25 * (serv1 + serv2 + serv3 + serv4);
    }
    public static double computeProbability(double a, double b, int servingsA, int servingsB) {
        if (a <= 0 && b > 0) return 1;
        if (a > 0 && b <= 0) return 0;
        if (a <= 0 && b <= 0) return 0.5;
        if (dp[servingsA][servingsB] != -1) return dp[servingsA][servingsB];
        double sum = 0;
        for (int i = 0; i < 4; i++) {
            double prob = 0.25 * computeProbability(a - operations[i][0], b - operations[i][1], servingsA + i + 1, servingsB + 3 - i);
            sum += prob;
        }
        dp[servingsA][servingsB] = sum;
        return sum;
    }
    public static void main (String[] args) {
        double pro = soupServings(4000);
        System.out.print(pro);
    }
}