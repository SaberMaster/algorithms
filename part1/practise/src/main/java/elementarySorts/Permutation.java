import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;


/**
 * @file   Permutation.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 24 22:31:56 2017
 *
 * @brief  Given two integer arrays of size n,
 design a subquadratic algorithm to determine whether one is a permutation of the other. That is,
 do they contain exactly the same entries but, possibly, in a different order.
 *
 *
 */

public class Permutation {
    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        Integer[] b = new Integer[10];
        // sort
        Shell.sort(a);
        Shell.sort(b);
        // comapre
        for (int i = 0; i < a.length; i++) {
            if (0 != a[i].compareTo(b[i])) {
                StdOut.println("no");
                return;
            }
        }
        StdOut.println("yes");
    }
}
