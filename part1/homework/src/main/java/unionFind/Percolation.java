import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[] isOpen;
    private int num;
    private int virtualTopNode;
    private int virtualBottomNode;
    private int openSiteNum;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        isOpen = new boolean[n * n];
        num = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        virtualTopNode = n * n;
        virtualBottomNode = n * n + 1;
        openSiteNum = 0;
        for (int i = 0; i < n * n; i++) {
            isOpen[i] = false;
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * num + (col - 1);
    }

    private boolean isOutBounds(int row, int col) {
        return row <= 0
            || row > num
            || col <= 0
            || col > num;
    }

    private boolean isOutBoundsAndOpen(int row, int col) {
        return isOutBounds(row, col)
            && isOpen(row, col);
    }


    private void unionAround(int row, int col) {
        int index = getIndex(row, col);
        int indexUp = index - num;
        int indexDown = index + num;
        int indexLeft = index - 1;
        int indexRight = index + 1;

        // union up
        if (isOutBoundsAndOpen(row - 1, col)) {
            uf.union(index, indexUp);
        }
        // union down
        if (isOutBoundsAndOpen(row + 1, col)) {
            uf.union(index, indexDown);
        }
        // union left
        if (isOutBoundsAndOpen(row, col - 1)) {
            uf.union(index, indexLeft);
        }
        // union right
        if (isOutBoundsAndOpen(row, col + 1)) {
            uf.union(index, indexRight);
        }
    }

    private void unionVirtualNode(int row, int col) {
        int index = getIndex(row, col);
        if (1 == row) {
            uf.union(index, virtualTopNode);
        }
        else if (num == row) {
            uf.union(index, virtualBottomNode);
        }
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        openSiteNum++;
        // union around
        unionAround(row, col);
        // union virtual node
        unionVirtualNode(row, col);
        isOpen[getIndex(row, col)] = true;
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isOutBounds(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return isOpen[getIndex(row, col)];
    }
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return isOpen(row, col)
            && uf.connected(getIndex(row, col), virtualTopNode);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSiteNum;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTopNode, virtualBottomNode);
    }
    // test client (optional)
    public static void main(String[] args) {
        long seed = 10;
        StdRandom.setSeed(seed);
        for (int i = 0; i < 10; i++) {
            int x = StdRandom.uniform(10);
            System.out.println(x);
        }
    }
}
