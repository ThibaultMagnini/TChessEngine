package domain.board.moves;

import domain.board.Board;
import domain.pieces.Piece;

public class AttackMove extends Move {

    private Piece attackedPiece;

    public AttackMove(Board board, Piece movedPiece, int destinationMovedPiece, Piece attackedPiece) {
        super(board, movedPiece, destinationMovedPiece);
        this.attackedPiece = attackedPiece;
    }
}
