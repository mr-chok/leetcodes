/**
 * 
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
// @ts-check
var threeSumCloses = function(nums, target) {
    nums.sort((a, b) => a - b);
    let s = [];
    let min = Number.POSITIVE_INFINITY;
    let minSum = nums[0] + nums[1] + nums[2];
    for (let i = 0; i < nums.length && s.length == 0; i++) {
        let p1 = i + 1;
        let p2 = nums.length - 1;
        let sum = nums[i] + nums[p1] + nums[p2];
        
        while (p1 < p2 && s.length == 0) {
            if (Math.abs(target - sum) < min) {
                min = Math.abs(target - sum);
                minSum = sum;
            }
            if (sum > target) p2--;
            else if (sum == target) s = [nums[i], nums[p1], nums[p2]];
            else p1++;
            sum = nums[i] + nums[p1] + nums[p2];
        }
    }
    return s.length != 0 ? s[0] + s[1] + s[2] : minSum;
}

console.log(threeSumCloses([1,2,7,13], 12));
// [-4,-1,1,2] -3 -1