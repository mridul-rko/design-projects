package LLD_Snake_Ladder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;

    public Board(int size, int snakes, int ladders) {
        initializeCells(size);
        addSnakesLadders(cells, snakes, ladders);
    }

    private void initializeCells(int size) {
        cells = new Cell[size][size];

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void addSnakesLadders(Cell[][] cells, int snakes, int ladders) {

        while(snakes > 0) {
            int head = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int tail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);

            if(head >= tail) {
                continue;
            }

            Jump snake = new Jump();
            snake.start = head;
            snake.end = tail;

            Cell cell = getCell(head);
            cell.jump = snake;

            snakes--;
        }

        while(ladders > 0) {
            int start = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int end = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);

            if(start <= end) {
                continue;
            }

            Jump ladderObj = new Jump();
            ladderObj.start = start;
            ladderObj.end = end;

            Cell cell = getCell(start);
            cell.jump = ladderObj;

            ladders--;
        }

    }

    Cell getCell(int playerPos) {

        int row = playerPos / cells.length;
        int col = playerPos % cells.length;

        return cells[row][col];
    }
}
