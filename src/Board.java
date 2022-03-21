/**
 * Author - Collin Campbell
 */
public class Board {
    public int size = 0; //size of the board size x size
    public Cell[][] board;

    Board(int size, boolean walls[][], int numberBulb[][]){
        this.size = size;
        this.board = new Cell[size][size];
        initCells();
        placeWalls(walls, numberBulb);
    }

    void initCells(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * @param walls - Where the walls are placed on the board
     */
    void placeWalls(boolean walls[][], int[][] numberBulb){
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                if(walls[i][j]){
                    board[i][j].setWall(true);
                    board[i][j].setBulbsAroundWall(numberBulb[i][j]);
                }
            }
        }
    }

    /**
     * Prints out the board at any given time
     */
    void printBoard(){
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                if(this.board[i][j].wall){
                    System.out.print(" X" + this.board[i][j].bulbsAroundWall);
                }else if(this.board[i][j].lit){
                    System.out.println(" L ");
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    class Cell{
        boolean wall = false;
        boolean lightBulb = false;
        boolean lit = false;
        int bulbsAroundWall = 0;

        Cell(boolean wall, boolean lightBulb, boolean lit){
            this.wall = wall;
            this.lightBulb = lightBulb;
            this.lit = lit;
        }

        Cell(){
            this.wall = false;
            this.lightBulb = false;
            this.lit = false;
        }

        /**
         * Sets it if the cell is lit or not
         * @param lit
         */
        void setLit(boolean lit){
            this.lit = lit;
        }

        /**
         * returns whether or not the cell is lit
         * @return
         */
        boolean getLit(){
            return lit;
        }

        /**
         * Sets a bulb in that cell
         * @param bulb
         */
        void setBulb(boolean bulb){
            this.lightBulb = bulb;
        }

        /**
         * Returns if there is a lightbulb in the current cell
         * @return
         */
        boolean getBulb(){
            return lightBulb;
        }

        /**
         * returns wheter the cell is a wall or not
         * @return
         */
        boolean getWall(){
            return this.wall;
        }

        /**
         * Sets the wall at the cell
         * @param wall
         * @return
         */
        void setWall(boolean wall){
            this.wall = wall;
        }

        /**
         *
         * @return The number of bulbs for any wall
         */
        int getBulbsAroundWall(){
            return this.bulbsAroundWall;
        }

        /**
         * Setting the number of bulbs for any wall
         * @param number
         */
        void setBulbsAroundWall(int number){
            this.bulbsAroundWall = number;
        }

    }
}
