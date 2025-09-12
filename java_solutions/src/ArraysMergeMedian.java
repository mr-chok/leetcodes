class ArraysMergeMedian {
    private int median;
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if (n + m == 0) return 0;

        boolean odd = (n + m) % 2 != 0;
        median = (n + m) / 2;
        int[] right = null, left = null;
        if (nums1.length == 0 || nums2.length == 0 || nums1[n - 1] <= nums2[0]) {
            right = nums2;
            left = nums1;
        } else if (nums1[0] >= nums2[m - 1]){
            right = nums1;
            left = nums2;
        }

        if (right != null) {
            if (left.length > median)
                return odd ? left[median] : (double) (left[median - 1] + left[median]) / 2;
            if (left.length == median)
                return odd ? right[0] : (double) (right[0] + left[median - 1]) / 2;
            else
                return odd ? right[median - left.length] : (double) (right[median - left.length - 1] + right[median - left.length]) / 2;
        }

        int l1 = 0, r1 = n - 1, l2 = 0, r2 = m - 1, m1 = r1 / 2, m2 = r2 / 2;

        int counter = 0;
        int c = 0;
        while (l1 <= r1 || l2 <= r2) {
             if (l2 <= r2) {
                int tmp = lowerBound(nums1, nums2[m2]);
                counter = tmp + m2 + 2;
                c++;
                if (counter < median) {
                    l2 = m2 + 1;
                } else if (counter > median) {
                    r2 = m2 - 1;
                } else {
                    m1 = tmp;
                    break;
                }
                m2 = l2 + (r2 - l2) / 2;
            }
            if (l1 <= r1) {
                int tmp = lowerBound(nums2, nums1[m1]);
                counter = tmp + m1 + 2;
                c++;
                if (counter < median) {
                    l1 = m1 + 1;
                } else if (counter > median) {
                    r1 = m1 - 1;
                } else {
                    m2 = tmp;
                    break;
                }
                m1 = l1 + (r1 - l1) / 2;
            }
        }
        if (odd) {
            return Math.min(
                    (m1 + 1 == n ? Integer.MAX_VALUE : nums1[m1 + 1]),
                    (m2 + 1 == m ? Integer.MAX_VALUE : nums2[m2 + 1])
            );
        } else {
            return (double)
                    (
                        Math.max(m1 >= 0 ? nums1[m1] : 0, m2 >= 0 ? nums2[m2] : 0) +
                        Math.min(
                                (m1 + 1 == n ? Integer.MAX_VALUE : nums1[m1 + 1]),
                                (m2 + 1 == m ? Integer.MAX_VALUE : nums2[m2 + 1])
                        )
                    ) / 2;
        }
    }

    private int lowerBound(int[] arr, int n) {
        int l1 = 0, r1 = arr.length - 1, m1 = 0;
        if (n < arr[l1]) {
            return -1;
        } else if (n >= arr[r1]) {
            return r1;
        } else {
            while (l1 <= r1) {
                m1 = l1 + (r1 - l1) / 2;
                if (n >= arr[m1]) l1 = m1 + 1;
                else r1 = m1 - 1;
            }
            if (arr[m1] > n) m1 = r1;
            return m1;
        }
    }

    public static void main(String[] args) {
        ArraysMergeMedian ac = new ArraysMergeMedian();
        int[] a1 = new int[] {1,3,4,5};
        int[] a2 = new int[] {2};
        System.out.println(ac.findMedianSortedArrays(a1, a2));
    }
}