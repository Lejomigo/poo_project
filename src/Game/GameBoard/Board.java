package Game.GameBoard;

import Configuration.Questions.CategoryColors;
import Game.GameBoard.Box.Box;
import Game.GameBoard.Matrix.MatrixBoard;
import Game.GameBoard.Matrix.MatrixCenter;

import java.util.*;

import static Configuration.Questions.QuestionUpdates.colors;

public class Board {

    public static void imprimirTableroCompleto(Box[][] matrixBoard, Box[][] matrixCenter) {
        // Crear colores ANSI
        Map<String, String> colorMap = new HashMap<>();
        colorMap.put("Azul", "\u001B[34m");
        colorMap.put("Amarillo", "\u001B[33m");
        colorMap.put("Naranja", "\u001B[38;5;208m");
        colorMap.put("Verde", "\u001B[32m");
        colorMap.put("Morado", "\u001B[35m");
        colorMap.put("Rosado", "\u001B[95m");
        colorMap.put("Blanco", "\u001B[97m");
        colorMap.put("Reset", "\u001B[0m");

        // Crear tablero 13x13 vacío
        String[][] tablero = new String[13][13];
        for (String[] fila : tablero) Arrays.fill(fila, "  ");

        // Posiciones del borde (42 casillas)
        int[][] posicionesBorde = {
                {6, 0}, {7, 0}, {8, 1}, {9, 2}, {10, 3}, {11, 4}, {12, 5},
                {12, 6}, {12, 7}, {11, 8}, {10, 9}, {9, 10}, {8, 11},
                {7, 12}, {6, 12}, {5, 11}, {4, 10}, {3, 9}, {2, 8},
                {1, 7}, {0, 6}, {0, 5}, {1, 4}, {2, 3}, {3, 2}, {4, 1}, {5, 0},
                {6, 1}, {7, 1}, {8, 2}, {9, 3}, {10, 4}, {11, 5},
                {11, 6}, {11, 7}, {10, 8}, {9, 9}, {8, 10}, {7, 11},
                {6, 11}, {5, 10}, {4, 9}, {3, 8}
        };

        // Pintar borde
        int count = 0;
        for (int fila = 0; fila < 7; fila++) {
            for (int col = 0; col < 6; col++) {
                Box box = matrixBoard[fila][col];
                int[] pos = posicionesBorde[count++];
                int x = pos[0], y = pos[1];
                tablero[y][x] = formatBox(box, colorMap);
            }
        }

        // Pintar centro: desde fila,columna (3,3) hasta (8,8)
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col < 6; col++) {
                int x = col + 3;
                int y = fila + 3;
                Box box = matrixCenter[fila][col];

                // si es especial (color null)
                if (box.getColor() == null) {
                    tablero[y][x] = colorMap.get("Blanco") + String.format("%02d", box.getNumber()) + colorMap.get("Reset");
                } else {
                    tablero[y][x] = formatBox(box, colorMap);
                }

                // marcar centro
                if (fila == 5) {
                    tablero[y][x] = colorMap.get("Blanco") + "C " + colorMap.get("Reset");
                }
            }
        }

        // Imprimir tablero final
        for (String[] fila : tablero) {
            for (String celda : fila) System.out.print(celda + " ");
            System.out.println();
        }
    }


    public static String formatBox(Box box, Map<String, String> colorMap) {
        if (box == null || box.getColor() == null) {
            return colorMap.get("Blanco") + String.format("%02d", box != null ? box.getNumber() : 0) + colorMap.get("Reset");
        }
        String color = box.getColor().getColor();
        String ansi = colorMap.getOrDefault(color, colorMap.get("Reset"));
        return ansi + String.format("%02d", box.getNumber()) + colorMap.get("Reset");
    }




    public static void main(String[] args) {
        imprimirTableroCompleto(new MatrixBoard().getBoard(),new MatrixCenter().getBoard());
    }
}

