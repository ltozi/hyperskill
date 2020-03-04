package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
//        game.loadConfig(System.in);

        game.displayBoard();
        game.start();

        while (game.notFinished()) {
            System.out.print("Enter the coordinates: ");
            try {
                Coordinate cood = game.readCoordinate();

                game.doMove(cood);

                game.displayBoard();

            } catch (IllegalArgumentException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            } catch (CellAlreadyOccupiedException e) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }

        System.out.println(game.getState());

    }
}
