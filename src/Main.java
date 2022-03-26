import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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

        //initial board taken from https://www.puzzle-light-up.com/faq.php
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

        //3. print out the board with the appr. bulbs and lights

        //backtrack solver
        //1. get the lightbulb candidates
        solver.preprocessor();
        ArrayList<Board.Cell> candidates = new ArrayList<>();
        for(int i = 0; i < board.size; i++){
            for(int j = 0; j < board.size; j++){
                if(!board.getCell(i, j).getWall()){
                    candidates.add(board.getCell(i, j)); //add the cell to i, j
                }
            }
        }
        ArrayList<Board.Cell> sentInCandidate = new ArrayList<>();
        for(int k = 0; k < candidates.size(); k++){
            sentInCandidate.add(candidates.get(k));  //reset the data
        }
        for(int i = 0; i < candidates.size(); i++) {
            Board solved = solver.backTrackSolve(board, sentInCandidate, i);
            if (solved != null) {
                solved.printBoard();
            }
            for(int k = 0; k < candidates.size(); k++){
                sentInCandidate.add(candidates.get(k));  //reset the data
            }
            board.reset();
            solver.preprocessor(); //reset the preprocessed data
        }

       // testing

//        board.placeBulb(0, 0);
//        board.placeBulb(3, 1);
//        board.removeBulb(3, 1);
//        board.printBoard();
//        boolean check = solver.checkBoardWalls(board.board); //sending in the cells
//        System.out.println(check);

//        solver.preprocessor();
//        board.printBoard();
//        board.reset();
//        board.printBoard();


        input.close();
    }



}
