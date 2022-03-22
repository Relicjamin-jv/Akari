import java.util.ArrayList;

/**
 * Author - Collin Campbell
 */
public class Board {
    public int size = 0; //size of the board size x size
    public Cell[][] board;

    Board(int size) {
        this.size = size;
        this.board = new Cell[size][size];
        initCells();
    }

    /**
     * init the cells into mem.
     */
    void initCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * @param i      - row
     * @param j      - col
     * @param number - wall number (how many bulbs are around the wall)
     */
    void placeWall(int i, int j, int number) {
        Cell position = board[i][j];
        position.setBulbsAroundWall(number);
        position.setWall(true);
    }

    void placeBulb(int i, int j) {
        //place the bulb
        boolean canPlace = board[i][j].checkCell() && checkNeighbors(i, j);;

        //light up the tiles
        if (canPlace) {
            board[i][j].setBulb(true);
            lightUpTiles(i, j);
        }
    }

    /**
     * @param i - row
     * @param j - col
     * @return if it can placed based on the wall constraint
     */
    private boolean checkNeighbors(int i, int j) {
        ArrayList<Cell> walls = new ArrayList<>();

        if(i > 0 && i < 6 && j > 0 && j < 6) {
            //check above
            if (board[i - 1][j].getWall()) {
                walls.add(board[i - 1][j]);
            }
            //check below
            if (board[i + 1][j].getWall()) {
                walls.add(board[i + 1][j]);
            }
            //check left
            if (board[i][j - 1].getWall()) {
                walls.add(board[i - 1][j]);
            }
            //check right
            if (board[i][j + 1].getWall()) {
                walls.add(board[i + 1][j]);
            }
        }else{ //edge cases
            //if we're at the top left corner
            if(i == 0 && j == 0){
                //check below
                if (board[i + 1][j].getWall()) {
                    walls.add(board[i + 1][j]);
                }
                //check right
                if (board[i][j + 1].getWall()) {
                    walls.add(board[i + 1][j]);
                }
            }else if(i == 6 && j == 0){ //we're at the bottom left corner
                //check above
                if (board[i - 1][j].getWall()) {
                    walls.add(board[i - 1][j]);
                }
                //check right
                if (board[i][j + 1].getWall()) {
                    walls.add(board[i][j + 1]);
                }
            }else if(i == 0 && j == 6){ //at the top right corner
                //check below
                if (board[i + 1][j].getWall()) {
                    walls.add(board[i + 1][j]);
                }
                //check left
                if (board[i][j - 1].getWall()) {
                    walls.add(board[i][j - 1]);
                }
            }else if(i == 6 && j == 6){//bottom right corner
                //check above
                if (board[i - 1][j].getWall()) {
                    walls.add(board[i - 1][j]);
                }
                //check left
                if (board[i][j - 1].getWall()) {
                    walls.add(board[i][j - 1]);
                }
            }else if(i == 0){ //were at the top most corner
                //check below
                if (board[i + 1][j].getWall()) {
                    walls.add(board[i + 1][j]);
                }
                //check left
                if (board[i][j - 1].getWall()) {
                    walls.add(board[i][j - 1]);
                }
                //check right
                if (board[i][j + 1].getWall()) {
                    walls.add(board[i][j + 1]);
                }
            }else if(i == 6){ //were at the right most corner
                //check above
                if (board[i - 1][j].getWall()) {
                    walls.add(board[i - 1][j]);
                }
                //check left
                if (board[i][j - 1].getWall()) {
                    walls.add(board[i][j - 1]);
                }
                //check right
                if (board[i][j + 1].getWall()) {
                    walls.add(board[i][j + 1]);
                }
            }else if(j == 0){ //were at left most corner
                //check above
                if (board[i - 1][j].getWall()) {
                    walls.add(board[i - 1][j]);
                }
                //check below
                if (board[i + 1][j].getWall()) {
                    walls.add(board[i + 1][j]);
                }
                //check right
                if (board[i][j + 1].getWall()) {
                    walls.add(board[i][j + 1]);
                }
            }else if(j == 6){ //at the most right most corner
                //check above
                if (board[i - 1][j].getWall()) {
                    walls.add(board[i - 1][j]);
                }
                //check below
                if (board[i + 1][j].getWall()) {
                    walls.add(board[i + 1][j]);
                }
                //check left
                if (board[i][j - 1].getWall()) {
                    walls.add(board[i][j - 1]);
                }
            }else{
                System.out.print("WE SHOULD NEVER GET HERE");
            }
        }


        //checking if the bulb is allowed to be there
        for (int b = 0; b < walls.size(); b++) {
            Cell curr = walls.get(b);
            if (curr.bulbs <= 0) {
                System.out.println("Cant place a bulb here due to wall constriction");
                return false;
            }
        }

        //if so decrement all the walls bulbs count
        for (int b = 0; b < walls.size(); b++) {
            Cell curr = walls.get(b);
            curr.bulbs--;
        }

        return true;
    }

    private void lightUpTiles(int i, int j) {
        //up
        for (int z = i; z >= 0; z--) {
            if (board[z][j].getWall()) {
                break;
            }
            board[z][j].setLit(true);
        }

        //down
        for (int z = i; z < size; z++) {
            if (board[z][j].getWall()) {
                break;
            }
            board[z][j].setLit(true);
        }

        //right
        for (int z = j; z < size; z++) {
            if (board[i][z].getWall()) {
                break;
            }
            board[i][z].setLit(true);
        }

        //left
        for (int z = j; z >= 0; z--) {
            if (board[i][z].getWall()) {
                break;
            }
            board[i][z].setLit(true);
        }
    }

    /**
     * Prints out the board at any given time
     */
    void printBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j].getWall()) {
                    if (board[i][j].getBulbsAroundWall() != -1) {
                        System.out.print(" X" + this.board[i][j].bulbsAroundWall);
                    } else {
                        System.out.print(" X ");
                    }
                } else if (this.board[i][j].getBulb()) {
                    System.out.print(" B ");
                } else if (this.board[i][j].getLit()) {
                    System.out.print(" L ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Used for debugging the win condition
     */
//    void printBoardCheck(){
//        for(int i = 0; i < size; i++){
//            for(int j = 0; j < size; j++){
//                if(board[i][j].getWall() || board[i][j].getLit() ){
//                    System.out.print(" T ");
//                }else{
//                    System.out.print(" F ");
//                }
//            }
//            System.out.println();
//        }
//    }

    /**
     * @return if the puzzle has been solved
     */
    boolean checkSolved() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getWall() || board[i][j].getLit()) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    class Cell {
        private boolean wall = false;
        private boolean lightBulb = false;
        private boolean lit = false;
        private int bulbsAroundWall = 0;
        private int row;
        private int col;
        private int bulbs = 0;

        Cell(boolean wall, boolean lightBulb, boolean lit) {
            this.wall = wall;
            this.lightBulb = lightBulb;
            this.lit = lit;
        }

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Sets it if the cell is lit or not
         *
         * @param lit t/f
         */
        void setLit(boolean lit) {
            this.lit = lit;
        }

        /**
         * returns whether or not the cell is lit
         *
         * @return
         */
        boolean getLit() {
            return lit;
        }

        /**
         * Sets a bulb in that cell
         *
         * @param bulb
         */
        void setBulb(boolean bulb) {
            this.lightBulb = bulb;
        }

        boolean checkCell(){
            if (wall || lit) {
                System.out.println("Cant place a bulb here: (" + row + ", " + col + ")");
                return false;
            }
            return true;
        }

        /**
         *
         * @return if the bulb can be placed, under the wall constraint
         */

        /**
         * Returns if there is a lightbulb in the current cell
         *
         * @return
         */
        boolean getBulb() {
            return lightBulb;
        }

        /**
         * returns wheter the cell is a wall or not
         *
         * @return
         */
        boolean getWall() {
            return this.wall;
        }

        /**
         * Sets the wall at the cell
         *
         * @param wall
         * @return
         */
        void setWall(boolean wall) {
            this.wall = wall;
        }

        /**
         * @return The number of bulbs for any wall
         */
        int getBulbsAroundWall() {
            return this.bulbsAroundWall;
        }

        /**
         * Setting the number of bulbs for any wall
         *
         * @param number
         */
        void setBulbsAroundWall(int number) {
            this.bulbsAroundWall = number;
            this.bulbs = number;
        }

    }
}
