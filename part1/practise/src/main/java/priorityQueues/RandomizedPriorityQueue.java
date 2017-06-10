/**
 * @file   RandomizedPriorityQueue.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun  8 10:36:09 2017
 *
 * @brief  Describe how to add the methods 𝚜𝚊𝚖𝚙𝚕𝚎() and 𝚍𝚎𝚕𝚁𝚊𝚗𝚍𝚘𝚖() to our binary heap implementation.
 The two methods return a key that is chosen uniformly at random among the remaining keys,
 with the latter method also removing that key.
 The 𝚜𝚊𝚖𝚙𝚕𝚎() method should take constant time; the 𝚍𝚎𝚕𝚁𝚊𝚗𝚍𝚘𝚖() method should take logarithmic time.
 Do not worry about resizing the underlying array.
 *
 *
 */

public class RandomizedPriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;
    private int randomIndex;

    public Key sample() {
        this.randomIndex = Math.uniform(this.N);
        return this.pq[this.randomIndex];
    }

    public void delRandom() {
        del(this.randomIndex);
    }

    public Key del(int index) {
        exch(index, N - 1);
        return this.pq[--N];
    }

    public void exch(int a, int b) {

    }
    public static void main(String[] args) {

    }
}
