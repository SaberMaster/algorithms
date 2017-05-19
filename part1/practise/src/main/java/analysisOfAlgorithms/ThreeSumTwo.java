import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

/**
 * @file   3sum.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 17 14:20:47 2017
 *
 * @brief  3-SUM in quadratic time.
 Design an algorithm for the 3-SUM problem that takes time proportional to n2 in the worst case.
 You may assume that you can sort the n integers in time proportional to n2 or better.
 *
 *
 */


public class ThreeSumTwo {

    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            int k = n - 1;
            if (a[i] >= 0) break;
            while (j < k
                   && j < n - 1
                   && k > 0) {
                // StdOut.println(i);
                // StdOut.println(j);
                // StdOut.println(k);
                int result = a[k] + a[j];
                if (result > -a[i]) {
                    k--;
                }
                else if (result < -a[i]) {
                    j++;
                }
                else {
                    count++;
                    j++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Arrays.sort(a);
        StdOut.println(count(a));
    }
}
