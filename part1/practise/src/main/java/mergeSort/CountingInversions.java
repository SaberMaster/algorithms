/**
 * @file   CountingInversions.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun  1 22:05:08 2017
 *
 * @brief  An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j].
 Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 *
 */


public class CountingInversions {
    public static int sort (int[] a) {
        int[] aux = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        sort(aux, a, 0, a.length - 1);
    }

    public static int sort (int[] a, int[] aux, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = (lo + hi) / 2;
        int revCount = 0;
        revCount += sort(aux, a, lo, mid);
        revCount += sort(aux, a, mid + 1, hi);
        return revCount + merge(a, aux, lo, hi, mid);
    }

    public static int merge(int[]a, int[]aux, int lo, int hi, int mid) {
        int i = lo;
        int j = mid + 1;
        int revCount = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                revCount += getOffset(j, k);
                aux[k] = a[j++];
            }
            else if (j > hi) {
                revCount += getOffset(i, k);
                aux[k] = a[i++];
            }
            else if (a[j] < a[i]) {
                revCount += getOffset(j, k);
                aux[k] = a[j++];
            }
            else {
                revCount += getOffset(i, k);
                aux[k] = a[i++;]
            }
        }
        return revCount;
    }

    public static int getOffset(int form, int to) {
        if (from < to) {
            return to - from;
        }
        return 0;
    }
    public static void main(String[] args) {

    }
}
