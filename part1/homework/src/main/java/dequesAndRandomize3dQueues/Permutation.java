import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * @file   Permutation.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu May 25 13:37:16 2017
 *
 * @brief
 *
 *
 */

public class Permutation {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        String[] strs = StdIn.readAllStrings();
        RandomizedQueue<String> randQue = new RandomizedQueue<String>();
        for (int i = 0; i < strs.length; i++) {
            if (i > num - 1) {
                randQue.dequeue();
            }
            randQue.enqueue(strs[i]);
        }
        int count = 0;
        for (String a : randQue) {
            if (count > num - 1) break;
            StdOut.println(a);
            count++;
        }
    }
}
