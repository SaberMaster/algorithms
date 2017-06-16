import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;


/**
 * @file   KdTree.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun 15 23:33:34 2017
 *
 * @brief
 *
 *
 */

public class KdTree {
    private Node kd;
    private int count;
    private static class Node {
        private Point2D p;
        private Node lb;
        private Node rt;
    }
    // construct an empty set of points
    public KdTree() {
        this.kd = new Node();
        this.count = 0;
    }
    // is the set empty?
    public boolean isEmpty() {
        return this.count <= 0;
    }
    // number of points in the set
    public int size() {
        return this.count;
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (null == p) throw new java.lang.NullPointerException();

        Node currentNode = this.kd;
        int level = 0;
        if (null == currentNode.p) {
            currentNode.p = p;
            this.count++;
        }
        while (true) {
            if (p.equals(currentNode.p)) return;
            if (comparePoints(p, currentNode.p, (level++ % 2 != 0))) {
                if (null == currentNode.rt) {
                    insertNewNode(p, currentNode, true);
                    return;
                }
                else {
                    currentNode = currentNode.rt;
                }
            }
            else {
                if (null == currentNode.lb) {
                    insertNewNode(p, currentNode, false);
                    return;
                }
                else {
                    currentNode = currentNode.lb;
                }
            }
        }
    }

    private void insertNewNode(Point2D p, Node n, boolean isRT) {
        Node tmp = new Node();
        tmp.p = p;
        if (isRT) n.rt = tmp;
        else n.lb = tmp;
        this.count++;
    }
    private boolean comparePoints(Point2D p1, Point2D p2, boolean isHorizon) {
        if (isHorizon) return p1.y() >= p2.y();
        else return p1.x() >= p2.x();
    }
    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (null == p) throw new java.lang.NullPointerException();

        Node currentNode = this.kd;
        int level = 0;
        if (null == currentNode.p) return false;
        while (true) {
            if (p.equals(currentNode.p)) return true;
            if (comparePoints(p, currentNode.p, (level++ % 2 != 0))) {
                if (null == currentNode.rt) {
                    return false;
                }
                else {
                    currentNode = currentNode.rt;
                }
            }
            else {
                if (null == currentNode.lb) {
                    return false;
                }
                else {
                    currentNode = currentNode.lb;
                }
            }
        }
    }
    // draw all points to standard draw
    public void draw() {
        drawPointAndLine(new RectHV(0.0, 0.0, 1.0, 1.0), this.kd, 0);
    }

    private void drawPointAndLine(RectHV rect, Node n, int level) {
        if (null == n) return;
        boolean isHorizon = (level++ % 2 != 0);
        // draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        n.p.draw();

        RectHV rectLB, rectRT;
        if (isHorizon) {
            rectLB = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.p.y());
            rectRT = new RectHV(rect.xmin(), n.p.y(), rect.xmax(), rect.ymax());
            drawLine(rect.xmin(), n.p.y(), rect.xmax(), n.p.y(), !isHorizon);
        }
        else {
            rectLB = new RectHV(rect.xmin(), rect.ymin(), n.p.x(), rect.ymax());
            rectRT = new RectHV(n.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            drawLine(n.p.x(), rect.ymin(), n.p.x(), rect.ymax(), !isHorizon);
        }

        drawPointAndLine(rectLB, n.lb, level);
        drawPointAndLine(rectRT, n.rt, level);
    }

    private void drawLine(double x0, double y0, double x1, double y1, boolean isRed) {
        if (isRed) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(x0, y0, x1, y1);
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new java.lang.NullPointerException();
        Queue<Point2D> pq = new Queue<Point2D>();
        if (null == this.kd.p) return pq;
        RectHV tmpRect = new RectHV(0.0, 0.0, 1.0, 1.0);
        int level = 0;
        checkPointInRect(tmpRect, rect, pq, this.kd, level);
        return pq;
    }

    private void checkPointInRect(RectHV src, RectHV dst, Queue<Point2D> pq, Node n, int level) {
        if (null == n) return;
        if (dst.contains(n.p)) pq.enqueue(n.p);
        boolean isHorizon = (level++ % 2 != 0);
        RectHV rectLB, rectRT;
        if (isHorizon) {
            rectLB = new RectHV(src.xmin(), src.ymin(), src.xmax(), n.p.y());
            rectRT = new RectHV(src.xmin(), n.p.y(), src.xmax(), src.ymax());
        }
        else {
            rectLB = new RectHV(src.xmin(), src.ymin(), n.p.x(), src.ymax());
            rectRT = new RectHV(n.p.x(), src.ymin(), src.xmax(), src.ymax());
        }

        if (rectLB.intersects(dst)) checkPointInRect(rectLB, dst, pq, n.lb, level);
        if (rectRT.intersects(dst)) checkPointInRect(rectRT, dst, pq, n.rt, level);
    }
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (null == p) throw new java.lang.NullPointerException();
        // as x,y between 0-1.0 so max distance < 2.1
        if (null == this.kd.p) return null;
        Point2D nearestPoint = this.kd.p;
        RectHV tmpRect = new RectHV(0.0, 0.0, 1.0, 1.0);
        int level = 0;
        return checkNearestPoint(tmpRect, nearestPoint, p, this.kd, level);
    }

    private Point2D checkNearestPoint(RectHV src, Point2D nearestPoint, Point2D dstP, Node n, int level) {
        if (null == n) return nearestPoint;
        boolean isHorizon = (level++ % 2 != 0);
        RectHV rectLB, rectRT;
        double nearestDistance = dstP.distanceSquaredTo(nearestPoint);
        if (dstP.distanceSquaredTo(n.p) < nearestDistance) nearestPoint = n.p;

        if (isHorizon) {
            double y = n.p.y();
            rectLB = new RectHV(src.xmin(), src.ymin(), src.xmax(), y);
            rectRT = new RectHV(src.xmin(), y, src.xmax(), src.ymax());
        }
        else {
            double x = n.p.x();
            rectLB = new RectHV(src.xmin(), src.ymin(), x, src.ymax());
            rectRT = new RectHV(x, src.ymin(), src.xmax(), src.ymax());
        }
        double distanceLB = rectLB.distanceSquaredTo(dstP);
        double distanceRT = rectRT.distanceSquaredTo(dstP);

        if (nearestDistance > distanceLB
            && nearestDistance > distanceRT) {
            if (distanceLB < distanceRT) {
                nearestPoint = checkNearestPoint(rectLB, nearestPoint, dstP, n.lb, level);
                nearestPoint = checkNearestPoint(rectRT, nearestPoint, dstP, n.rt, level);
            }
            else {
                nearestPoint = checkNearestPoint(rectRT, nearestPoint, dstP, n.rt, level);
                nearestPoint = checkNearestPoint(rectLB, nearestPoint, dstP, n.lb, level);
            }
        }
        else {
            if (nearestDistance > distanceLB)
                nearestPoint = checkNearestPoint(rectLB, nearestPoint, dstP, n.lb, level);
            if (nearestDistance > distanceRT)
                nearestPoint = checkNearestPoint(rectRT, nearestPoint, dstP, n.rt, level);
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
