class PowerOfThree {
    public static boolean isPowerOfThree(int n) {
        if (n > 3 && n % 3 != 0) return false;
        int num = 1;
        while (num > 0 && num < n) {
            num *= 3;
        }
        return num == n;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(2413));
    }
}