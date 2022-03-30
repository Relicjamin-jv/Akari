import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        /*
        TESTING BOARD
         */

         /*Board board = new Board(7);

         board.placeWall(5, 5, -1);
         board.placeWall(3,4,3);

         board.placeBulb(4, 3);
         board.placeBulb(4,2);
         board.placeBulb(5, 5);


         board.printBoard();*/

        //init scanner for user input
        Scanner input = new Scanner(System.in);
        //Instantiating the File class
        File file = new File("./sample.txt");
        //Instantiating the PrintStream class
        PrintStream stream = new PrintStream(file);
        System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
        System.setOut(stream);

       // initial board taken from https://www.puzzle-light-up.com/faq.php
        Board board = new Board(7); //7x7 board
        BoardSolver solver = new BoardSolver(board.board, 7, board);

        //place walls
        board.placeWall(0, 3, 1);
        board.placeWall(0, 5, 0);
        board.placeWall(1, 0, -1);
        board.placeWall(2, 2, -1);
        board.placeWall(2, 4, -1);
        board.placeWall(3, 0, -1);
        board.placeWall(3, 6, -1);
        board.placeWall(4, 2, -1);
        board.placeWall(4, 4, 3);
        board.placeWall(5, 6, -1);
        board.placeWall(6, 1, 3);
        board.placeWall(6, 3, 2);

        //backtrack solver
        //1. get the lightbulb candidates
//        solver.preprocessor();
//        ArrayList<Board.Cell> candidates = new ArrayList<>();
//        for (int i = 0; i < board.size; i++) {
//            for (int j = 0; j < board.size; j++) {
//                if (!board.getCell(i, j).getWall() && !board.getCell(i, j).getBulb()) {
//                    candidates.add(board.getCell(i, j)); //add the cell to i, j
//                }
//            }
//        }
//        //candidates = hueristicSort(candidates, board);
//        for (int i = 0; i < candidates.size(); i++) {
//            System.out.println(BoardSolver.solvedn);
//            if(i > 0){
//                candidates.remove(0);
//            }
//            solver.backTrackSolve(board, candidates);
//            board.reset();
//            solver.preprocessor(); //reset the preprocessed data
//       }
        BoardSolver.solvedn = false; //reset

//        Board board2 = new Board(6);
//        BoardSolver boardSolver2 = new BoardSolver(board2.board, 6, board2);
//        board2.placeWall(0,1,2);
//        board2.placeWall(1, 2, 1);
//        board2.placeWall(1, 4, 2);
//        board2.placeWall(3, 0, 1);
//        board2.placeWall(4, 1, 2);
//        board2.placeWall(4, 4, 2);
//        board2.placeWall(5, 3, 2);
//
//
//        boardSolver2.preprocessor();
//        ArrayList<Board.Cell> candidates2 = new ArrayList<>();
//        for (int i = 0; i < board2.size; i++) {
//            for (int j = 0; j < board2.size; j++) {
//                if (!board2.getCell(i, j).getWall() && !board2.getCell(i, j).getBulb()) {
//                    candidates2.add(board2.getCell(i, j)); //add the cell to i, j
//                }
//            }
//        }
//        //candidates2 = hueristicSort(candidates2, board2);
//        //candidates = hueristicSort(candidates, board);
//        for (int i = 0; i < candidates2.size(); i++) {
//            System.out.println(BoardSolver.solvedn);
//            if(i > 0){
//                candidates2.remove(0);
//            }
//            boardSolver2.backTrackSolve(board2, candidates2);
//            board2.reset();
//            boardSolver2.preprocessor(); //reset the preprocessed data
//        }

//        Board board3 = new Board(7);
//        BoardSolver boardSolver3 = new BoardSolver(board3.board, 7, board3);
//
//        board3.placeWall(0, 0, 2);
//        board3.placeWall(0, 6, 1);
//        board3.placeWall(1, 4, -1);
//        board3.placeWall(2,1, 0);
//        board3.placeWall(4, 5, 3);
//        board3.placeWall(0, 6, 1);
//        board3.placeWall(6, 6, -1);
//
//        boardSolver3.preprocessor();
//        ArrayList<Board.Cell> candidates3 = new ArrayList<>();
//        for (int i = 0; i < board3.size; i++) {
//            for (int j = 0; j < board3.size; j++) {
//                if (!board3.getCell(i, j).getWall() && !board3.getCell(i, j).getBulb()) {
//                    candidates3.add(board3.getCell(i, j)); //add the cell to i, j
//                }
//            }
//        }
//        //candidates3 = hueristicSort(candidates3, board3);
//        for (int i = 0; i < candidates3.size(); i++) {
//            System.out.println(BoardSolver.solvedn);
//            if(i > 0){
//                candidates3.remove(0);
//            }
//            boardSolver3.backTrackSolve(board3, candidates3);
//            board3.reset();
//            boardSolver3.preprocessor(); //reset the preprocessed data
//        }


        Board board4 = new Board(8);
        BoardSolver boardSolver4 = new BoardSolver(board4.board, 8, board4);

//        board4.placeWall(0, 2, 2);
//        board4.placeWall(0, 5, 2);
//        board4.placeWall(1, 1, 3);
//        board4.placeWall(2, 2, -1);
//        board4.placeWall(2, 3, -1);
//        board4.placeWall(2, 5, 3);
//        board4.placeWall(2, 7, 0);
//        board4.placeWall(3,6, -1);
//        board4.placeWall(4, 1, -1);
//        board4.placeWall(5, 0, 2);
//        board4.placeWall(5, 2, 3);
//        board4.placeWall(5, 4, -1);
//        board4.placeWall(5, 5,-1);
//        board4.placeWall(6, 6, 2);
//        board4.placeWall(6, 2, -1);
//        board4.placeWall(6, 5, 2);

        board4.placeWall(1,3,0);
        board4.placeWall(1,6,1);
        board4.placeWall(2,2,2);
        board4.placeWall(4,0,-1);
        board4.placeWall(4,1,-1);
        board4.placeWall(4,5,-1);
        board4.placeWall(5,2,-1);
        board4.placeWall(5,6,0);
        board4.placeWall(6,2,1);
        board4.placeWall(7,0,2);
        board4.placeWall(7,5,0);



        boardSolver4.preprocessor();
        ArrayList<Board.Cell> candidates4 = new ArrayList<>();
        for (int i = 0; i < board4.size; i++) {
            for (int j = 0; j < board4.size; j++) {
                if (!board4.getCell(i, j).getWall() && !board4.getCell(i, j).getBulb()) {
                    candidates4.add(board4.getCell(i, j)); //add the cell to i, j
                }
            }
        }
        //candidates4 = hueristicSort(candidates4, board4);
        for (int i = 0; i < candidates4.size(); i++) {
            System.out.println(BoardSolver.solvedn);
            if(i > 0){
                candidates4.remove(0);
            }
            boardSolver4.backTrackSolve(board4, candidates4);
            board4.reset();
            boardSolver4.preprocessor(); //reset the preprocessed data
        }



        //print board
        //preprocessor
//        System.out.println("Initial board\n");
//        board.printBoard();
//        solver.preprocessor();
//        board.printBoard();

        //play it yourself
        /*int turns = 0;
        while(!board.checkSolved()){
            System.out.println("Turn: " + turns);
            board.printBoard();
            System.out.print("Input - i j - to place a bulb:");
            try {
                int i = Integer.parseInt(input.next());
                int j = Integer.parseInt(input.next());

                board.placeBulb(i, j);

            }catch (Exception e){
                System.out.println(e.toString());
            }
            turns++;
        }*/

        //SAT solver
        //1. Generate a CNF file in Dimacs format

        //2. Send it to the SAT solver to see if it can be satisfied

        //3. print out the board with the appr. bulbs and light


        input.close();
    }

    private static ArrayList<Board.Cell> hueristicSort(ArrayList<Board.Cell> candidates, Board board) {
        ArrayList<Board.Cell> cellSorted = new ArrayList<>();
        ArrayList<Board.Cell> cellSorted0 = new ArrayList<>();
        ArrayList<Board.Cell> cellSorted1 = new ArrayList<>();
        ArrayList<Board.Cell> cellSorted2 = new ArrayList<>();
        ArrayList<Board.Cell> cellSorted3 = new ArrayList<>();
        for (int i = 0; i < candidates.size(); i++) {
            int row = candidates.get(i).getRow();
            int col = candidates.get(i).getCol();
            try {
                if (board.getCell(row - 1, col).getWall()) {
                    if (board.getCell(row - 1, col).getBulbsAroundWall() == 3) {
                        cellSorted3.add(board.getCell(row, col));
                        board.getCell(row, col).h = 3;
                        continue;
                    }
                    if (board.getCell(row - 1, col).getBulbsAroundWall() == 2) {
                        cellSorted2.add(board.getCell(row, col));
                        board.getCell(row, col).h = 2;
                        continue;
                    }
                    if (board.getCell(row - 1, col).getBulbsAroundWall() == 1) {
                        cellSorted1.add(board.getCell(row, col));
                        board.getCell(row, col).h = 1;
                        continue;
                    }
                }

            } catch (Exception e) {
            }
            try {
                if (board.getCell(row + 1, col).getWall()) {
                    if (board.getCell(row + 1, col).getBulbsAroundWall() == 3) {
                        cellSorted3.add(board.getCell(row, col));
                        board.getCell(row, col).h = 3;
                        continue;
                    }
                    if (board.getCell(row + 1, col).getBulbsAroundWall() == 2) {
                        cellSorted2.add(board.getCell(row, col));
                        board.getCell(row, col).h = 2;
                        continue;
                    }
                    if (board.getCell(row + 1, col).getBulbsAroundWall() == 1) {
                        cellSorted1.add(board.getCell(row, col));
                        board.getCell(row, col).h = 1;
                        continue;
                    }
                }
            } catch (Exception e) {
            }
            try{
                if(board.getCell(row, col + 1).getWall()){
                    if(board.getCell(row, col + 1).getBulbsAroundWall() == 3) {
                        cellSorted3.add(board.getCell(row, col));
                        board.getCell(row, col).h = 3;
                        continue;
                    }
                    if(board.getCell(row, col + 1).getBulbsAroundWall() == 2) {
                        cellSorted2.add(board.getCell(row, col));
                        board.getCell(row, col).h = 2;
                        continue;
                    }
                    if(board.getCell(row, col + 1).getBulbsAroundWall() == 1) {
                        cellSorted1.add(board.getCell(row, col));
                        board.getCell(row, col).h = 1;
                        continue;
                    }
                }
            }catch (Exception e){}
            try{
                if(board.getCell(row, col - 1).getWall()){
                    if(board.getCell(row, col -1).getBulbsAroundWall() == 3) {
                        cellSorted3.add(board.getCell(row, col));
                        board.getCell(row, col).h = 3;
                        continue;
                    }
                    if(board.getCell(row, col - 1).getBulbsAroundWall() == 2) {
                        cellSorted2.add(board.getCell(row, col));
                        board.getCell(row, col).h = 2;
                        continue;
                    }
                    if(board.getCell(row, col - 1).getBulbsAroundWall() == 1) {
                        cellSorted1.add(board.getCell(row, col));
                        board.getCell(row, col).h = 1;
                        continue;
                    }
                }
            }catch (Exception e){}
            cellSorted0.add(board.getCell(row, col));
        }

//        for(int i = 0; i < cellSorted3.size(); i++){
//            System.out.println(cellSorted3.get(i).getRow() + ", " + cellSorted3.get(i).getCol() + ", " + cellSorted3.get(i).h);
//        }

        cellSorted.addAll(cellSorted3);
        cellSorted.addAll(cellSorted2);
        cellSorted.addAll(cellSorted1);
        cellSorted.addAll(cellSorted0);

        for(int i = 0; i < cellSorted.size(); i++){
            System.out.println(cellSorted.get(i).getRow() + ", " + cellSorted.get(i).getCol() + ", " + cellSorted.get(i).h);
        }
        return cellSorted;
    }


}
