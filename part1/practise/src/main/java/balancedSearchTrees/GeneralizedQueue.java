/**
 * @file   GeneralizedQueue.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun 15 09:58:36 2017
 *
 * @brief  Design a generalized queue data type that supports all of the following operations in logarithmic time (or better) in the worst case.

 Create an empty data structure.
 Append an item to the end of the queue.
 Remove an item from the front of the queue.
 Return the ith item in the queue.
 Remove the ith item from the queue.
 *
 *
 */

// build a RB-BST
// use the order of insert as the key
// while insert, insert length of the BST+1 as the key
// remove end , remove the max one(the rightest), update the count of node and parents
// remove front, remove the min one (the leftest), update the count of node and parents
// return ith, search the rank = i
// remove ith, del rank = i, update the count of node and parents


// Hint: create a red–black BST where the keys are integers and the values are the items such that the ith largest integer key in the red–black BST
// corresponds to the ith item in the queue.
