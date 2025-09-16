/**
 * @param {number[]} nums
 * @return {number[][]}
 */
// @ts-check
// var threeSumBinarySearch = function(nums) {
//     nums.sort((a, b) => { return a - b; });
//     const n = nums.length;
//     if (nums[nums.length - 1] < 0 || nums[0] > 0) return [];
//     if (
//         (nums[0] == 0 && nums[1] === 0 &&  nums[2] === 0) ||
//         (nums[n - 1] == 0 && nums[n - 2] === 0 &&  nums[n - 3] === 0)
//     ) return [ [0, 0, 0] ];

//     let num, n1, target, targetIndex, key;

//     let rightEndPair = nums[n - 1] + nums[n - 2];
//     let leftBound = rightEndPair >= -nums[0] ? 0 : upperbound(nums, -rightEndPair, 0, n - 1);
//     let rightBound = n - 1;
//     let result = [];
    
//     for (let i = leftBound; nums[i] <= 0; i++) {
//         if (nums[i] == 0) {
//             if (nums[i + 1] === 0 &&  nums[i + 2] === 0) result.push([0,0,0]);
//             break;
//         }
//         num = nums[i];
//         if (i > leftBound && nums[i] === nums[i - 1]) continue;

//         let sum = -(num + nums[i + 1]);
//         rightBound = upperbound(nums, sum, i + 2, rightBound);
//         if (nums[rightBound] > sum) rightBound--;
//         let right = rightBound;

//         for (let j = i + 1; j < right; j++) {
//             if (j > i + 1 && n1 == nums[j]) continue;
//             n1 = nums[j];
//             target = -num - n1;
//             if (target > nums[right]) continue;

//             targetIndex = binarySearch(nums, target, j + 1, right);
//             if (targetIndex != -1) {
//                 key = [num, n1, nums[targetIndex]];
//                 result.push(key);
//                 right = targetIndex - 1;
//             }
//         }
        
//     }
//     return result;
// };

// var binarySearch = function(arr, target, l, r) {
//     let mid;
//     while (l <= r) {
//         mid = (l + (r - l) / 2) | 0;
//         if (arr[mid] == target) return mid;
//         else if (arr[mid] < target) l = mid + 1;
//         else r = mid - 1;
//     }
//     return -1;
// }

// var upperbound = function(arr, target, l, r) {
//     let mid;
//     while (l <= r) {
//         mid = (l + (r - l) / 2) | 0;
//         if (arr[mid] == target) return mid;
//         else if (arr[mid] < target) l = mid + 1;
//         else r = mid - 1;
//     }
//     return l;
// }

var threeSum = function (nums) {
    nums.sort((a, b) => { return a - b; });
    let result = [], right = nums.length - 1;
    for (let i = 0; nums[i] <= 0; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        let p1 = i + 1, p2 = right;
        while (p1 < p2) {
            let sum = nums[i] + nums[p1] + nums[p2];
            if (sum === 0) {
                result.push([nums[i], nums[p1], nums[p2]]);
                p1++;
                p2--;
            }
            else if (sum < 0) p1++;
            else p2--;

            while (p1 < p2 && nums[p1] == nums[p1 - 1]) p1++;
            while (p1 < p2 && nums[p2] == nums[p2 + 1]) p2--;
        }
    }
    return result;
}

function test() {
    console.time("threeSum runtime");
    console.log(threeSum([2,-3,0,-2,-5,-5,-4,1,2,-2,2,0,2,-4,5,5,-10]));
    console.timeEnd("threeSum runtime");
}

test();