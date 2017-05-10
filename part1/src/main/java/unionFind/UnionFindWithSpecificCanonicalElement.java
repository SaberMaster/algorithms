/**
 * @file   UnionFindWithSpecificCanonicalElement.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 10 14:22:13 2017
 *
 * @brief
 * Union-find with specific canonical element. Add a method 𝚏𝚒𝚗𝚍() to the union-find data type so that 𝚏𝚒𝚗𝚍(𝚒) returns the largest element in the connected component containing i. The operations, 𝚞𝚗𝚒𝚘𝚗(), 𝚌𝚘𝚗𝚗𝚎𝚌𝚝𝚎𝚍(), and 𝚏𝚒𝚗𝚍() should all take logarithmic time or better.

 For example, if one of the connected components is {1,2,6,9}, then the 𝚏𝚒𝚗𝚍() method should return 9 for each of the four elements in the connected components.
 *
 */

package unionFind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


class UnionFindWithSpecificCanonicalElement {
    private int[] id;
    private int[] weight;
    private int[] max;

    public UnionFindWithSpecificCanonicalElement(int N) {
        initData(N);
    }

    private void initData(int N) {
        id = new int[N];
        weight = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            max[i] = i;
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

    public static void main(String[] args) {
        int peopleCount = 4;
        UnionFindWithSpecificCanonicalElement uf = new UnionFindWithSpecificCanonicalElement(peopleCount);

        try {
            File file = new File("UnionFindWithSpecificCanonicalElementInput.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while(null != (s = br.readLine())) {
                String[] lineSplit = s.split(" ");
                uf.union(Integer.parseInt(lineSplit[0]),
                         Integer.parseInt(lineSplit[1]));
                System.out.println(uf.find(2));
            }
        }
        catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

    }
}
