package domain.board;

import com.google.common.collect.ImmutableList;

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



    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[64];
        for(int i = 0; i < row.length; i++) {
            row[i] = false;
        }
        while(rowNumber % 8 != 0) {
            row[rowNumber] = true;
            rowNumber++;
        }
        return row;
    }
}
