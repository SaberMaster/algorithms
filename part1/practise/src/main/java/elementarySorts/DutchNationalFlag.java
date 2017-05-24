/**
 * @file   DutchNationalFlag.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 24 22:51:32 2017
 *
 * @brief
 * Dutch national flag. Given an array of n buckets,
   each containing a red, white, or blue pebble, sort them by color.
   The allowed operations are:

 swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 color(i): color of pebble in bucket i.
 The performance requirements are as follows:

 At most n calls to color().
 At most n calls to swap().
 Constant extra space.
 *
 */

public class DutchNationalFlag {
    private class NationalFlag {
        public int color() {
            return 0;
        }
    }
    public static void swap(NationalFlag[] a, int i, int j) {
        NationalFlag tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void main(String[] args) {
        // two point
        int n = 100;
        NationalFlag[] flags = new NationalFlag[n];
        int red = 0;
        int white = n - 1;
        for (int i = 0; i < white + 1; i++) {
            if (-1 == flags[i].color()) {
                swap(flags, i, red);
                red++;
            }
            else if (1 == flags[i].color()) {
                swap(flags, i, white);
                white--;
            }
        }
    }
}
