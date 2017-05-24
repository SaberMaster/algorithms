import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
/**
 * @file   StackWithMax.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 24 19:56:35 2017
 *
 * @brief
 * Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation.
   Assume the elements are reals numbers so that you can compare them.
 *
 */

public class StackWithMax {
    private Stack<Integer> values;
    private Stack<Integer> maxs;

    public StackWithMax() {
        values = new Stack<Integer>();
        maxs = new Stack<Integer>();
    }

    public void push(int item) {
        if (maxs.isEmpty()) maxs.push(item);
        else maxs.push(Math.max(maxs.peek(), item));
        values.push(item);
    }

    public int pop() {
        maxs.pop();
        return values.pop();
    }

    public int max() {
        return maxs.peek();
    }


    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StackWithMax myStack = new StackWithMax();
        for (int i : a) {
            myStack.push(i);
        }

        StdOut.println(myStack.max());
    }
}
