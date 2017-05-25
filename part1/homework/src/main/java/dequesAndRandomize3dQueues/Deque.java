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
    private DoubleEndedQueue<Item> first, last;
    private int n;
    private class DoubleEndedQueue<Item> {
        private Item value;
        private DoubleEndedQueue<Item> previous, next;

        public DoubleEndedQueue() {
            previous = null;
            next = null;
        }
    }
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return 0 >= n;
    }
    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (null == item) throw new java.lang.NullPointerException();
        DoubleEndedQueue<Item> oldFirst = first;
        first = new DoubleEndedQueue<Item>();
        first.value = item;
        first.next = oldFirst;
        if (0 != n) oldFirst.previous = first;
        else last = first;
        n++;
    }
    // add the item to the end
    public void addLast(Item item) {
        if (null == item) throw new java.lang.NullPointerException();
        DoubleEndedQueue<Item> oldLast = last;
        last = new DoubleEndedQueue<Item>();
        last.value = item;
        last.previous = oldLast;
        if (0 != n) oldLast.next = last;
        else first = last;
        n++;
    }
    // remove and return the item from the front
    public Item removeFirst() {
        if (size() <= 0) throw new java.util.NoSuchElementException();
        Item value = first.value;
        first = first.next;
        if (1 < n) first.previous = null;
        else last = first;
        n--;
        return value;
    }
    // remove and return the item from the end
    public Item removeLast() {
        if (size() <= 0) throw new java.util.NoSuchElementException();
        Item value = last.value;
        last = last.previous;
        if (1 < n) last.next = null;
        else first = last;
        n--;
        return value;
    }
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private DoubleEndedQueue<Item> current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item value = current.value;
            current = current.next;
            return value;
        }
    }
    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> de = new Deque<Integer>();
        de.addFirst(1);
        StdOut.println(de.removeLast());
        // de.addFirst(1);
        // de.addLast(2);;
        // de.addLast(2);
        // de.addLast(2);
        // // de.removeFirst();;
        // // de.removeLast();
        // de.addFirst(2);
        for (int x : de) {
            StdOut.println(x);
        }
        de.addLast(4);
    }
}
