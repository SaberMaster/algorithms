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
        private Board board;
        private int moves;
        private int manha;
        private Board previous;
        public BoardStruct(int moves, Board board, Board previous) {
            this.moves = moves;
            this.board = board;
            this.manha = this.board.manhattan();
            this.previous = previous;
        }

        public Board getPrevious() {
            return this.previous;
        }

        public void setPrevious(Board pre) {
            this.previous = pre;
        }

        public int getPriority() {
            return this.moves + this.manha;
        }

        public Board getBoard() {
            return this.board;
        }

        public void setBoard(Board curr) {
            this.board = curr;
            this.manha = this.board.manhattan();
        }

        public int getMoves() {
            return this.moves;
        }

        public void setMoves(int moves) {
            this.moves = moves;
        }

        public int compareTo(BoardStruct that) {
            if (this.getPriority() == that.getPriority()) {
                if (this.manha == that.manha) {
                    return 0;
                }
                else if (this.manha > that.manha) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
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
        aStar(initial, this.boardList);
        if (this.isSolve) {
            Stack<Board> tmpSB = new Stack<Board>();
            for (Board b : this.boardList) {
                tmpSB.push(b);
            }
            this.boardList = tmpSB;
        }
    }

    private void aStar(Board init, Stack<Board> boardListTmp) {
        MinPQ<BoardStruct> pq = new MinPQ<BoardStruct>();
        boardListTmp.push(init);


        // SimpleStruct oneSS = new SimpleStruct();
        // oneSS.current = init;
        BoardStruct bs = new BoardStruct(0, init, null);


        // Board another = init.twin();
        // Stack<Board> boardListAnother = new Stack<Board>();
        // MinPQ<BoardStruct> pqAnother = new MinPQ<BoardStruct>();
        // boardListAnother.push(another);

        // SimpleStruct anotherSS = new SimpleStruct();
        // anotherSS.current = another;

        boolean notFinishA = true, notFinishB = true;
        if (init.isGoal()) return;
        while (true) {
            if (notFinishA) {
                notFinishA = circle(bs, boardListTmp, pq);
                if (bs.getBoard().isGoal()) {
                    this.isSolve = true;
                    this.moves = bs.getMoves();
                    break;
                }
                StdOut.println(init.manhattan());
                // if (bs.getMoves() > init.manhattan()) {
                //     break;
                // }
            }
            else break;
            // if (notFinishB) {
            //     notFinishB = circle(anotherSS, boardListAnother, pqAnother);
            //     if (anotherSS.min.getBoard().isGoal()) {
            //         this.isSolve = false;
            //         break;
            //     }
            // }
        }
    }

    private boolean circle(BoardStruct bs, Stack<Board> boardListOne, MinPQ<BoardStruct> pq) {

        int currentMoves;
        int diff;
        boolean isEqualed = false;
        currentMoves = bs.getMoves() + 1;

        for (Board b : bs.getBoard().neighbors()) {
            // cmp pre
            if (!isEqualed
                && b.equals(bs.getPrevious())) {
                isEqualed = true;
                continue;
            }
            // if (null == ss.min
            //     || b.manhattan() == ss.current.manhattan() - 1) {
            //     BoardStruct bs = new BoardStruct(currentMoves, b, ss.current);
            //     pq.insert(bs);
            // }
            BoardStruct bsNew = new BoardStruct(currentMoves, b, bs.getBoard());
            pq.insert(bsNew);
        }

        // StdOut.println("*****");
        // for (BoardStruct bb : pq) {;
        //     StdOut.print(bb.getBoard());
        //     StdOut.println(bb.getBoard().manhattan());
        // // }
        if (pq.size() <= 0) return false;
        BoardStruct min = pq.delMin();
        while (min.getMoves() > currentMoves) {
            // StdOut.println("====");
            // StdOut.println(ss.min.getBoard());
            min = pq.delMin();
        }

        // StdOut.println("*****");
        // StdOut.println(min.getBoard());
        // StdOut.println(min.getBoard().manhattan());

        diff = currentMoves - min.getMoves();
        // StdOut.println("====");
        // StdOut.println(boardListOne);
        // StdOut.println(currentMoves);
        // StdOut.println(ss.min.getMoves());
        // StdOut.println(currentMoves - ss.min.getMoves());
        // StdOut.println(diff);
        while (diff-- > 0) {
            boardListOne.pop();
        }

        bs.setPrevious(min.getPrevious());
        bs.setBoard(min.getBoard());
        bs.setMoves(min.getMoves());
        boardListOne.push(bs.getBoard());

        // StdOut.println("====");
        // StdOut.println(bs.getBoard());
        return true;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.isSolve;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.moves;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.boardList;
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
