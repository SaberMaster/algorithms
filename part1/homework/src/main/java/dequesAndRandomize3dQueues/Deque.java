/**
 * @file   Deque.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu May 25 11:15:43 2017
 *
 * @brief
 *
 *
 */


import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Item[] values;
    private int first, last;
    // construct an empty deque
    public Deque() {
        values = (Item[]) new Object[1];
        first = 0;
        last = 0;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return first <= last;
    }
    // return the number of items on the deque
    public int size() {
        return first - last;
    }

    /**
     * resize array size
     *
     * @param mode
     */
    private void resize(int mode) {
        int length = 0;
        if (1 == mode) {
            length = values.length * 2;
        }
        else {
            length = values.length / 2;
        }
        Item[] newValues = (Item[]) new Object[length];
        int newLast = (length - size()) / 2;
        int newFirst = newLast + size();
        for (int i = last; i < first; i++) {
            newValues[newLast + (i - last)] = values[i];
        }
        last = newLast;
        first = newFirst;
        values = newValues;
    }
    // add the item to the front
    public void addFirst(Item item) {
        if (null == item) throw new java.lang.NullPointerException();
        if (first >= values.length - 1) resize(1);
        values[first++] = item;
    }
    // add the item to the end
    public void addLast(Item item) {
        if (null == item) throw new java.lang.NullPointerException();
        if (last <= 1) resize(1);
        values[--last] = item;
    }
    // remove and return the item from the front
    public Item removeFirst() {
        if (size() <= 0) throw new java.util.NoSuchElementException();
        if (size() <= values.length / 4) resize(2);
        return values[--first];
    }
    // remove and return the item from the end
    public Item removeLast() {
        if (size() <= 0) throw new java.util.NoSuchElementException();
        if (size() <= values.length / 4) resize(2);
        return values[last++];
    }
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int current = last;
        private int myMax = first;
        public boolean hasNext() {
            return current < myMax;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return values[current++];
        }
    }
    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> de = new Deque<Integer>();
        de.addFirst(1);
        de.addLast(2);
        de.addLast(2);
        de.addLast(2);
        de.removeFirst();
        de.removeLast();
        // de.addFirst(2);
        for (int x : de) {
            StdOut.println(x);
        }
        de.addLast(4);
    }
}
