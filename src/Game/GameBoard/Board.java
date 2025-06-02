package Game.GameBoard;

import static Utilies.ClearTerminal.clearScreen;

public class Board {

    private static final int SIZE_X = 60;  // Smaller size for X
    private static final int SIZE_Y = 30;  // Smaller size for Y
    private static String[][] boardMatrix;
    private static int counter = 1;

    private static final int TOTAL_SQUARES = 72;
    private static final int TOTAL_COLORS = 6;
    private static int[] colorCounter = new int[TOTAL_COLORS];

    private static final String[] COLORS = {
            "\033[38;5;208m", // orange
            "\033[32m",       // green
            "\033[34m",       // blue
            "\033[38;5;206m", // pink
            "\033[33m",       // yellow
            "\033[35m"        // purple
    };
    private static final String CENTER_COLOR = "\033[97m";
    private static final String RED_COLOR = "\033[31m";
    private static final String RESET = "\033[0m";

    public static final int[] SPECIAL_SQUARES = {
            3, 10, 17, 24, 31, 38, 6, 13, 20, 27, 34, 41
    };

    public static void printBoard() {
        clearScreen();
        counter = 0;
        boardMatrix = new String[SIZE_Y][SIZE_X];
        initializeMatrix();
        drawHexagonWithInternalLines();
        printMatrix();

    }

    private static void initializeMatrix() {
        for (int y = 0; y < SIZE_Y; y++)
            for (int x = 0; x < SIZE_X; x++)
                boardMatrix[y][x] = "  ";
        for (int i = 0; i < TOTAL_COLORS; i++)
            colorCounter[i] = 0;
    }

    private static void drawHexagonWithInternalLines() {
        int centerX = SIZE_X / 2;
        int centerY = SIZE_Y / 2;

        int dx = 4;
        int dy = 2;

        int[][] vertices = new int[6][2];
        vertices[0] = new int[]{centerX, centerY - 4 * dy};
        vertices[1] = new int[]{centerX + 4 * dx, centerY - 2 * dy};
        vertices[2] = new int[]{centerX + 4 * dx, centerY + 2 * dy};
        vertices[3] = new int[]{centerX, centerY + 4 * dy};
        vertices[4] = new int[]{centerX - 4 * dx, centerY + 2 * dy};
        vertices[5] = new int[]{centerX - 4 * dx, centerY - 2 * dy};

        for (int i = 0; i < 6; i++)
            placeNumber(vertices[i][0], vertices[i][1]);

        for (int i = 0; i < 6; i++)
            placePointsBetween(vertices[i], vertices[(i + 1) % 6], 6);

        placeLine(vertices[0], vertices[3], 10); // vertical
        placeLine(vertices[1], vertices[4], 10); // diagonal left
        placeLine(vertices[2], vertices[5], 10); // diagonal right

        placeCenterPoint(centerX, centerY);
    }

    private static void placePointsBetween(int[] a, int[] b, int quantity) {
        int dx = b[0] - a[0];
        int dy = b[1] - a[1];

        for (int i = 1; i <= quantity; i++) {
            if (counter > TOTAL_SQUARES) return;
            double t = i / (double)(quantity + 1);
            int x = (int)Math.round(a[0] + t * dx);
            int y = (int)Math.round(a[1] + t * dy);
            placeNumber(x, y);
        }
    }

    private static void placeLine(int[] a, int[] b, int points) {
        int dx = b[0] - a[0];
        int dy = b[1] - a[1];

        for (int i = 1; i <= points; i++) {
            if (counter > TOTAL_SQUARES) return;
            double t = i / (double)(points + 1);
            int x = (int)Math.round(a[0] + t * dx);
            int y = (int)Math.round(a[1] + t * dy);
            placeNumber(x, y);
        }
    }

    private static void placeCenterPoint(int x, int y) {
        boardMatrix[y][x] = CENTER_COLOR + "C" + RESET;
    }

    private static void placeNumber(int x, int y) {
        if (counter > TOTAL_SQUARES || y < 0 || y >= SIZE_Y || x < 0 || x >= SIZE_X) return;

        String num = String.format("%02d", counter);
        String color;

        if (isSpecialSquare(counter)) {
            color = RED_COLOR;
        } else {
            int colorIdx = findLeastUsedColor();
            color = COLORS[colorIdx];
            colorCounter[colorIdx]++;
        }

        boardMatrix[y][x] = color + num + RESET;
        counter++;
    }

    public static boolean isSpecialSquare(int num) {
        for (int val : SPECIAL_SQUARES)
            if (val == num) return true;
        return false;
    }

    private static int findLeastUsedColor() {
        int minIdx = 0;
        for (int i = 1; i < TOTAL_COLORS; i++)
            if (colorCounter[i] < colorCounter[minIdx])
                minIdx = i;
        return minIdx;
    }

    private static void printMatrix() {
        for (int y = 0; y < SIZE_Y; y++) {
            boolean hasContent = false;
            for (String s : boardMatrix[y])
                if (!s.equals("  ")) {
                    hasContent = true;
                    break;
                }
            if (hasContent) {
                for (String s : boardMatrix[y])
                    System.out.print(s);
                System.out.println();
            }
        }
    }
}
