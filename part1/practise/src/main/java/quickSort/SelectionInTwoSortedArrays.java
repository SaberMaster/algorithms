/**
 * @file   SelectionInTwoSortedArrays.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  2 00:24:03 2017
 *
 * @brief  Given two sorted arrays a[] and b[],
 of sizes n1 and n2, respectively, design an algorithm to find the kth largest key.
 The order of growth of the worst case running time of your algorithm should be logn,
 where n=n1+n2.

 Version 1: n1=n2 and k=n/2
 Version 2: k=n/2
 Version 3: no restrictions
 *
 *
 */

/**
 * @file   SelectionInTwoSortedArrays.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  2 01:50:23 2017
 *
 * @brief
 *
 *
 */
public class SelectionInTwoSortedArrays {
// Hint: there are two basic approaches.

// Approach A: Compute the median in a[] and the median in b[]. Recur in a subproblem of roughly half the size.
// Approach B: Design a constant-time algorithm to determine whether a[i] is the kth largest key. Use this subroutine and binary search.
// Dealing with corner cases can be tricky.
    public int search (int[] a, int[] b, int k) {
        return search(a, b, k, a.length - k, a.length - 1);
    }

    public int search (int[] a, int[] b,int k, int lo, int hi) {
        int offset_a = (lo + hi) / 2;
        int offset_b = b.length - (k - (a.length - offset_a));
        if (a <)
        int min = Math.min(a[offset_a], b[offset_b]);
        if (a[offset_a - 1] <= min && b[offset_b - 1] <= min)
            return min;
        else if (a[offseta - 1] > min) {
            search(a, b, k, lo, offset_a);
        } else if (b[offset_b - 1] > min) {
            search(a, b, k, offset_a, hi);
        }
    }
    public static void main(String[] args) {

    }
}
