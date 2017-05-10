/**
 * @file   SocialNetworkConnectivity.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 10 13:17:20 2017
 *
 * @brief
 * Social network connectivity. Given a social network containing n members and a log file containing m timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or better and use extra space proportional to n
 *
 */

package unionFind;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


class SocialNetworkConnectivity {
    private WeightQuickUnionWithPathCompression uf;
    private int numMembers;

    public SocialNetworkConnectivity(int N) {
        uf = new WeightQuickUnionWithPathCompression(N);
        numMembers = N;
    }

    public void union(int x, int y) {
        if(uf.union(x, y)) numMembers--;
    }

    public boolean isAllConnected() {
        return 1 == numMembers;
    }


    public static void main(String[] args) {
        int peopleCount = 4;
        SocialNetworkConnectivity snc = new SocialNetworkConnectivity(peopleCount);
        try {
            File file = new File("SocialNetworkConnectivityInput.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while(null != (s = br.readLine())) {
                String[] lineSplit = s.split(" ");
                snc.union(Integer.parseInt(lineSplit[0]),
                          Integer.parseInt(lineSplit[1]));
                if (snc.isAllConnected()) {
                    System.out.println(lineSplit[2]);
                    break;
                }
            }
            if (!snc.isAllConnected()) {
                System.out.println("Sorry");
            }
        }
        catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

    }
}
