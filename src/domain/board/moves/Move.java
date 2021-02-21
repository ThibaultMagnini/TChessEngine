package domain.board.moves;

import domain.board.Board;
import domain.board.Builder;
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

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public int getDestinationCoordinate() {
        return this.destinationMovedPiece;
    }

    public Board execute(){

        Builder builder = new Builder();

        for (final Piece p : this.board.getCurrentPlayer().getActivePieces()){
            if (!this.movedPiece.equals(p)){
                builder.setPiece(p);
            }
        }

        for (Piece p : this.board.getCurrentPlayer().getOpponent().getActivePieces()){
            builder.setPiece(p);
        }

        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getColor());

        Board res = builder.build();
        System.out.println(res.toString());
        return res;
    }

}
