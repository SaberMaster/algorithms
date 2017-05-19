import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;


/**
 * @file   SearchInABitonicArray.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 17 14:21:30 2017
 *
 * @brief   An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers.
 Write a program that, given a bitonic array of n distinct integer values,
 determines whether a given integer is in the array.
 *
 *
 */

public class SearchInABitonicArray {

    private static int binarySearch(int[] a,
                                     int fromIndex,
                                     int toIndex,
                                    int key,
                                    int type) {
        int low = fromIndex;
        int high = toIndex - 1;


        while (low <= high) {
            int mid = (low + high) >>> 1;
            int result = compareExt(a, mid, type, key);
            if (1 == result)
                low = mid + 1;
            else if (-1 == result)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    private static int compareExt(int[] a,
                                      int index,
                                      int type,
                                      int key) {

        if (0 == type) {
            if (a[index] > a[index - 1]
                && a[index] < a[index + 1]) {
                return 1;
            }
            else if (a[index] < a[index - 1]
                     && a[index] > a[index + 1]) {
                return -1;
            }
            else return 0;
        }
        else if (1 == type) {
            if (a[index] < key) {
                return 1;
            }
            else if (a[index] > key) {
                return -1;
            }
            else return 0;
        }
        else {
            if (a[index] < key) {
                return -1;
            }
            else if (a[index] > key) {
                return 1;
            }
            else return 0;
        }
    }

    public static boolean searchInBitconicArray(int[] a, int key) {
        int maxIndex = binarySearch(a, 0, a.length, 0, 0);
        return binarySearch(a, 0, maxIndex + 1, key, 1) >= 0
            || binarySearch(a, maxIndex + 1, a.length, key, -1) >= 0;
    }
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(searchInBitconicArray(a, Integer.parseInt(args[1])));
    }
}
