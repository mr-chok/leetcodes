import java.util.HashSet;
import java.util.Set;

class JudgePoint24 {
    public static boolean judgePoint24(int[] cards) {
        double[] cards2 = new double[4];
        for (int i = 0; i < cards.length; i++) {
            cards2[i] = cards[i];
        }
        return process(cards2);
    }
    public static boolean process(double[] cards) {
        if (cards.length == 1) return cards[0] == 24 || Math.abs(24 - cards[0]) <= 1e-6;
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length ; j++) {
                if (j != i) {
                    for (int k = 0; k < 4; k++) {
                        double[] current = new double[cards.length - 1];
                        current[0] = getOperation(k, cards[i], cards[j]);
                        for (int l = 0, m = 0; l < cards.length; l++) {
                            if (l != i && l != j) {
                                current[m + 1] = cards[l];
                                m++;
                            }
                        }
                        if (process(current)) return true;
                    }
                }
            }
        }
        return false;
    }
    public static double getOperation(int index, double a, double b) {
        if (index == 0) return a + b;
        else if (index == 1) return a - b;
        else if (index == 2) return a * b;
        else return a / b;
    }
    public static void main(String[] args) {
//        int[] cards = new int[]{8, 3, 3, 8};
        int[] cards = new int[]{1, 1, 2, 2};
        System.out.println(judgePoint24(cards));
//        double x = (8 / (3 - ((double)8/3)) );
    }
}