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

    Board(Board b){
        this.size = b.size;
        this.board = b.board;
        initCells();
    }

    Cell getCell(int i, int j){
        return board[i][j];
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
        }else{
            System.out.println("FAILED");
        }
    }

    public void delightTiles(int i, int j) {
        //up
        for (int z = i; z >= 0; z--) {
            if (board[z][j].getWall()) {
                break;
            }
            board[z][j].setLit(false);
        }

        //down
        for (int z = i; z < size; z++) {
            if (board[z][j].getWall()) {
                break;
            }
            board[z][j].setLit(false);
        }

        //right
        for (int z = j; z < size; z++) {
            if (board[i][z].getWall()) {
                break;
            }
            board[i][z].setLit(false);
        }

        //left
        for (int z = j; z >= 0; z--) {
            if (board[i][z].getWall()) {
                break;
            }
            board[i][z].setLit(false);
        }
    }

    void removeBulb(int i, int j){
        board[i][j].setBulb(false);

        //remove the l tiles
        delightTiles(i, j);

        //now need to up date all other Bulb tiles
        for(int row = 0; row <size; row++){
            for(int col = 0; col < size; col++){
                if(board[row][col].getBulb()){
                    lightUpTiles(row, col);
                }
            }
        }
    }

    /**
     * @param i - row
     * @param j - col
     * @return if it can placed based on the wall constraint
     */
    private boolean checkNeighbors(int i, int j) {
        ArrayList<Cell> walls = new ArrayList<>();
        int wallsSat = 0;
        //checking curr position to see if there are any wall around me
        //looking above
        try{
            if(board[i-1][j].getWall()){
                walls.add(board[i-1][j]);
            }
        }catch (Exception e){
            System.out.println("See if there is a neighbor above did not work");
        }
        //looking below
        try{
            if(board[i+1][j].getWall()){
                walls.add(board[i+1][j]);
            }
        }catch (Exception e){
            System.out.println("See if there is a neighbor below did not work");
        }

        //looking left
        try{
            if(board[i][j-1].getWall()){
                walls.add(board[i][j-1]);
            }
        }catch (Exception e){
            System.out.println("See if there is a neighbor left did not work");
        }

        //looking right
        try{
            if(board[i][j+1].getWall()){
                walls.add(board[i][j+1]);
            }
        }catch (Exception e){
            System.out.println("See if there is a neighbor right did not work");
        }


        for(int iter = 0; iter < walls.size(); iter++){
            if(walls.get(iter).getBulbsAroundWall() == -1){ //dosent matter how many bulbs are around
                wallsSat++;
                continue;
            }else{
                //have the wall calculate how many bulbs it has around it
                int count = countNeighborBulb(walls.get(iter).getRow(), walls.get(iter).getCol());
                if(walls.get(iter).getBulbsAroundWall() > count){ //check if you can still place
                    wallsSat++;
                }else{
                    System.out.println("Cant place bulb here because of wall: " + walls.get(iter).getRow() + ", " + walls.get(iter).getCol());
                    return false;
                }
            }
        }

        if(wallsSat == walls.size()){ //all walls are satisfied
            return true;
        }
        return  false;
    }

    public int countNeighborBulb(int i, int j){
        int count = 0;
        try{
            if(board[i - 1][j].getBulb()){
                count++;
            }
        }catch (Exception e){

        }
        try{
            if(board[i + 1][j].getBulb()){
                count++;
            }
        }catch (Exception e){

        }
        try{
            if(board[i][j + 1].getBulb()){
                count++;
            }
        }catch (Exception e){

        }
        try{
            if(board[i][j - 1].getBulb()){
                count++;
            }
        }catch (Exception e){

        }

        return count;
    }

    public void reset(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(!board[i][j].getWall()){
                    board[i][j].setBulb(false);
                    board[i][j].setLit(false);
                }
            }
        }
    }

    public void lightUpTiles(int i, int j) {
        //up
        for (int z = i - 1; z >= 0; z--) {
            if (board[z][j].getWall()) {
                break;
            }
            board[z][j].setLit(true);
        }

        //down
        for (int z = i + 1; z < size; z++) {
            if (board[z][j].getWall()) {
                break;
            }
            board[z][j].setLit(true);
        }

        //right
        for (int z = j + 1; z < size; z++) {
            if (board[i][z].getWall()) {
                break;
            }
            board[i][z].setLit(true);
        }

        //left
        for (int z = j - 1; z >= 0; z--) {
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
                if (board[i][j].getWall() || board[i][j].getLit() || board[i][j].getBulb()) {
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
        public int bulbs;

        Cell(boolean wall, boolean lightBulb, boolean lit) {
            this.wall = wall;
            this.lightBulb = lightBulb;
            this.lit = lit;
        }

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
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
