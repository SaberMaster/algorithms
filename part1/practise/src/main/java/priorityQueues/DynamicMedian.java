/**
 * @file   DynamicMedian.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun  8 10:35:39 2017
 *
 * @brief  Dynamic median. Design a data type that supports insert in logarithmic time,
 find-the-median in constant time, and remove-the-median in logarithmic time.
 *
 *
 */

public class DynamicMedian {

    private class MedianTree<Comparable> {
        private Comparable[] arr;
        private int size;

        public MedianTree(int capacity) {
            this.arr = new Comparable[capacity];
            this.size = 0;
        }

        public void insert(Comparable a) {
            if (this.size < this.arr.length) {
                a[size++] = a;
                swim(size);
            }
            // throw exception
        }

        private void swim(int index) {
            // to swim the index elem
            // compare with index / 2
        }

        private void swap(int a, int b) {

        }

        private void sink(int a) {

        }


        private int getMediaIndex() {
            // return the mid one
            if (1 == this.size) return 0;

            int cmp = this.arr[size / 2].compareTo(this.arr[1 + size / 2]);
            if (1 == this.size % 2) {
                if (cmp < 0) return 1 + size / 2;
                else return size / 2;
            }
            else
            {
                if (cmp > 0) return 1 + size / 2;
                else return size / 2;
            }
        }
        public Comparable findMedian() {
            return this.arr[getMediaIndex()];
        }

        public void removeMedian() {
            int med = getMediaIndex();
            swap(med, this.size - 1);
            this.arr[this.size - 1] = null;
            sink(med);
        }
    }
    public static void main(String[] args) {

    }
}

// Hint: maintain two binary heaps, one that is max-oriented and one that is min-oriented.
