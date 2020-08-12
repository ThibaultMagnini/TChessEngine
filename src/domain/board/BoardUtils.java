package domain.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initCol(0);
    public static final boolean[] SECOND_COLUMN = initCol(1);
    public static final boolean[] EIGHT_COLUMN = initCol(6);
    public static final boolean[] SEVENTH_COLUMN = initCol(7);


    public static boolean isValidPosition(int tilePos) {
        return tilePos < 64 && tilePos >= 0;
    }

    private static boolean[] initCol(int colNumber) {
        final boolean[] column = new boolean[64];

        while (colNumber < 64) {
            column[colNumber] = true;
            colNumber += 8;
        }
        return column;

    }
}
