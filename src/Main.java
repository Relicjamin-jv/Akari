public class Main {

    public static void main(String[] args) {
        /*
        TESTING BOARD
         */
        int size = 7;
        boolean[][] walls = new boolean[size][size];
        int[][] numberBulbsAroundWall = new int[size][size];
        walls[1][1] = true;
        walls[5][2] = true;
        numberBulbsAroundWall[1][1] = 2;
        numberBulbsAroundWall[5][2] = 3;

        Board board = new Board(size, walls, numberBulbsAroundWall);

        board.printBoard();
    }

}
