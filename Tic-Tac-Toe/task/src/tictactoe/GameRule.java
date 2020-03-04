package tictactoe;

public class GameRule {
    private final Board board;

    public GameRule(Board b) {
        this.board = b;
    }


    public boolean isWinner(char x) {
        return findRowMatch(x) || findColumnMatch(x) || findDiagonalMatch(x);
    }

    private boolean findColumnMatch(char x) {
        char[][] data = board.getData();
        for (int i = 0; i < data.length; i++) {
            if (data[0][i] == x && data[1][i] == x && data[2][i] == x) {
                return true;
            }
        }
        return false;
    }

    private boolean findRowMatch(char x) {
        char[][] data = board.getData();
        for (int i = 0; i < data.length; i++) {
            if (data[i][0] == x && data[i][1] == x && data[i][2] == x) {
                return true;
            }
        }
        return false;
    }

    private boolean findDiagonalMatch(char x) {
        char[][] data = board.getData();

        int countD1 = 0;
        int countD2 = 0;

        for (int i = 0, j = 0; i < data.length; i++, j++) {
            if (data[i][j] == x)
                countD1++;
        }

        //check diagonal left
        for (int i = data.length - 1, j = 0; i >= 0; i--, j++) {
            if (data[i][j] == x)
                countD2++;
        }

        return countD1 == 3 || countD2 == 3;
    }

    public boolean hasEmptyCells() {
        char[][] data = board.getData();
        for (int i = 0; i < data.length; i++) {
            char[] row = data[i];
            for (int col = 0; col < row.length; col++) {
                if(row[col] == Move.EMPTY)
                    return true;
            }
        }
        return false;
    }
}
