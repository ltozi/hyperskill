package tictactoe;

public enum GameState {

    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");


    private final String message;

    GameState(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        return message;
    }
}
