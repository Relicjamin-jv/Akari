Hello to whoever is reading this. This code is very poorly written, it works but kinda like I threw it into a salad and tossed it.

So lets start with main.java
    1. All the boards are commented out except for one, which is then ran. You could comment out all the boards and run them all, but reading the output would be hard
    2. To remove preprocessing, comment out the lines with .preprocessing() within the call to dfs
    3. To remove the huer. commit hot the line that sorts the candidates
    4. Making a new board is painful, I'd recommend that if you were going to fiddle with this poorly written code, I'd just go to the size board you are looking for and put in new walls
        with board.placeWall(row, col, num); i.e -1 for num will not put a constraint on that wall for how many lightbulbs are needed.
        ex.
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

                solver.preprocessor(); <--- COMMENT OUT THIS FOR REMOVAL OF PREPROCESSOR
                ArrayList<Board.Cell> candidates = new ArrayList<>();
                for (int i = 0; i < board.size; i++) {
                    for (int j = 0; j < board.size; j++) {
                        if (!board.getCell(i, j).getWall() && !board.getCell(i, j).getBulb()) {
                            candidates.add(board.getCell(i, j)); //add the cell to i, j
                        }
                    }
                }
                long startTime = System.nanoTime();
                candidates = hueristicSort(candidates, board); <---- COMMENT OUT THIS LINE TO REMOVE HUER.
                for (int i = 0; i < candidates.size(); i++) {
                    if(i > 0){
                        candidates.remove(0);
                    }
                    solver.backTrackSolve(board, candidates);
                    board.reset();
                    solver.preprocessor(); <--- COMMENT OUT THIS FOR REMOVAL OF PREPROCESSOR
               }
                long endTime = System.nanoTime();

                System.out.println("Took: " + (endTime - startTime)/1000000000.0);
