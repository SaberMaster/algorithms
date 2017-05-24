/**
 * @file   EggDrop.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Wed May 17 14:21:52 2017
 *
 * @brief  Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs.
 An egg breaks if it is dropped from floor T or higher and does not break otherwise.
 Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:

 Version 0: 1 egg, ≤T tosses.
 Version 1: ∼1lgn eggs and ∼1lgn tosses.
 Version 2: ∼lgT eggs and ∼2lgT tosses.
 Version 3: 2 eggs and ∼2n‾‾√ tosses.
 Version 4: 2 eggs and ≤cT‾‾√ tosses for some fixed constant c.
 *
 *
 */


public class EggDrop {
    private static boolean test(int i, int t) {
        return i < t;
    }

    public static int drop0(int n, int t) {
        int i = 1;
        while (i < n) {
            if (!test(i, t)) break;
        }
        return i;
    }

    public static int drop1(int n, int t) {
        int high = n;
        int low = 1;
        int mid = low;
        while (high >= low) {
            mid = (high + low) >>> 1;
            if (!test(mid, t)) {
                if (high == low) break;
                high = mid;
            }
            else if (test(mid, t)) {
                low = mid + 1;
            }
        }
        return mid;
    }

    public static int drop2(int n, int t) {
        int i = 1;
        while (i < n) {
            if (!test(i, t)) break;
            i = i << 1;
        }
        return drop1(Math.min(i, n) - i >>> 1, t - i >>> 1)
            + i >>> 1;
    }

    public static int drop3(int n, int t) {
        int tosses = (int) Math.floor(Math.sqrt((double) n));
        int times = 0;
        int sum = tosses;
        for (times = 0; times < tosses; times++) {
            sum += tosses - times;
            if (!test(sum, t)) break;
        }
        for (int i = (sum - (tosses - times)); i < sum; i++) {
            if (!test(i, t)) return i;
        }
        return 0;
    }

    public static int drop4(int n, int t) {
        int tosses = 1;
        while (true) {
            if (tosses * (tosses + 1) >= 2 * n) break;
            tosses++;
        }
        int times = 0;
        int sum = tosses;
        for (times = 0; times < tosses; times++) {
            sum += tosses - times;
            if (!test(sum, t)) break;
        }
        for (int i = (sum - (tosses - times)); i < sum; i++) {
            if (!test(i, t)) return i;
        }
        return 0;
    }


    public static void main(String[] args) {

    }
}
