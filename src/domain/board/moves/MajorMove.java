package domain.board.moves;

import domain.board.Board;
import domain.pieces.Piece;

public class MajorMove extends Move {

    public MajorMove(Board board, Piece movedPiece, int destinationMovedPiece) {
        super(board, movedPiece, destinationMovedPiece);
    }

}
