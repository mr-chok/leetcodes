import java.util.Arrays;
import java.util.Collections;

class LargestTripleDigit {
    public static String largestGoodInteger(String num) {
        StringBuilder answer = new StringBuilder();
        Character[] chars = new Character[num.length()];
        for (int i = 0; i < num.length(); i++) {
            chars[i] = num.charAt(i);
        }
        Arrays.sort(chars, Collections.reverseOrder());
        for (int i = 0; i < chars.length; i++) {
            int j = i;
            answer.setLength(0);
            answer.append(chars[i]);
            while (++j < chars.length && chars[j] == chars[i]) {
                answer.append(chars[j]);
                i = j;
            }
        }
        return answer.length() >= 3 ? answer.substring(0, 3) : "";
//        return chars[0] == chars[1] && chars[2] == chars[1] ? "" + chars[0] + chars[0] + chars[0] : "";
    }

    public static void main (String[] args) {
        System.out.println(largestGoodInteger("2300019"));;
    }
}