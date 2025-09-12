import java.util.Stack;

class MinimumOnesArea2 {
    public int minimumSum(int[][] grid) {
        // best case scenario: 3 1s and 27 0s
        // worst case scenario: 30 1's or  
        // IF A CONCATENATION OF 0s IS ON THE EDGE, it will reduce the
        // total area automatically by the length of the concatenation
        // any 0 that stays surrounded by 1s will should be considered as 1?
        int[] leftBounds = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            leftBounds[i] = -1;
        }
        int[] rightBounds = new int[grid.length];
        int[] topBounds = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            topBounds[i] = -1;
        }
        int[] botBounds = new int[grid[0].length];

        int top = -1, bot = -1, left = -1, right = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (top == -1) top = i;
                    if (bot == -1 || bot < i) bot = i;
                    if (left == -1 || left > j) left = j;
                    if (right == -1 || right < j) right = j;
                    if (leftBounds[i] == -1) leftBounds[i] = j;
                    rightBounds[i] = j;
                    if (topBounds[j] == -1) topBounds[j] = i;
                    botBounds[j] = i;
                }
            }
        }
        
        Stack<Integer> ts = new Stack<>(), bs = new Stack<>(), ls = new Stack<>(), rs = new Stack<>();
        int currentZeroArea, maxZeroVerticalArea = 0, maxZeroHorizontalArea = 0, w1, h1, w2, h2, leftArea, rightArea, topArea, botArea, commonArea = 0;
        for (int i = top; i <= bot; i++) {
            leftArea = 0; rightArea = 0;
            if (leftBounds[i] == -1) leftBounds[i] = bot + 1;
            while (!ls.isEmpty() && leftBounds[i] <= leftBounds[ls.peek()]) {
                ls.pop();
            }
            w1 = leftBounds[i] - left;
            h1 = ls.isEmpty() ? i - top : i - ls.peek() - 1;
            leftArea = w1 * h1;
            maxZeroHorizontalArea = Math.max(leftArea, maxZeroHorizontalArea);
            ls.push(i);
            if (w1 == bot + 1 - left) continue;

            while (!rs.isEmpty() && rightBounds[i] > rightBounds[rs.peek()]) {
                ls.pop();
            }
            w2 = right - rightBounds[i];
            h2 = rs.isEmpty() ? i - top : i - rs.peek() - 1;
            rightArea = w2 * h2;
            maxZeroHorizontalArea = Math.max(rightArea, maxZeroHorizontalArea);
            rs.push(i);

            if (rightArea > 0 && leftArea > 0) {
                int minH = Math.min(h1, h2);
            }
        }
//        for (int j = left; j <= right; j++) {
//            currentZeroArea = topBounds[j] - top;
//            maxZeroVerticalArea = Math.max(maxZeroVerticalArea, currentZeroArea);
//            tw = 1;
//            while (!ts.isEmpty() && topBounds[j] < topBounds[ts.peek()]) {
//                int h = topBounds[ts.pop()] - top;
//                tw = ts.isEmpty() ? j - left : j - ts.peek() - 1;
//                currentZeroArea = tw * h;
//                maxZeroVerticalArea = Math.max(maxZeroVerticalArea, currentZeroArea);
//            }
//            ts.push(j);
//
//            while (!bs.isEmpty() && botBounds[j] > botBounds[bs.peek()]) {
//                int h = bot - botBounds[bs.pop()];
//                bw = bs.isEmpty() ? j - left : j - bs.peek() - 1;
//                if (tw == bw) currentZeroArea += bw * h;
//                else currentZeroArea = bw * h;
//                maxZeroVerticalArea = Math.max(maxZeroVerticalArea, currentZeroArea);
//            }
//            bs.push(j);
//        }
        return 0;
    }
    public static void main(String[] args) {
        MinimumOnesArea2 ms = new MinimumOnesArea2();
        int[][] grid = new int[][] {
                {0,0,1,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,0,0,1,1,0},
                {1,1,0,0,1,1,0},
                {1,0,0,1,1,0,0}
        };
        ms.minimumSum(grid);
    }
}