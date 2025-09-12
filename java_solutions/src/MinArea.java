class MinArea {
    public static int minArea(int[][] grid) {
        int h = grid.length - 1, h0 = 0, w = grid[0].length - 1, w0 = 0;
        int top = -1, bot = -1, left = -1, right = -1;
        while (h0 <= h && (top == -1 || bot == -1)) {
            for (int i = 0; i <= w; i++) {
                if (grid[h0][i] == 1 && top == -1) {
                    top = h0;
                }
                if (grid[h][i] == 1 && bot == -1) {
                    bot = h;
                }
            }
            if (top == -1) h0++;
            if (bot == -1) h--;
        }

        while (w0 <= w && (left == -1 || right == -1)) {
            for (int i = top; i <= bot; i++) {
                if (grid[i][w0] == 1 && left == -1) {
                    left = w0;
                }
                if (grid[i][w] == 1 && right == -1) {
                    right = w;
                }
            }
            if (left == -1) w0++;
            if (right == -1) w--;
        }
        if (top == -1 && bot == -1 && right == -1 && left == -1) return 0;
        return (bot - top + 1) * (right - left + 1);
    }
     public static void main(String[] args) {
        int[][] mat = new int[][] {
                {0,1,0},
                {0,1,0},
                {0,1,1},
                {0,1,0},
                {0,0,0},
                {0,0,0}
        };
        System.out.println(minArea(mat));
     }
}