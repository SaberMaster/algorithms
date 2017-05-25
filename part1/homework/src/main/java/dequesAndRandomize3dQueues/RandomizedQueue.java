import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * @file   RandomizedQueue.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu May 25 11:15:36 2017
 *
 * @brief
 *
 *
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] values;
    private boolean[] isDels;
    private int n;
    private int current;
    // construct an empty randomized queue
    public RandomizedQueue() {
        values = (Item[]) new Object[1];
        isDels = new boolean[1];
        isDels[0] = true;
        n = 0;
        current = 0;
    }
    // is the queue empty?
    public boolean isEmpty() {
        return n <= 0;
    }
    // return the number of items on the queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] newValues = (Item[]) new Object[capacity];
        boolean[] newIsDels = new boolean[capacity];
        for (int i = 0; i < capacity; i++) {
            newIsDels[i] = true;
        }
        for (int i = 0, j = 0; i < isDels.length; i++) {
            if (isDels[i]) continue;
            newValues[j] = values[i];
            newIsDels[j] = false;
            j++;
        }
        current = n;
        values = newValues;
        isDels = newIsDels;
    }
    // add the item
    public void enqueue(Item item) {
        if (null == item) throw new java.lang.NullPointerException();
        if (current >= values.length) {
            resize(values.length * 2);
        }
        values[current] = item;
        isDels[current] = false;
        current++;
        n++;
    }
    // remove and return a random item
    public Item dequeue() {
        if (0 >= size()) throw new java.util.NoSuchElementException();
        if (n * 4 <= values.length) {
            resize(values.length);
        }
        while (true) {
            int rand = StdRandom.uniform(values.length);
            if (!isDels[rand]) {
                isDels[rand] = true;
                n--;
                return values[rand];
            }
        }
    }
    // return (but do not remove) a random item
    public Item sample() {
        if (0 >= size()) throw new java.util.NoSuchElementException();
        while (true) {
            int rand = StdRandom.uniform(values.length);
            if (!isDels[rand]) {
                return values[rand];
            }
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int curr;
        private int[] rands;

        public RandomizedQueueIterator() {
            rands = new int[n];
            for (int i = 0, j = 0; i < isDels.length; i++) {
                if (isDels[i]) continue;
                rands[j++] = i;
            }
            for (int i = 0; i < rands.length; i++) {
                int rand = StdRandom.uniform(i + 1);
                swap(i, rand);
            }
            curr = 0;
        }
        private void swap(int i, int j) {
            int tmp = rands[i];
            rands[i] = rands[j];
            rands[j] = tmp;
        }

        public boolean hasNext() {
            return curr < rands.length;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return values[rands[curr++]];
        }
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    // unit testing (optional)
    public static void main(String[] args) {
        // RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();;
        // Iterator<Integer> it = rq.iterator();
    }
}
