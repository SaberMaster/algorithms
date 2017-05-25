import edu.princeton.cs.algs4.StdOut;

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
        String[] strs = args[1].split(" ");
        RandomizedQueue<String> randQue = new RandomizedQueue<String>();
        for (int i = 0; i < strs.length; i++) {
            randQue.enqueue(strs[i]);
        }
        int count = 0;
        for (String a : randQue) {
            StdOut.println(a);
            count++;
            if (count >= num) break;
        }
    }
}
