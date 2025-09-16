/**
 * @param {number[]} nums
 * @return {number[]}
 */
// @ts-check
var nextPermutation = function(nums) {
    let i = nums.length - 1;
    while (i > 0 && nums[i - 1] === Math.max(nums[i], nums[i - 1])) i--;
    if (i == 0) return nums.sort((a,b) => a - b);

    const maxNumber = 101;
    let max = maxNumber;
    let swapIndex = -1;
    for (let j = i; j < nums.length; j++) {
        if (nums[i - 1] >= nums[j]) continue;
        let candidate = Math.max(nums[i - 1], nums[j]);
        swapIndex = candidate < max ? j : swapIndex;
        max = Math.min(candidate, max);
    }

    if (swapIndex >= 0) {
        const tmp = nums[i - 1];
        nums[i - 1] = nums[swapIndex];
        nums[swapIndex] = tmp;
    }

    
    let j = i;
    const sub = nums.slice(i, nums.length).sort((a, b) => a - b);
    for (const element of sub) nums[j++] = element;
    
    return nums;
};

function test2() {
    console.log(nextPermutation([6,5,1]));
}

test2();

/**
 * 1 2 13 13 14
 * 1 2 13 14 13
 * 1 2 14 13 13
 * 1 13 2 13 14
 * 1 13 2 14 13
 * 1 13 13 2 14
 * 1 13 13 14 2
 * 1 13 14 2 13
 * 1 13 14 13 2
 * 1 14 2 13 13
 * 1 14 13 2 13
 * 1 14 13 13 2
 * 2 1 13 13 14
 * 2 1 13 14 13
 * 2 1 14 13 13
 * 2 13 1 13 14
 * 2 13 1 14 13
 * 2 13 13 1 14
 * 2 13 13 14 1
 * 2 14 1 13 13
 * 2 14 13 1 13
 * 2 14 13 13 1
 * 13 1 2 13 14
 * 
 * 5 4 3 2 1
 * 
 */