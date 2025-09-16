/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
// @ts-check
class ListNode {
  constructor(val, next = null) {
    this.val = (val===undefined ? 0 : val);
    this.next = (next===undefined ? null : next);
  }
};


/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
function addTwoNumbers(l1, l2) {
    let head = new ListNode(), p1 = l1, p2 = l2;
    let carry = 0;
    let current = head, prev;

    while (p1 !== null || p2 !== null) {
      prev = current;
      const sum = (p1?.val ?? 0) + (p2?.val ?? 0) + carry;
      carry = Number(sum >= 10);
      const value = sum % 10;
      current = new ListNode(value);
      prev.next = current;
      p1 = p1?.next ?? null;
      p2 = p2?.next ?? null;
    }

    if (carry > 0) current.next = new ListNode(carry);

    return head;
};

export function test() {
  const h0 = new ListNode(9);
  const h1 = new ListNode(9,h0);
  const h2 = new ListNode(9,h1);
  const h3 = new ListNode(9,h2);
  const h4 = new ListNode(9,h3);
  const h5 = new ListNode(9,h4);
  const l1 = new ListNode(9,h5);

  const f3 = new ListNode(9);
  const f2 = new ListNode(9,f3);
  const f1 = new ListNode(9,f2);
  const l2 = new ListNode(9,f1);
  console.log(addTwoNumbers(l1, l2));
}