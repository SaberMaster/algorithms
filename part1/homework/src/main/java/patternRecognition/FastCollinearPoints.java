import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @file   FastCollinearPoints.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  2 13:09:16 2017
 *
 * @brief
 *
 *
 */

public class FastCollinearPoints {
    private LineSegment[] lines;
    private int count;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (null == points
            || 0 == points.length) throw new java.lang.NullPointerException();

        this.lines = new LineSegment[0];
        this.count = 0;
        // if (points.length < 4) return;
        Point[] pointsNew = checkDup(points);
        Point[] pointsBak = pointsNew.clone();
        for (int i = 0; i < pointsNew.length; i++) {
            getAllSlopesBaseOnThePoint(pointsNew[i], pointsBak);
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

    private void getAllSlopesBaseOnThePoint(Point p, Point[] points) {
        Comparator<Point> slopeOrder = p.slopeOrder();
        Arrays.sort(points, slopeOrder);
        int beginIndex = 1;
        for (int i = 2; i < points.length; i++) {
            if (0 != slopeOrder.compare(points[beginIndex], points[i])) {
                if (i - beginIndex >= 3) {
                    getCollinearPoints(p, points, beginIndex, i - 1);
                }
                beginIndex = i;
            }
            // else {
            //     if (i == points.length - 1
            //         && i - beginIndex >= 2) {
            //     }
            // }
        }
        // finish
        if (points.length - 1 - beginIndex >= 2) {
            getCollinearPoints(p, points, beginIndex, points.length - 1);
        }
    }

    private void getCollinearPoints(Point p, Point[] points, int from, int to) {
        Arrays.sort(points, from, to + 1);
        // for (int j = from; j <= to; j++) {
        //     StdOut.println(points[j]);
        // }

        if (p.compareTo(points[from]) < 0) {
            addToLineList(p, points[to]);
        }
    }

    private void addToLineList(Point begin, Point end) {
        this.count++;
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

    // the number of line segments
    public int numberOfSegments() {
        return this.count;
    }
    // the line segments
    public LineSegment[] segments() {
        return this.lines.clone();
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
