/**
 * @file   MergingWithSmallerAuxiliaryArray.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun  1 21:10:17 2017
 *
 * @brief  Suppose that the subarray 𝚊[𝟶] to 𝚊[𝚗−𝟷] is sorted and the subarray 𝚊[𝚗] to 𝚊[𝟸∗𝚗−𝟷] is sorted.
 How can you merge the two subarrays so that 𝚊[𝟶] to 𝚊[𝟸∗𝚗−𝟷] is sorted using an auxiliary array of length n (instead of 2n)?
 *
 *
 */

public class MergingWithSmallerAuxiliaryArray {
    public static void sort(int[] a, int[] aux, int i, int j) {
        int k = 0;
        while(k < n) {
            if (a[i] <= a[j]) {
                aux[k++] = a[i++];
            }
            else {
                aux[k++] = a[j++];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] a = new int[2 * n];
        int[] auxiliary = new int[n];
        int i = 0, j = n;
        //get first n auxiliary array
        //fill all of the auxiliary array
        i = sort(a, auxiliary, i, j);
        //get the first n part index i, move[i, n-1] to [n, 2*n-1-i]
        for (int m = 0; m < n - i; m++) {
            a[n + m] = a[i + m];
        }
        for (int x = 0; x < n; x++) {
            a[x] = auxiliary[x];
        }
        //then sorted [n, 2*n-1]
        sort(a, auxiliary, n, n + (n - i));
        for (int x = 0; x < n; x++) {
            a[n + x] = auxiliary[x];
        }
    }
}
