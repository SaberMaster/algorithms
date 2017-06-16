/**
 * @file   DocumentSearch.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun 15 09:57:54 2017
 *
 * @brief  Design an algorithm that takes a sequence of n document words and a sequence of m query words and find the shortest interval in
 which the m query words appear in the document in the order given.
 The length of an interval is the number of words in that interval.
 *
 *
 */


// build a RB-BST the key is the word
// save the min interval to the node
// save the last index as the node value
// after insert word, if exists update min interval and last index,
// if not exists create, and the interval = -1
// after build the BST then search search


// Hint: for each word, maintain a sorted list of the indices in the document in which that word appears.
// Scan through the sorted lists of the query words in a judicious manner.
