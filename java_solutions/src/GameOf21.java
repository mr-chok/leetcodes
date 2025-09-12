class GameOf21 {
    public static double new21Game (int n, int k, int maxPts) {
        if (k == 0) return 1; // game over already, and points (0) is less or equal than any n
        double[] dp = new double[k];
        double accumulatedProbability = 0.0;
        double currentProbability;
        for (int i = k - 1; i >= 0; i--) {
            int assuredCases = Math.max(Math.min(n, i + maxPts) - k + 1, 0);
            currentProbability = (accumulatedProbability + assuredCases) / maxPts;
            accumulatedProbability = accumulatedProbability + currentProbability - (i + maxPts >= k ? 0 : dp[i + maxPts]);
            dp[i] = currentProbability;
        }
        return dp[0];
    }

    public static double new21Game2(int n, int k, int maxPts) {
            double dp[] = new double[n + 1];
            dp[0] = 1;
            double s = k > 0 ? 1 : 0;
            for (int i = 1; i <= n; i++) {
                dp[i] = s / maxPts;
                if (i < k) {
                    s += dp[i];
                }
                if (i - maxPts >= 0 && i - maxPts < k) {
                    s -= dp[i - maxPts];
                }
            }
            double ans = 0;
            for (int i = k; i <= n; i++) {
                ans += dp[i];
            }
            return ans;
        }

    public static void main (String[] args) {
        int n = 5710, k = 5070, maxPts = 8516;
//        int n = 9676, k = 8090, maxPts = 3056;
//        int n = 21, k = 17, maxPts = 10;
        System.out.println("mine: " + new21Game(n, k, maxPts));
        System.out.println("editorial: " + new21Game2(n, k, maxPts));
    }
}