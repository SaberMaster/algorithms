import edu.princeton.cs.algs4.Stack;
/**
 * @file   Board.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun  9 10:37:15 2017
 *
 * @brief
 *
 *
 */

public class Board {
    private int[][] blocks;
    private int dimen;
    private int manha;
    private int hamm;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
        this.dimen = blocks.length;
        this.manha = -1;
        this.hamm = -1;
    }
    // board dimension n
    public int dimension() {
        return this.dimen;
    }
    // number of blocks out of place
    public int hamming() {
        if (-1 != this.hamm) return this.hamm;
        int sum = 0;
        int index;
        for (int i = 0; i < this.dimen; i++) {
            for (int j = 0; j < this.dimen; j++) {
                index = i * this.dimen + j;
                if (0 == this.blocks[i][j]) continue;
                if (index + 1 != this.blocks[i][j]) sum++;
            }
        }
        this.hamm = sum;
        return sum;
    }
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (-1 != this.manha) return this.manha;
        int sum = 0;
        int index;
        for (int i = 0; i < this.dimen; i++) {
            for (int j = 0; j < this.dimen; j++) {
                index = i * this.dimen + j;
                if (0 == this.blocks[i][j]) continue;
                if (index + 1 != this.blocks[i][j]) {
                    int xCoor = (this.blocks[i][j] - 1) % this.dimen;
                    int yCoor = (this.blocks[i][j] - 1) / this.dimen;
                    sum += Math.abs(i - yCoor) + Math.abs(j - xCoor);
                }
            }
        }
        this.manha = sum;
        return sum;
    }
    // is this board the goal board?
    public boolean isGoal() {
        return 0 == this.hamming();
    }
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] blocksCopy = this.getBlocks();
        int index1 = 0, index2 = 1;
        if (0 == blocksCopy[0 / this.dimen][0 % this.dimen]) {
            index1 = 2;
        }
        else if (0 == blocksCopy[1 / this.dimen][1 % this.dimen]) {
            index2 = 2;
        }

        swap(blocksCopy, index1, index2);
        return new Board(blocksCopy);
    }

    private void swap(int[][] blocksTmp, int index1, int index2) {
        int tmp = blocksTmp[index1 / this.dimen][index1 % this.dimen];
        blocksTmp[index1 / this.dimen][index1 % this.dimen] = blocksTmp[index2 / this.dimen][index2 % this.dimen];
        blocksTmp[index2 / this.dimen][index2 % this.dimen] = tmp;
    }

    private int[][] getBlocks() {
        int[][] blocksCopy = new int[this.dimen][this.dimen];
        int length = this.dimen * this.dimen;
        int index1 = length;
        int index2 = length;
        for (int i = 0; i < this.dimen; i++) {
            for (int j = 0; j < this.dimen; j++) {
                blocksCopy[i][j] = this.blocks[i][j];
                if (length == index1
                    && 0 != blocksCopy[i][j]) {
                    index1 = i * this.dimen + j;
                }
                else if (length == index2
                         && 0 != blocksCopy[i][j]) {
                    index2 = i * this.dimen + j;
                }
            }
        }
        return blocksCopy;
    }
    // does this board equal y?
    public boolean equals(Object y) {
        if (null == y) return false;
        if (y == this) return true;
        if (this.getClass() != y.getClass()) return false;
        int[][] blocksCopy = ((Board) y).getBlocks();
        for (int i = 0; i < this.dimen; i++) {
            for (int j = 0; j < this.dimen; j++) {
                if (blocksCopy[i][j] != this.blocks[i][j]) return false;
            }
        }
        return true;
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {
        int blankIndex = 0;
        for (int i = 0; i < this.dimen * this.dimen; i++) {
            if (0 == this.blocks[i / this.dimen][i % this.dimen]) {
                blankIndex = i;
                break;
            }
        }
        // StdOut.println(blankIndex);
        int xCoor = blankIndex / this.dimen;
        int yCoor = blankIndex % this.dimen;
        Stack<Board> boardArr = new Stack<Board>();
        // swap with up
        if (xCoor - 1 >= 0) {
            // StdOut.println("up");
            addNeighbor(boardArr, blankIndex, blankIndex - this.dimen);
        }
        // swap with down
        if (xCoor + 1 < this.dimen) {
            // StdOut.println("down");
            addNeighbor(boardArr, blankIndex, blankIndex + this.dimen);
        }
        // swap with left
        if (yCoor - 1 >= 0) {
            // StdOut.println("left");
            addNeighbor(boardArr, blankIndex, blankIndex - 1);
        }
        // swap with right
        if (yCoor + 1 < this.dimen) {
            // StdOut.println("right");
            addNeighbor(boardArr, blankIndex, blankIndex + 1);
        }
        return boardArr;
    }


    private void addNeighbor(Stack<Board> boardArr, int blankIndex, int toIndex) {
        int[][] newBlocks = this.getBlocks();
        swap(newBlocks, blankIndex, toIndex);
        boardArr.push(new Board(newBlocks));
    }


    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.dimen + "\n");
        for (int i = 0; i < this.dimen; i++) {
            for (int j = 0; j < this.dimen; j++) {
                s.append(String.format("%2d ", this.blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
