import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

class MaxTankCapacity {
    public int maxArea(int[] height) {
        LinkedList<Integer> stack = new LinkedList<> ();
        stack.add(0);
        int maxArea = Integer.MIN_VALUE, currentArea;
        for (int i = 1; i < height.length; i++) {
            // look for the most left h gte h[i]
            for (int j = stack.size() - 1; j >= 0; j--) {
                currentArea = Math.min(height[i], height[stack.peek()]) * (i - stack.peek());
                if (maxArea < currentArea)
                    maxArea = currentArea;
            }
            if (stack.isEmpty() || height[i] > height[stack.peek()]) stack.push(i);
        }
        return maxArea;
    }

    public int maxArea2(int[] height) {
        int i = 0, j = height.length - 1, max = Integer.MIN_VALUE, a;
        while(i < j) {
            a = (j - i) * Math.min(height[i], height[j]);
            if (a > max) max = a;
            if (height[i] < height[j]) i++;
            else j--;
        }
        return max;
    }
    public static void main(String[] args) {
        int[] h = new int[] {1,8,6,2,5,4,8,3,7};
        MaxTankCapacity m = new MaxTankCapacity();
        System.out.println(m.maxArea2(h));
    }
}