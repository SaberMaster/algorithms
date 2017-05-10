/**
 * @file   SuccessorWithDelete.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 10 14:59:38 2017
 *
 * @brief
 * Successor with delete. Given a set of N integers S={0,1,...,n−1} and a sequence of requests of the following form:

 Remove x from S
 Find the successor of x: the smallest y in S such that y≥x.
 design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
 *
 */

package unionFind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;




class SuccessorWithDelete {
    private int[] id;
    private int[] weight;
    private int[] max;
    private boolean[] isRemove;
    private int num;

    public SuccessorWithDelete(int N) {
        initData(N);
    }

    private void initData(int N) {
        id = new int[N];
        weight = new int[N];
        max = new int[N];
        isRemove = new boolean[N];
        num = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            max[i] = i;
            weight[i] = 1;
            isRemove[i] = false;
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

    public int find(int x) {
        return max[root(x)];
    }

    public boolean union(int x, int y) {
        int root_x = root(x);
        int root_y = root(y);
        if (root_x == root_y) return false;
        if (weight[root_x] < weight[root_y]) {
            id[root_x] = root_y;
            weight[root_y] += weight[root_x];
            max[root_y] = max[root_y] > max[root_x] ? max[root_y] : max[root_x];
        } else {
            id[root_y] = root_x;
            weight[root_x] += weight[root_y];
            max[root_x] = max[root_y] > max[root_x] ? max[root_y] : max[root_x];
        }
        return true;
    }

    public int remove(int x) {
        if (!isRemove[x]) {
            isRemove[x] = true;
                if (x >= 1 &&
                isRemove[x - 1]) {
                union(x, x - 1);
            }
            if (x < num - 1 &&
                isRemove[x + 1]) {
                union(x, x + 1);
            }
        }
        return num - 1 == x ? x : find(x) + 1;
    }

    public static void main(String[] args) {
        int peopleCount = 10;
        SuccessorWithDelete uf = new SuccessorWithDelete(peopleCount);

        try {
            File file = new File("SuccessorWithDeleteInput.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while(null != (s = br.readLine())) {
                int result = uf.remove(Integer.parseInt(s));
                System.out.println(result);
            }
        }
        catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

    }
}
