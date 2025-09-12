import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class SubarraysBitwiseOr {
    private static HashSet<Integer> dp = new HashSet<Integer> ();
    private static int accumulation = 0;
    public static int subarrayBitwiseORs(int[] arr) {
        for (int num : arr) {
            processDp(num);
        }
        return dp.size();
    }
    public static void processDp(int newNum) {
        if (dp.contains(newNum)) return;
        ArrayList<Integer> helper = new ArrayList<>();
        for (int num : dp) {
            if ((newNum | num) != newNum && !dp.contains(newNum | num)) helper.add(newNum | num);
        }
        for (int newOrResult : helper) {
            processDp(newOrResult);
        }
        dp.add(newNum);

        accumulation |= newNum;
        processDp(accumulation);
    }
    public static void main(String[] args) {
        System.out.print(subarrayBitwiseORs(new int[] {1,2,4}));
    }
}