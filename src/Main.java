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
        System.out.println("Initial board\n");
        board.printBoard();

        int turns = 0;
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
        }


        input.close();
    }

}
