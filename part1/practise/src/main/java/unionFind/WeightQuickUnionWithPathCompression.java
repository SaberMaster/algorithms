/**
 * @file   WeightQuickUnionWithPathCompression.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Tue May  9 20:07:41 2017
 *
 * @brief
 *
 *
 */
package unionfind;

class WeightQuickUnionWithPathCompression {
    private int[] id;
    private int[] weight;

    public WeightQuickUnionWithPathCompression(int n) {
        initData(n);
    }

    private void initData(int n) {
        id = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            weight[i] = 1;
        }
    }

    private int root(int x) {
        while (x != id[x])  {
            // path compression
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    public boolean isConnected(int x, int y) {
        return root(x) == root(y);
    }

    public boolean union(int x, int y) {
        int rootX = root(x);
        int rootY = root(y);
        if (rootX == rootY) return false;
        if (weight[rootX] < weight[rootY]) {
            id[rootX] = rootY;
            weight[rootY] += weight[rootX];
        }
        else {
            id[rootY] = rootX;
            weight[rootX] += weight[rootY];
        }
        return true;
    }
}
