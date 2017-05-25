import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

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
        int[] rands = new int[strs.length];
        for (int i = 0; i < rands.length; i++) {
            rands[i] = i;
        }
        for (int i = 0; i < rands.length; i++) {
            int rand = StdRandom.uniform(i + 1);
            int tmp = rands[i];
            rands[i] = rands[rand];
            rands[rand] = tmp;
        }
        RandomizedQueue<String> randQue = new RandomizedQueue<String>();
        if (num <= 0) return;
        for (int i = 0; i < num; i++) {
            randQue.enqueue(strs[rands[i]]);
        }
        for (String a : randQue) {
            StdOut.println(a);
        }
    }
}
