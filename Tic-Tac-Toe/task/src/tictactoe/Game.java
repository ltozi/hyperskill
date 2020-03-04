package tictactoe;

import java.io.InputStream;
import java.util.Scanner;

public class Game {

    private char nextPlayer = Move.X; //default

    private Board board = new Board();

    private GameState state = GameState.NOT_FINISHED;

    private Scanner scanner;

    private char[] initialConfigInput = {Move.EMPTY, Move.EMPTY, Move.EMPTY, Move.EMPTY, Move.EMPTY, Move.EMPTY, Move.EMPTY, Move.EMPTY, Move.EMPTY };

    public Game() {
        scanner = new Scanner(System.in);
    }


    public void start() {
        board.load(initialConfigInput);
    }


    public Board getBoard() {
        return board;
    }


    /**
     * Apply game rules to calculate current state. This change internal state of game
     *
     */
    public void applyRules() {
        GameRule gameRule = new GameRule(getBoard());

        boolean wonX = gameRule.isWinner(Move.X);
        boolean wonY = gameRule.isWinner(Move.O);

        if(wonX && wonY)
            switchState(GameState.IMPOSSIBLE);
        else if (wonX)
            switchState(GameState.X_WINS);
        else if(wonY)
            switchState(GameState.O_WINS);
        else if(gameRule.hasEmptyCells()) {
            switchState(GameState.NOT_FINISHED);
        }
        else
            switchState(GameState.DRAW);
    }


    void switchState(GameState newState) {
        this.state = newState;
    }

    public GameState getState() {
        return state;
    }

    public void displayBoard() {
        getBoard().draw();
    }

    /**
     * When a move is performed correctly, the next player is automatically calculated
     *
     * @param coordinate
     * @throws CellAlreadyOccupiedException
     */
    public void doMove(Coordinate coordinate) throws CellAlreadyOccupiedException {
        board.setCell(nextPlayer, coordinate.x, coordinate.y);
        applyRules();

        nextPlayer = nextPlayer();
    }

    public boolean notFinished() {
        return this.getState() == GameState.NOT_FINISHED;
    }

    public void loadConfig(InputStream in) {
        scanner = new Scanner(in);
        System.out.print("Enter cells: "); // __0X__0XX
        String s = scanner.nextLine();
        initialConfigInput = s.toCharArray();
    }

    public Coordinate readCoordinate() {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Coordinate(x, y);
    }

    /**
     *
     * @return the correct player that can do the move
     */
    private char nextPlayer() {
        if(nextPlayer == Move.EMPTY) {
            nextPlayer = Move.O; //Alternatively, use a method to choose player
        }
        else if(nextPlayer == Move.X)
             nextPlayer = Move.O;
        else
            nextPlayer = Move.X;

        return nextPlayer;
    }
}
