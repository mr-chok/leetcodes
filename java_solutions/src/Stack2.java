import java.util.Stack;

class Stack2 {
    public static boolean isBalanced(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') counter++;
            else if (str.charAt(i) == ')' && counter > 0) {
                counter--;
            } else if (str.charAt(i) == ')' && counter <= 0) {
                return false;
            }
        }
        return counter == 0;
    }

    public static void main(String[] args) {
        System.out.println("1 - " + isBalanced("()")); // true
        System.out.println("2 - " + isBalanced("(()")); // false
        System.out.println("3 - " + isBalanced("(()())")); // true
        System.out.println("4 - " + isBalanced("(test) ()")); // true
        System.out.println("5 - " + isBalanced("(test) (test")); // false
        System.out.println("6 - " + isBalanced(")(")); // false
        System.out.println("7 - " + isBalanced("(((((((((())))))))))")); // true
        System.out.println("8 - " + isBalanced("test")); // true
        System.out.println("9 - " + isBalanced("()()")); // true
        System.out.println("10 - " + isBalanced("() test) test () (")); // false
    }
}