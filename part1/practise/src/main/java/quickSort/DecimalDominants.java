/**
 * @file   DecimalDominants.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  2 01:13:23 2017
 *
 * @brief  Given an array with n keys, design an algorithm to find all values that occur more than n/10 times.
 The expected running time of your algorithm should be linear.
 *
 *
 */

public class DecimalDominants {
// Hint: determine the (n/10)th largest key using quickselect and check if it occurs more than n/10 times.

// Alternate solution hint: use 9 counters.


    Remember up to three elements, together with counters.

//         remember first element, set count1 = 1
//         scan until you find first different element, increasing count1 for each occurrence of element 1
//         remember second elemt, set count2 = 1
//         scan until you find first element different from elem1 and elem2, incrementing count1 or count2, depending which element you see
//         remember third element, set count3 = 1
//         continue scanning, if the element is one of the remembered, increment its count, if it's none of the remembered, decrement all three counts; if a count drops to 0, forget the element, go to step 1, 3, or 5, depending on how many elements you forget
// If you have three elements occurring strictly more than one-fourth times the number of elements in the array, you will end up with three remembered elements each with positive count, these are the three majority elements.
// Small constant additional space, O(n), no sorting.
    public static void main(String[] args) {
        // use hash table
        // dict
        // store times on table
    }
}
