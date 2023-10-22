package LLD_Snake_Ladder;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;

    public Game() {
        initializeGame();
    }

    private void initializeGame() {

        // set up board
        board = new Board(10, 5,4);

        // set up dice
        dice = new Dice(1);

        // default winner
        winner = null;

        addPlayers();
    }

    private void addPlayers() {

        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame(){

        while(winner == null) {

            //check for turn
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.name + " current position is: " + playerTurn.position);

            //roll the dice
            int diceOutcome = dice.rollKaro();

            //get new position
            int newPosition = playerTurn.position + diceOutcome;

            // check agar jump hai nayi position pe ya nahi
            newPosition = jumpCheck(newPosition);

            // nayi position ko current set kardo
            playerTurn.position = newPosition;

            System.out.println("player turn is:" + playerTurn.name + " new Position is: " + newPosition);


            //check for winner (jo pehle board ke bahar gaya woh winner)
            if(newPosition >= board.cells.length * board.cells.length-1){
                winner = playerTurn;
            }
        }

        System.out.println("WINNER IS:" + winner.name);
    }


    private Player findPlayerTurn() {

        Player playerTurns = playersList.removeFirst();
        playersList.addLast(playerTurns);
        return playerTurns;
    }

    private int jumpCheck (int playerNewPosition) {

        // base case
        if(playerNewPosition > board.cells.length * board.cells.length-1 ){
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);

        if(cell.jump != null && cell.jump.start == playerNewPosition) {
            String jumpBy = (cell.jump.start < cell.jump.end) ? "ladder" : "snake";
            System.out.println("jump by: " + jumpBy);

            return cell.jump.end;
        }

        return playerNewPosition;
    }
}

