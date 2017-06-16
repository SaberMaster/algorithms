import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @file   PointSET.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun 15 23:08:41 2017
 *
 * @brief
 *
 *
 */

public class PointSET {
    private SET<Point2D> bt;
    // construct an empty set of points
    public PointSET() {
        this.bt = new SET<Point2D>();
    }
    // is the set empty?
    public boolean isEmpty() {
        return this.bt.isEmpty();
    }
    // number of points in the set
    public int size() {
        return this.bt.size();
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (null == p) throw new java.lang.NullPointerException();
        this.bt.add(p);
    }
    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (null == p) throw new java.lang.NullPointerException();
        return this.bt.contains(p);
    }
    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D p : this.bt) {
            p.draw();
        }
    }
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new java.lang.NullPointerException();
        Queue<Point2D> pq = new Queue<Point2D>();
        for (Point2D p : this.bt) {
            if (!rect.contains(p)) continue;
            pq.enqueue(p);
        }
        return pq;
    }
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (null == p) throw new java.lang.NullPointerException();
        // as x,y between 0-1.0 so max distance < 2.1
        double nearest = 2.1;
        Point2D nearestPoint = null;
        double distance;
        for (Point2D newP : this.bt) {
            distance = p.distanceSquaredTo(newP);
            if (nearest > distance) {
                nearest = distance;
                nearestPoint = newP;
            }
        }
        return nearestPoint;
    }
    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
