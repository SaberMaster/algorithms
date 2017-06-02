import java.util.Comparator;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
/**
 * @file   BruteCollinearPoints.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  2 10:59:20 2017
 *
 * @brief
 *
 *
 */

public class BruteCollinearPoints {
    private LineSegment[] lines;
    private int count;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (null == points
            || 0 == points.length) throw new java.lang.NullPointerException();
        lines = new LineSegment[0];
        count = 0;
        Point[] pointsNew = checkDup(points);
        Point[] point4 = new Point[4];
        for (int i = 0; i < pointsNew.length - 3; i++) {
            for (int j = i + 1; j < pointsNew.length - 2; j++) {
                for (int k = j + 1; k < pointsNew.length - 1; k++) {
                    for (int m = k + 1; m < pointsNew.length; m++) {
                        point4[0] = pointsNew[i];
                        point4[1] = pointsNew[j];
                        point4[2] = pointsNew[k];
                        point4[3] = pointsNew[m];
                        if (!compareFour(point4)) continue;
                        addToLineList(point4[0], point4[3]);
                    }
                }
            }
        }
        resizeLineList(count);
    }

    private Point[] checkDup(Point[] pointsOld) {
        Point[] points = pointsOld.clone();
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (0 == points[i].compareTo(points[i + 1])) throw new java.lang.IllegalArgumentException();
        }
        return points;
    }

    private boolean compareFour(Point[] list) {
        Comparator<Point> cmp = list[0].slopeOrder();
        for (int i = 1; i < list.length; i++) {
            if (0 == list[0].compareTo(list[i])) throw new java.lang.IllegalArgumentException();
            if (i >= 2) {
                if (0 != cmp.compare(list[i - 1], list[i])) return false;
            }
        }
        return true;
    }
    // the number of line segments
    public int numberOfSegments() {
        return this.count;
    }
    // the line segments
    public LineSegment[] segments() {
        return this.lines.clone();
    }

    private void addToLineList(Point begin, Point end) {
        count++;
        if (this.count > this.lines.length) {
            int newLength = this.lines.length * 2;
            if (0 == this.lines.length) {
                newLength = 2;
            }
            resizeLineList(newLength);
        }
        this.lines[count - 1] = new LineSegment(begin, end);
    }

    private void resizeLineList(int length) {
        LineSegment[] newLines = new LineSegment[length];
        for (int i = 0; i < this.lines.length && i < length; i++) {
            newLines[i] = this.lines[i];
        }
        this.lines = newLines;
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
