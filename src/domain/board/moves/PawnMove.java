package domain.board.moves;

import domain.board.Board;
import domain.pieces.Piece;

public class PawnMove extends Move {
    public PawnMove(Board board, Piece movedPiece, int destinationMovedPiece) {
        super(board, movedPiece, destinationMovedPiece);
    }
}
