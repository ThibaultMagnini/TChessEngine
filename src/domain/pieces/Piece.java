package domain.pieces;

import domain.Color;
import domain.board.Board;
import domain.board.moves.Move;

import java.util.List;

public abstract class Piece {

    protected final int piecePos;
    protected final Color pieceColor;

    Piece(int piecePos, Color pieceColor){
        this.pieceColor = pieceColor;
        this.piecePos = piecePos;
    }

    public abstract List<Move> legalMoves(Board board);

    public Color getPieceColor() {
        return this.pieceColor;
    }


}
