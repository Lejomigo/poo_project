package Game.GameBoard;

import Game.GameBoard.Box.Box;
import Game.GameBoard.Matrix.MatrixBoard;
import Game.GameBoard.Matrix.MatrixCenter;
import Game.Pieces.Piece;
import Utilies.Easymeth;

import static Game.GameBoard.Board.printBoard;
import static Utilies.ClearTerminal.clearScreen;

public class BoardMovements {

    public static void backMovBoard(Piece piece, int moves) {
        int positionY = piece.getPositionY();
        int positionX = piece.getPositionX();
        int direction = -1;

        while (moves != 0) {

            printBoard();

            Box matrixPosition = piece.getMatrix().getBoard()[positionY][positionX];;
            System.out.println("movimientos rest: " + moves);
            System.out.println("pieza en la que va " + matrixPosition.getNumber());

            if (piece.getMatrix().getBoard().length == 7) {
                if (positionY == 0) {

                    String option = Easymeth.getString("llegaste a esquina, Deseas ir al centro (1) o seguir por el borde (2)?: ");
                    while (!option.equals("1") && !option.equals("2")) {
                        option = Easymeth.getString("Entrada inválida. Elige 1 (centro) o 2 (borde): ");
                    }

                    if (option.equals("1")) {
                        // Ir al centro: cambiamos la matriz y salimos del movimiento
                        piece.setMatrix(new MatrixCenter());
                        piece.setPositionY(0);
                        piece.setPositionX(positionX);
                        frontMovBoard(piece,moves);
                        return;

                    } else {
                        // Seguir borde hacia la izquierda
                        if (positionX == 0) {
                            positionX = piece.getMatrix().getBoard().length-1 ; // rebote al otro extremo
                        } else {
                            positionX--;
                        }
                        positionY = 6;
                        piece.setMatrix(new MatrixCenter());
                        piece.setPositionY(0);
                        piece.setPositionX(positionX);
                        backMovBoard(piece,moves);
                        return;

                    }

                } else {
                    // Retroceder en Y si no estamos en el borde superior
                    positionY--;

                }

            } else {

                if (positionY == 0) {
                    piece.setMatrix(new MatrixBoard());
                    String option = Easymeth.getString("llegaste a esquina, derecha(1) o izquierda(2)?: ");
                    while (!option.equals("1") && !option.equals("2")) {
                        option = Easymeth.getString("Entrada inválida. Elige 1 (derecha) o 2 (izquierda): ");
                    }
                    if (option.equals("1")) {
                        if (positionX == piece.getMatrix().getBoard()[0].length-1){
                            positionX = 0;}
                        else {
                            positionX--;
                        }


                    } else {
                        positionY=6;
                        if (positionX==0){
                            positionX = piece.getMatrix().getBoard()[0].length-1;
                        }else{
                            positionX--;
                        }
                    }
                }else{
                    positionY--;
                }
            }
            moves--;
        }

        // Actualizar posición final de la pieza
        piece.setPositionX(positionX);
        piece.setPositionY(positionY);
    }

    public static void frontMovBoard(Piece piece, int moves) {
        int positionY = piece.getPositionY();
        int positionX = piece.getPositionX();
        int direction = 1;

        while (moves > 0) {

            printBoard();
            Box matrixPosition = piece.getMatrix().getBoard()[positionY][positionX];
            System.out.println("Movimientos restantes: " + moves);
            System.out.println("Pieza en la que va: " + matrixPosition.getNumber());

            if (piece.getMatrix().getBoard().length == 7) {
                if (positionY == 6) {
                    //if piece.checkColors = true
                    positionY =0;
                    moves--;
                    String option = Easymeth.getString("Llegaste a la esquina ¿Ir al centro (1) o seguir por el borde (2)?: ");
                    while (!option.equals("1") && !option.equals("2")) {
                        option = Easymeth.getString("Entrada inválida. Elige 1 (centro) o 2 (borde): ");
                    }

                    if (option.equals("1")) {
                        piece.setMatrix(new MatrixCenter());

                    } else {
                        if (positionX == 0) {
                            positionX = piece.getMatrix().getBoard()[0].length - 1; // rebote al otro lado
                        } else {
                            positionX++;
                        }
                        positionY = 0;
                    }
                } else {
                    positionY++;
                }
            } else {
                if (positionY == piece.getMatrix().getBoard().length - 1) {

                    for (int i = 0; i < piece.getMatrix().getBoard().length; i++) {
                        System.out.println(i+1 + ") " + piece.getMatrix().getBoard()[4][i].getNumber());
                    }
                    int option = Easymeth.getInt("Llegaste al final del centro, elige direccion: ");
                    while (option<1 && option>6) {

                        for (int i = 0; i < piece.getMatrix().getBoard().length; i++) {
                            System.out.println(i+1 + ") " + piece.getMatrix().getBoard()[4][i].getNumber());
                        }
                        option = Easymeth.getInt("Entrada inválida, seleccione una buena.");
                    }
                    positionX= option-1;
                    piece.setPositionX(positionX);
                    piece.setPositionY(positionY);
                    backMovBoard(piece,moves);
                    return;

                } else {
                    positionY++;
                }
            }

            moves--;
        }

        piece.setPositionX(positionX);
        piece.setPositionY(positionY);
    }

    public static void selectMovement(Piece piece, int moves) {
        int posX = piece.getPositionX();
        int posY = piece.getPositionY();
        Box currentBox = piece.getMatrix().getBoard()[posY][posX];

        System.out.println("Estás en la casilla: " + currentBox.getNumber());
        if (currentBox.getNumber() == 0){
            frontMovBoard(piece,moves);
        }else {

            String option = Easymeth.getString("¿Deseas avanzar (1) o retroceder (2)?: ");
            while (!option.equals("1") && !option.equals("2")) {
                option = Easymeth.getString("Entrada inválida. Elige 1 para avanzar o 2 para retroceder: ");
            }

            if (option.equals("1")) {
                frontMovBoard(piece, moves);
            } else {
                backMovBoard(piece, moves);
            }
        }
        // Mostrar nueva posición
        posX = piece.getPositionX();
        posY = piece.getPositionY();
        Box newBox = piece.getMatrix().getBoard()[posY][posX];
        System.out.println("Ahora estás en la casilla: " + newBox.getNumber());
    }

}
