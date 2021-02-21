package domain.board.moves;

import domain.board.Board;
import domain.pieces.Piece;

public abstract class Move {

    private final Board board;
    private final Piece movedPiece;
    private final int destinationMovedPiece;

    public Move(Board board, Piece movedPiece, int destinationMovedPiece) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationMovedPiece = destinationMovedPiece;
    }


    public int getDestinationCoordinate() {
        return this.destinationMovedPiece;
    }

    public Board execute(){
        return null;
    }
}
