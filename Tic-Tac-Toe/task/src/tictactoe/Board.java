package tictactoe;

import java.util.Arrays;

public class Board {

    private static final int BOARD_SIZE = 3;

    private char[][] data;

    public Board() {
        data = new char[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < data.length; row++) {
            char[] rows = data[row];
            for (int col = 0; col < rows.length; col++) {
                rows[col] = Move.EMPTY;
            }
        }

    }

    /**
     * Load board
     * @param chars
     */
    public void load(char[] chars) {
        int countX = 0;
        int countY = 0;

        int charIndexStartsFrom = 0; //Can be 0 - 3 - 6 to correctly place elements in 3x3 matrix
        for (int row = 0; row < data.length; row++) {
            char[] columns = data[row];
            for (int col = 0; col < columns.length; col++) {
                char anInput = chars[charIndexStartsFrom + col];
                columns[col] = anInput;

                if(anInput == Move.X)
                    countX++;
                else if(anInput == Move.O)
                    countY++;

            }
            charIndexStartsFrom += BOARD_SIZE;
        }

        if(Math.abs(countX - countY) > 1) {
            throw new IllegalArgumentException("Too many moves for a player: " + chars);
        }
    }



    public void draw() {
        System.out.println("---------");
        for (int i = 0; i < data.length; i++) {
            System.out.print("| ");
            for (int col = 0; col < data[i].length; col++) {
                if(data[i][col] == Move.EMPTY)
                    System.out.print( " " + " ");
                else
                    System.out.print(data[i][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public char[][] getData() {
        return data;
    }

    public void setCell(char c, int x, int y) throws CellAlreadyOccupiedException {
        if(x < 1 || x > BOARD_SIZE)
            throw new IllegalArgumentException();
        if(y < 1 || y > BOARD_SIZE)
            throw new IllegalArgumentException();

        int convertedX = x - 1;
        int convertedY = data.length - y;

        if(data[convertedY][convertedX] != Move.EMPTY) {
            throw new CellAlreadyOccupiedException();
        }

        data[convertedY][convertedX] = c;
    }
}
