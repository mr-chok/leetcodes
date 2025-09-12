import java.util.HashMap;

public class ReorderedPowerOf2 {
    public static boolean reorderedPowerOf2(int n) {
        if (n == 0) return false;
        HashMap<Integer, Integer> powersKeyedByDigits = new HashMap<>();
        int[] digits = new int[10];
        powersKeyedByDigits.put(1,1);
        int power = 1;
        int tenPower = 1;
        for (int i = 1; i < 31; i++) {
            if (power == n) return true;
            power *= 2;
            if (power > Math.pow(10, tenPower)) {
                powersKeyedByDigits.put(++tenPower, power);
            }
        }

        // iterate over the interval of powers that holds
        // the same amount of digits of n
        // explode n
        int explode = n;
        int digitsOfN = 0;
        while (explode != 0) {
            digits[explode % 10]++;
            explode /= 10;
            digitsOfN++;
        }

        int lowerBound = powersKeyedByDigits.get(digitsOfN);
        int upperBound = powersKeyedByDigits.getOrDefault(digitsOfN + 1, Integer.MAX_VALUE);
        boolean isPower = false;
        while (lowerBound > 0 && lowerBound < upperBound && !isPower) {
            // check if all the digits of lowerBound are present in digits[]
            isPower = compareDigits(lowerBound, digits);
            lowerBound *= 2;
        }
        return isPower;
    }

    public static boolean compareDigits (int number, int[] digits) {
        int[] digitsCopy = digits.clone();
        while (number != 0) {
            digitsCopy[number % 10]--;
            number /= 10;
        }
        for (int i = 0; i < 10; i++) {
            if (digitsCopy[i] != 0) return false;
        }
        return true;
    }
    public static  void main(String[] args) {
        boolean f = reorderedPowerOf2(Integer.MAX_VALUE);
        System.out.print(f);
    }
}