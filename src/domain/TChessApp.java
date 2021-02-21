package domain;

import domain.board.Board;
import domain.board.Builder;
import domain.board.moves.MajorMove;
import domain.board.moves.Move;
import domain.board.moves.PawnMove;
import domain.pieces.Pawn;
import domain.player.MoveTransition;

public class TChessApp {

    public static void main(String[] args) {
        Board board = Board.setUpBoard();

        System.out.println(board.whitePlayer().getLegalMoves().toString());

        board.whitePlayer().makeMove(new PawnMove(board, board.getTile(52).getPiece(), 36));

        System.out.println(board.toString());
    }
}
