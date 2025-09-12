class HillsValleys {
    public static int countHillValley(int[] nums) {
        int index = 1;
        boolean maybeHill = false;
        boolean maybeValley = false;
        int hillsValleys = 0;
        int left = nums[0];
        while (index < nums.length) {
            // System.out.println("iterating on index: "+index+", number: "+nums[index]);
            if (maybeValley || maybeHill) {
                if (maybeValley && nums[index] > nums[index-1]) {
                    maybeValley = false;
                    // System.out.println("\tincreasing valley on index "+(index-1)+" bc "+nums[index-1]+" is surrounded by "+left+", "+nums[index]);
                    hillsValleys++;
                    index--;
                    left = nums[index];
                } else if (maybeHill && nums[index] < nums[index-1]) {
                    maybeHill = false;
                    // System.out.println("\tincreasing hill on index "+(index-1)+" bc "+nums[index-1]+" is surrounded by "+left+", "+nums[index]);
                    hillsValleys++;
                    index--;
                    left = nums[index];
                } else if (nums[index] != nums[index-1]){
                    maybeHill = false;
                    maybeValley = false;
                    left = nums[--index];
                    // System.out.println("\t\tclearing guesses");
                }
                // System.out.println("\t\tleft is now "+left);
            } else {
                if (nums[index] < left) {
                    // System.out.println("\tfound a possible valley bc adjacent left is "+left);
                    maybeValley = true;
                } else if (nums[index] > left) {
                    // System.out.println("\tfound a possible hill bc adjacent left is "+left);
                    maybeHill = true;
                }
            }
            index++;
        }
        return hillsValleys;
    }

    public static void main(String[] args) {
        int[] nums = {6,6,5,5,4,1};
        // System.out.print("max: " + countHillValley(nums));
    }
}