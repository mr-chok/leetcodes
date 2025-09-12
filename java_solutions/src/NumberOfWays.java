import java.util.Arrays;

class NumberOfWays {
    private static int[] powers = new int[501];
    private static int[] dp = new int[501];
    private static int[] prefix = new int[501];
    public static int numberOfWays2(int n, int x) {
        powers[0] = 0;
        Arrays.fill(dp, -1);
        for (int i = 1; i <= n && powers[i-1] <= n; i++) {
            powers[i] = (int) Math.pow(i, x);
            prefix[i] += prefix[i-1] + powers[i];
        }
        dp[1] = 1;
        return findWays(n, 0, x);
    }
    public static int findWays (int n, int complement, int x) {
        if (dp[n] != -1) {
            if (n == complement) {
                int i = 0;
                while ((int) Math.pow(i, x) < n)   i++;
                if (n == powers[i]) return dp[n] - 1;
            }
            return dp[n];
        }
        int i = 1;
        int ways = 0;
        int upperBound = complement > 0 ? Math.min(n, complement) : n;
        while ((int) Math.pow(i, x) < upperBound) i++;
        int lowerBound = 0;
        while (prefix[lowerBound] < n) lowerBound++;
        if (n == powers[i--] && n != complement) ways++;
        for (int j = i; j >= lowerBound && j > 0; j--) {
            int part2 = n - powers[j];
            if (part2 <= prefix[j - 1]) {
                ways += findWays(part2, powers[j], x);
            }
        }
        dp[n] = ways;
        return ways;
    }


    final static int MOD = 1_000_000_007;
    public static int numberOfWays(int n, int x) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = (int) Math.pow(i, x); // left part
            if (val > n) {
                break;
            }
            for (int j = n; j >= val; j--) {
                dp[j] = (dp[j] + dp[j - val]) % MOD; // this is your ++ operation,
                    // for each combination of j (that will be n, n-1, n-2...) and its
                    // complement you add up that to dp[j]
                    // but how did they figure out they could do the ++ this way?
            }
        }
        return (int) dp[n];
    }


    public static void main(String[] args) {
        int n = 14;
        int x = 1;
        int rt = numberOfWays(n, x);
        int rt2 = numberOfWays2(n, x);
        System.out.println("correct: " + rt);
        System.out.println(rt2);
    }
}