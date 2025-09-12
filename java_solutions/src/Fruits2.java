class Fruits2 {
    public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        boolean found = false;
        int n = baskets.length;
        int[] gathered = new int[n];
        int missing = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (!found && j < n) {
                if (fruits[i] <= baskets[j] && gathered[j] < 1) {
                    found = true;
                    gathered[j] = fruits[i];
                }
                j++;
            }
            if (!found) missing++;
            found = false;
        }
        return missing;
    }

    public static void main(String[] args) {
        numOfUnplacedFruits(new int[]{4,2,5}, new int[]{3,5,4});
    }
}