/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function(nums, target) {
    // variant of a binary search
    // just move left or right depending on the target and the reference pointer taken (right or left)
    if (target < nums[0] && target > nums[nums.length - 1]) {
        return -1;
    }

    let right = nums.length - 1;
    let left = 0;
    if (target >= nums[0]) {
        while (left <= right) {
            let mid = left + (right - left) / 2;
            if (nums[mid] > target || nums[mid] < nums[left]) {
                right = mid - 1;
            } else if (nums[mid] < target && nums[mid] >= nums[left]) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
    } else if (target <= nums[nums.length - 1]) {
        while (left <= right) {
            let mid = left + (right - left) / 2;
            if (nums[mid] < target || nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] > target && nums[mid] <= nums[right]) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
    }
    return -1;
};