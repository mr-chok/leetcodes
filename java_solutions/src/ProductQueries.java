import java.util.Arrays;

class ProductQueries {
    private static final int MOD = 1000000007;
    public static int[] productQueries(int n, int[][] queries) {
        int exponent = 15;
        int lowerBound = 1 << exponent;
        int[] powersOf2 = new int[31];
        int length = 0;
        while (n > lowerBound) {
            lowerBound *= 2;
            exponent++;
        }
        while (n < lowerBound) {
            lowerBound /= 2;
            exponent--;
        }

        while (n > 0) {
            powersOf2[exponent]++;
            n -= lowerBound;
            while (n < lowerBound) {
                lowerBound /= 2;
                exponent--;
            }
            length++;
        }
        int[] powers = new int[length];
        int powersIndex = 0;
        for (int i = 0; i < 31; i++) {
            if (powersOf2[i] > 0) {
                powers[powersIndex++] = i;
            }
        }
        int[] answers = new int[queries.length];
        Arrays.fill(answers, 1);
        for (int i = 0; i < queries.length; i++) {
            int answerExponent = 0;
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                answerExponent += powers[j];
            }

            if (answerExponent > 30) {
                int mod = (1 << 30) % MOD;

                long mod2 = 1;
                for (int k = 0; k < answerExponent / 30; k++) {
                    mod2 = (mod2 * mod) % MOD;
                }
                mod2 = (mod2 * (1 << (answerExponent % 30))) % MOD;
                answers[i] = (int) mod2;
            } else {
                answers[i] = (1 << answerExponent) % MOD;
            }
        }
        return answers;
    }

    public static void main (String[] args) {
        int[] lel = productQueries(508, new int[][] {{2,6}});
    }
}