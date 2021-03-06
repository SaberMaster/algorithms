import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithBtmVirtualNode;
    // private QuickFindUF uf;
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
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufWithBtmVirtualNode = new WeightedQuickUnionUF(n * n + 2);
        // uf = new QuickFindUF(n * n + 2);

        num = n;
        virtualTopNode = 0;
        virtualBottomNode = n * n + 1;
        openSiteNum = 0;
        // isPercolates = false;

        for (int i = 0; i < n * n; i++) {
            isOpen[i] = false;
        }
        unionTopVirtualNode();
    }


    private int getIndex(int row, int col) {
        return (row - 1) * num + col;
    }

    private boolean isOutBounds(int row, int col) {
        return row <= 0
            || row > num
            || col <= 0
            || col > num;
    }

    private boolean isInBoundsAndOpen(int row, int col) {
        return !isOutBounds(row, col)
            && isOpen(row, col);
    }


    private void unionAround(int row, int col) {
        int index = getIndex(row, col);
        int indexUp = index - num;
        int indexDown = index + num;
        int indexLeft = index - 1;
        int indexRight = index + 1;

        // union up
        if (isInBoundsAndOpen(row - 1, col)) {
            uf.union(index, indexUp);
            ufWithBtmVirtualNode.union(index, indexUp);
        }
        // union down
        if (isInBoundsAndOpen(row + 1, col)) {
            uf.union(index, indexDown);
            ufWithBtmVirtualNode.union(index, indexDown);
        }
        // union left
        if (isInBoundsAndOpen(row, col - 1)) {
            uf.union(index, indexLeft);
            ufWithBtmVirtualNode.union(index, indexLeft);
        }
        // union right
        if (isInBoundsAndOpen(row, col + 1)) {
            uf.union(index, indexRight);
            ufWithBtmVirtualNode.union(index, indexRight);
        }
    }

    /**
     * init virtual node at beginning may be the best method
     * but if percolation all open bottom node is full
     *
     */
    private void unionTopVirtualNode() {
        for (int i = 1; i <= num; i++) {
            uf.union(getIndex(1, i), virtualTopNode);
            ufWithBtmVirtualNode.union(getIndex(1, i), virtualTopNode);
        }
    }

    private void unionBtmVirtualNode(int row, int col) {
        int index = getIndex(row, col);
        if (num == row) {
            ufWithBtmVirtualNode.union(index, virtualBottomNode);
        }
    }

    private int getRowFromIndex(int index) {
        if (0 == index % num) {
            return index / num;
        }
        else {
            return index / num + 1;
        }
    }

    private int getColFromIndex(int index) {
        if (0 == index % num) {
            return num;
        }
        else {
            return index % num;
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        openSiteNum++;
        // union around
        unionAround(row, col);
        // union virtual node
        unionBtmVirtualNode(row, col);
        isOpen[getIndex(row, col) - 1] = true;
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isOutBounds(row, col)) {
            // System.out.printf("row = %d, col = %d, index = %d\n", row, col, getIndex(row, col));
            throw new java.lang.IndexOutOfBoundsException();
        }
        return isOpen[getIndex(row, col) - 1];
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
        return ufWithBtmVirtualNode.connected(virtualTopNode, virtualBottomNode);
    }
    // test client (optional)
    public static void main(String[] args) {
        int size = 20;
        int sizeSquare = size * size;
        // init array
        int[] ids = new int[sizeSquare];
        Percolation p = new Percolation(size);
        for (int i = 0; i < sizeSquare; i++) {
            ids[i] = i + 1;
        }
        StdRandom.shuffle(ids);
        for (int i = 0; i < sizeSquare; i++) {
            if (p.percolates()) break;
            p.open(p.getRowFromIndex(ids[i]), p.getColFromIndex(ids[i]));
        }
        System.out.printf("size = %d, sites = %d, opens = %d, threshold = %.2f%n",
                          size,
                          sizeSquare,
                          p.numberOfOpenSites(),
                          (float) p.numberOfOpenSites() / sizeSquare);
    }
}
