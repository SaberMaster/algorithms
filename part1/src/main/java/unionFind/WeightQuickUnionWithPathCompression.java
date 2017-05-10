/**
 * @file   WeightQuickUnionWithPathCompression.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Tue May  9 20:07:41 2017
 *
 * @brief
 *
 *
 */
package unionFind;

class WeightQuickUnionWithPathCompression {
    private int[] id;
    private int[] weight;

    public WeightQuickUnionWithPathCompression(int N) {
        initData(N);
    }

    private void initData(int N) {
        id = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; i++) {
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
        int root_x = root(x);
        int root_y = root(y);
        if (root_x == root_y) return false;
        if (weight[root_x] < weight[root_y]) {
            id[root_x] = root_y;
            weight[root_y] += weight[root_x];
        } else {
            id[root_y] = root_x;
            weight[root_x] += weight[root_y];
        }
        return true;
    }
}
