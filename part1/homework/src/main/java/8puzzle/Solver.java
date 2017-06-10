/**
 * @file   Solver.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  9 10:37:24 2017
 *
 * @brief
 *
 *
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;


public class Solver {

    private class BoardStruct implements Comparable<BoardStruct> {
        private final Board board;
        private int moves;
        private BoardStruct previous;
        public BoardStruct(int moves, Board board, BoardStruct previous) {
            this.moves = moves;
            this.board = board;
            this.previous = previous;
        }

        private int getPriority() {
            return this.moves + this.board.manhattan();
        }

        public int compareTo(BoardStruct that) {
            if (this.getPriority() == that.getPriority()) return 0;
            else if (this.getPriority() > that.getPriority()) return 1;
            else return -1;
        }
    }


    private int moves;
    private boolean isSolve;
    private Stack<Board> boardList;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (null == initial) throw new java.lang.NullPointerException();
        this.moves = 0;
        this.isSolve = false;
        this.boardList = new Stack<Board>();
        // first circle
        BoardStruct bs = aStar(initial);
        if (null != bs) {
            this.isSolve = true;
            this.moves = bs.moves;
            while (null != bs) {
                this.boardList.push(bs.board);
                bs = bs.previous;
            }
        }
    }

    private BoardStruct aStar(Board init) {
        MinPQ<BoardStruct> pq = new MinPQ<BoardStruct>();
        BoardStruct bs = new BoardStruct(0, init, null);
        pq.insert(bs);

        Board another = init.twin();
        MinPQ<BoardStruct> pqAnother = new MinPQ<BoardStruct>();
        BoardStruct bsAnother = new BoardStruct(0, another, null);
        pqAnother.insert(bsAnother);


        if (init.isGoal()) return bs;
        if (another.isGoal()) return null;
        while (true) {
            bs = circle(pq);
            if (bs.board.isGoal()) return bs;
            bsAnother = circle(pqAnother);
            if (bsAnother.board.isGoal()) return null;
        }
    }

    private BoardStruct circle(MinPQ<BoardStruct> pq) {
        BoardStruct bs = pq.delMin();
        for (Board b : bs.board.neighbors()) {
            // cmp pre
            if (null != bs.previous
                && b.equals(bs.previous.board)) {
                continue;
            }
            BoardStruct bsNew = new BoardStruct(bs.moves + 1, b, bs);
            pq.insert(bsNew);
        }
        return bs;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.isSolve;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) return this.moves;
        else return -1;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) return this.boardList;
        else return null;
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
