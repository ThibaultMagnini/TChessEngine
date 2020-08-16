package domain;

import domain.board.Board;
import domain.board.Builder;

public class TChessApp {

    public static void main(String[] args) {
        Board board = Board.setUpBoard();

        System.out.println(board.toString());
    }
}
