package domain.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initCol(0);
    public static final boolean[] SECOND_COLUMN = initCol(1);
    public static final boolean[] EIGHT_COLUMN = initCol(6);
    public static final boolean[] SEVENTH_COLUMN = initCol(7);

    public static final boolean[] SECOND_ROW = {};
    public static final boolean[] SEVENTH_ROW = {};


    public static boolean isValidPosition(int tilePos) {
        return tilePos < 64 && tilePos >= 0;
    }

    private static boolean[] initCol(int colNumber) {
        final boolean[] grid = new boolean[64];

        while (colNumber < 64) {
            grid[colNumber] = true;
            colNumber += 8;
        }
        return grid;

    }


    private static boolean[] initRow(int rowNumber){
        final boolean[] grid = new boolean[64];

        if (rowNumber == 1) {
            for (int i = 0; i < 64; i++) {
                if (i == 8) {
                    for (int j = 0; j < 8; j++)
                        grid[i + j] = true;
                }
            }
        }

        if (rowNumber == 6) {
            for (int i = 0; i < 64; i++) {
                if (i == 47) {
                    for (int j = 0; j < 8; j++)
                        grid[i + j] = true;
                }
            }
        }
        return grid;
    }
}
