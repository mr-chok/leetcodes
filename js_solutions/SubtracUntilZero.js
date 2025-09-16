// @ts-check
let ops = [];
const s = new Map;
const subtractUntilZero = function(num1, num2) {
    if (num1 <= num2) return -1;
    let k = 1;
    let x = num1;
    while (x > 0) {
        x = num1 - k * num2;
        if (k >= bitCount(x)) return k;
        k++;
    }
    return -1;
};

function bitCount(n) {
  let count = 0;
  while (n) {
    n &= n - 1;
    count++;
  }
  return count;
}

console.time("subtractUntilZero");
console.log(subtractUntilZero(300340230, 114));
console.timeEnd("subtractUntilZero");