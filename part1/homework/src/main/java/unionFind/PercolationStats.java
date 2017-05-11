import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


public class PercolationStats {
    private double[] results;
    private int[] ids;
    private double myMean;
    private double myStd;
    private double myLo;
    private double myHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        initAll(n, trials);
        // init numbers
        executeTrials(n, trials);
    }

    private void initAll(int size, int trials) {
        int sizeSquare = size * size;
        results = new double[trials];
        ids = new int[sizeSquare];
        for (int i = 0; i < sizeSquare; i++) {
            ids[i] = i + 1;
        }
    }

    private double executeOneTrail(int size) {
        int sizeSquare = size * size;
        // init array
        Percolation p = new Percolation(size);
        StdRandom.shuffle(ids);
        for (int i = 0; i < sizeSquare; i++) {
            if (p.percolates()) break;
            p.open(getRowFromIndex(ids[i], size), getColFromIndex(ids[i], size));
        }
        // System.out.printf("size = %d, sites = %d, opens = %d, threshold = %.2f%n",
        //                   size,
        //                   sizeSquare,
        //                   p.numberOfOpenSites(),
        //                   (double) p.numberOfOpenSites() / sizeSquare);
        return (double) p.numberOfOpenSites() / sizeSquare;
    }


    private void executeTrials(int size, int trials) {
        for (int i = 0; i < trials; i++) {
            results[i] = executeOneTrail(size);
        }
        calc(trials);
    }


    private int getRowFromIndex(int index, int num) {
        if (0 == index % num) {
            return index / num;
        }
        else {
            return index / num + 1;
        }
    }

    private int getColFromIndex(int index, int num) {
        if (0 == index % num) {
            return num;
        }
        else {
            return index % num;
        }
    }

    private void calc(int trials) {
        myMean = StdStats.mean(results);
        myStd = StdStats.stddev(results);
        double delta = (1.96 * myStd / Math.sqrt(trials));
        myLo = myMean - delta;
        myHi = myMean + delta;
    }
    // sample mean of percolation threshold
    public double mean() {
        return myMean;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return myStd;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return myLo;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return myHi;
    }
    // test client (described below)
    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        double delta = timer.elapsedTime();
        System.out.printf("mean = %f%nstddev = %f%n95%% confidence interval = [%f, %f]%ntime =  %f",
                          ps.mean(),
                          ps.stddev(),
                          ps.confidenceLo(),
                          ps.confidenceHi(),
                          delta);
    }
}
