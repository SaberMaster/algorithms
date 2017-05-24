import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;


/**
 * @file   IntersectionOfTwoSets.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 24 22:16:49 2017
 *
 * @brief
 * Given two arrays 𝚊[] and 𝚋[],
 each containing n distinct 2D points in the plane,
 design a subquadratic algorithm to count the number of points that are contained both in array 𝚊[] and array 𝚋[].
 *
 */


public class IntersectionOfTwoSets {
    public static void main(String[] args) {
        Point2D[] a, b;
        a = new Point2D[10];
        b = new Point2D[10];
        // merge
        Point2D[] c = new Point2D[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            c[i + a.length] = b[i];
        }
        // sort
        Shell.sort(c);
        // count
        int count = c.length;
        Point2D last = c[0];
        for (int i = 1; i < c.length; i++) {
            if (0 == last.compareTo(c[i])) count--;
            else last = c[i];
        }
        StdOut.println(count);
    }
}
