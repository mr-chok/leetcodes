class PowerOfFour {
    public static boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        if ((n & n-1) != 0) return false;
        int counter = 0;
        while (n > 1) {
            n = n >> 1;
            counter++;
        }
        return counter % 2 == 0;
    }
    public static void main(String[] args) {
        System.out.println(3 | 5);
    }
}