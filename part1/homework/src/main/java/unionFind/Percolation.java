import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[] isOpen;
    private int num;
    private int virtualTopNode;
    private int virtualBottomNode;
    private int openSiteNum;
    private boolean isPercolates;
    private boolean isUnionAllVirtualMode;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        isOpen = new boolean[n * n];
        uf = new WeightedQuickUnionUF(n * n + 2);

        num = n;
        virtualTopNode = 0;
        virtualBottomNode = n * n + 1;
        openSiteNum = 0;
        isPercolates = false;

        for (int i = 0; i < n * n; i++) {
            isOpen[i] = false;
        }
        isUnionAllVirtualMode = false;
        unionTopAndBottomVirtualNode(isUnionAllVirtualMode);
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
        }
        // union down
        if (isInBoundsAndOpen(row + 1, col)) {
            uf.union(index, indexDown);
        }
        // union left
        if (isInBoundsAndOpen(row, col - 1)) {
            uf.union(index, indexLeft);
        }
        // union right
        if (isInBoundsAndOpen(row, col + 1)) {
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

    private void unionVirtualTopNode(int row, int col) {
        int index = getIndex(row, col);
        if (1 == row) {
            uf.union(index, virtualTopNode);
        }
    }

    private void unionVirtualBottomNode() {
        for (int i = 1; i <= num; i++) {
            int index = getIndex(num, i);
            if (isOpen(num, i)) {
                uf.union(index, virtualBottomNode);
            }
        }
    }

    /**
     * init virtual node at beginning may be the best method
     * but if percolation all open bottom node is full
     *
     */
    private void unionTopAndBottomVirtualNode(boolean isAll) {
        for (int i = 1; i <= num; i++) {
            uf.union(getIndex(1, i), virtualTopNode);
            if (isAll) {
                uf.union(getIndex(num, i), virtualBottomNode);
            }
        }
    }

    /**
     * checkPercolaates on call percolates func
     * travel all bottom open site
     *
     * @return
     */
    private boolean checkIsPercolates() {
        for (int i = 1; i <= num; i++) {
            int index = getIndex(num, i);
            if (isOpen(num, i)) {
                if (uf.connected(index, virtualTopNode)) {
                    return true;
                }
            }
        }
        return false;
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

    // /**
    //  * check is Percolates on Open new site
    //  * using find_max() fun but not implement this
    //  * @param row
    //  * @param col
    //  */
    // private void updateIsPercolatesAfterOpen(int row, int col) {
    //     int index = getIndex(row, col);
    //     int max = uf.find_max(index);
    //     if (max <= num * (num - 1)) return;
    //     isPercolates = isFull(getRowFromIndex(max), getColFromIndex(max));
    // }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        openSiteNum++;
        // union around
        unionAround(row, col);
        // union virtual node
        // unionVirtualNode(row, col);
        // unionVirtualTopNode(row, col);
        isOpen[getIndex(row, col) - 1] = true;
        // updateIsPercolatesAfterOpen(row, col);
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
        if (isUnionAllVirtualMode) {
            return uf.connected(virtualTopNode, virtualBottomNode);
        }
        else {
            return checkIsPercolates();
        }
        // return isPercolates;
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
