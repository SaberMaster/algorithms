/**
 * @file   NutsAndBolts.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun  1 23:34:00 2017
 *
 * @brief  A disorganized carpenter has a mixed pile of n nuts and n bolts.
 The goal is to find the corresponding pairs of nuts and bolts.
 Each nut fits exactly one bolt and each bolt fits exactly one nut.
 By fitting a nut and a bolt together, the carpenter can see which one is bigger (but the carpenter cannot compare two nuts or two bolts directly).
 Design an algorithm for the problem that uses nlogn compares (probabilistically).
 *
 *
 */

public class NutsAndBolts {

// Hint: modify the quicksort partitioning part of quicksort.

// Remark: This research paper gives an algorithm that runs in nlog4n time in the worst case.
    public void sort (int[] nuts, int[] bolts, int lo, int hi) {
        if (lo >= hi) return;
        int mid = Partation(nuts, bolts, lo, hi);

        sort(nuts, bolts, lo, mid);
        sort(nuts, bolts, mid + 1, hi);
    }


    public int Partation(int[] nuts, int bolts, int lo, int hi) {
        if (lo >= hi) return;

        // get random index
        int i = (lo + hi) / 2;
        int m = lo - 1;
        int n = hi + 1;
        while (true) {
            while(nuts[++m] < bolts[i])
                if (m == high) break;

            while(nuts[--n] > bolts[i])
                if (n == lo) break;

            if (m >= n) break;
            exch(nuts, m, n);
        }

        int k = m;
        int i = lo;
        int m = lo;
        int n = hi;
        while (i <= n) {
            int compare = bolts[i].CompareTo(nuts[m]);
            if (compare < 0) exch(bolts, m++, i++);
            else if (compare > 0) exch(bolts, n--, i);
            else i++;
        }

        return k;
    }

    public static void main(String[] args) {
        // shuffle the two list first
        // random choose a buts to compare all the bolts
        // divided into two part and get the fit bolts
        // then use the bolts to divided the nuts
        // and use the step on all small part
    }
}
