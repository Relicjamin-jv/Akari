//import org.sat4j.minisat.SolverFactory;
//import org.sat4j.reader.DimacsReader;
//import org.sat4j.reader.ParseFormatException;
//import org.sat4j.reader.Reader;
//import org.sat4j.specs.ContradictionException;
//import org.sat4j.specs.IProblem;
//import org.sat4j.specs.ISolver;
//import org.sat4j.specs.TimeoutException;

//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;

public class BoardSolver {
    static public boolean solvedn = false;
    public Board.Cell[][] board;
    public int sizeOfBoard;
    public Board b;

    BoardSolver(Board.Cell[][] board, int sizeOfBoard, Board b) {
        this.board = board;
        this.sizeOfBoard = sizeOfBoard;
        this.b = b;
    }

    /**
     * decreases the search space and may even solve the puzzle
     */
    void preprocessor() {
        int numNeighbor = 0;
        int numOfbulbs = 0;

        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                if (board[i][j].getWall()) {
                    numOfbulbs = board[i][j].getBulbsAroundWall();
                    numNeighbor = 0;
                    try {
                        if (board[i - 1][j].getBulb()) {

                        }
                        numNeighbor++;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[i + 1][j].getBulb()) {
                        }
                        numNeighbor++;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[i][j + 1].getBulb()) {
                        }
                        numNeighbor++;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[i][j - 1].getBulb()) {
                        }
                        numNeighbor++;
                    } catch (Exception e) {
                    }
                    //System.out.println(i + "," + j + "," + numNeighbor + "," + numOfbulbs);
                    if(numNeighbor == numOfbulbs ){
                        placeLightTrivial(i, j);
                    }
                }
            }
        }
    }


    /**
     * used by the preprocessor to put update tiles to lit
     * @param i
     * @param j
     */
    void placeLightTrivial(int i, int j) {
        try {
//            board[i + 1][j].setBulb(true);
//            b.lightUpTiles(i + 1, j);
            b.placeBulb(i+1, j);
        } catch (Exception e) {
            //System.out.println("Cell below does not exists (Place light)");
        }
        //set the below cell to implacable
        try {
//            board[i - 1][j].setBulb(true);
//            b.lightUpTiles(i - 1, j);
            b.placeBulb(i-1, j);
        } catch (Exception e) {
            //System.out.println("Cell above does not exists (Place light)");
        }
        //check left
        try {
//            board[i][j - 1].setBulb(true);
//            b.lightUpTiles(i, j - 1);
            b.placeBulb(i, j - 1);
        } catch (Exception e) {
            //System.out.println("Cell to the left does not exists (Place light)");
        }
        //check right
        try {
//            board[i][j + 1].setBulb(true);
//            b.lightUpTiles(i, j + 1);
            b.placeBulb(i, j + 1);
        } catch (Exception e) {
            //System.out.println("Cell to the right does not exists (Place Light)");
        }
    }

    /**
     * Solves with SAT file to see if it's satisfied. If so it will print out the model to Console (can change it to some sting for answer)
     * @param - Dimacs file
     *
     */
    /*void SATSolver(String filename) {
        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(3600); // 1 hour timeout
        Reader reader = new DimacsReader(solver);
        PrintWriter out = new PrintWriter(System.out, true);
        // CNF filename is given on the command line
        try {
            IProblem problem = reader.parseInstance(filename);
            if (problem.isSatisfiable()) {
                System.out.println("Satisfiable !");
                reader.decode(problem.model(), out);
            } else {
                System.out.println("Unsatisfiable !");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (ParseFormatException e) {
            System.out.println("Not in DIMACS format");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ContradictionException e) {
            System.out.println("Unsatisfiable (trivial)!");
        } catch (TimeoutException e) {
            System.out.println("Ran out of time to solve the issue");
        }

    }*/

    Board backTrackSolve(Board b, ArrayList<Board.Cell> candidates, int i){
        //b.printBoard();
        System.out.println();
        if(b.checkSolved()){
            //return board
            System.out.println("-----SOLVED------");
            b.printBoard();
            solvedn = true;
            return null;
        }
        if(solvedn){ //no need to find another solution if there is one
            return null;
        }
        if(i >= candidates.size() || checkBoardWalls(b.board)) {
            //b.printBoard();
            if(candidates.isEmpty()){
                //System.out.println("Ran out of candidates");
            }else{
                //System.out.println("Cant Satisfy due to constraints");
            }
            return null;
        }

        //place a lightbulb on the next candidate
        b.placeBulb(candidates.get(i).getRow(), candidates.get(i).getCol());
        backTrackSolve(b, candidates, i+1); //add the next candidate
        b.removeBulb(candidates.get(i).getRow(), candidates.get(i).getCol());
        candidates.remove(i); //we no longer what this candidate to be propagated from this
        backTrackSolve(b, candidates, i); //add the next candidate



        return null;
    }

    /**
     *
     * @param b the board its looking at
     * @return true if a wall can no longer be satisfied
     */
    boolean checkBoardWalls(Board.Cell[][] b) {
        ArrayList<Board.Cell> walls = new ArrayList<>();
        //get all the walls
        for(int i = 0; i < sizeOfBoard; i++){
            for(int j = 0; j < sizeOfBoard; j++){
                if(b[i][j].getWall()){
                    walls.add(b[i][j]);
                }
            }
        }
        //get information on all the walls neighbors, then use that information to see if it can still be satisfied, if not return true for failing the check
        for(int iter = 0; iter < walls.size(); iter++){
            Board.Cell curr = walls.get(iter);
            int numLit = 0;
            int space = 0;
            int numBulb = 0;
            int row = curr.getRow();
            int col = curr.getCol();

            try{
                if(b[row-1][col].getLit()){
                    numLit++;
                }
                if(b[row-1][col].getBulb()){
                    numBulb++;
                }
                space++;
            }catch (Exception e){}
            try{
                if(b[row+1][col].getLit()){
                    numLit++;
                }
                if(b[row+1][col].getBulb()){
                    numBulb++;
                }
                space++;
            }catch (Exception e){}
            try{
                if(b[row][col-1].getLit()){
                    numLit++;
                }
                if(b[row][col-1].getBulb()){
                    numBulb++;
                }
                space++;
            }catch (Exception e){}
            try{
                if(b[row][col+1].getLit()){
                    numLit++;
                }
                if(b[row][col+1].getBulb()){
                    numBulb++;
                }
                space++;
            }catch (Exception e){}

            //if lit more then number of bulbs then there should be the exact correct number of bulbs around it
            if(numBulb == walls.get(iter).getBulbsAroundWall() || space - numLit >= walls.get(iter).getBulbsAroundWall()){
                //System.out.println(row + ", " + col + ", " + numLit + ", " + numBulb + ", " + space);
            }else{
                //System.out.println("true " + row + ", " + col + ", " + numLit + ", " + numBulb + ", " + space);
                return true;
            }
        }

        return false;
    }

}
