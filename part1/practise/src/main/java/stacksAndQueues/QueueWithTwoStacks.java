import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * @file   QueueWithTwoStacks.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 24 19:57:48 2017
 *
 * @brief
 * Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
 *
 */

public class QueueWithTwoStacks<Item> {
    private Stack<Item> in, out;

    public QueueWithTwoStacks() {
        in = new Stack<Item>();
        out = new Stack<Item>();
    }

    public boolean isEmpty() {
        return in.isEmpty()
            && out.isEmpty();
    }
    public void enqueue(Item item) {
        in.push(item);
    }

    public Item dequeue() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.push(in.pop());
        }
        return out.pop();
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        QueueWithTwoStacks<Integer> myQueue = new QueueWithTwoStacks<Integer>();
        for (int i : a) {
            myQueue.enqueue(i);
        }

        while (!myQueue.isEmpty()) StdOut.println(myQueue.dequeue());
    }
}
