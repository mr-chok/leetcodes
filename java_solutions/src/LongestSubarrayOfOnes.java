import java.util.ArrayList;

class LongestSubarrayOfOnes {
    public static int longestSubarray(int[] nums) {
        int max = 0;
        int current = 0, suffix = 0;
        boolean linked = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (!linked) current++;
                else suffix++;
            } else {
                if (linked || (i - 1 >= 0 && nums[i - 1] == 0)) {
                    if (max < current + suffix) max = current + suffix;
                    current = suffix;
                    suffix = 0;
                    linked = nums[i + 1] != 0;
                } else if (current > 0 && (i + 1 < nums.length && nums[i + 1] == 1)) {
                    linked = true;
                }
            }
            if (max < current + suffix) max = current + suffix;
        }
        return current == nums.length ? nums.length - 1 : max;
    }
    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[] {1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1}));
    }
}