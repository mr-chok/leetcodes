class ZeroFilledSubarrays {
    public static long zeroFilledSubarray(int[] nums) {
        long counter = 0;
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                counter++;
            } else if (counter > 0) {
                result += counter * (counter + 1) / 2;
                counter = 0;
            }
        }
        if (counter > 0) {
            result += counter * (counter + 1) / 2;
        }
        return result;
    }
    public static void main(String[] args) {
        long z = zeroFilledSubarray(new int[100000]);
        System.out.println(z);
    }
}