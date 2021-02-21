package domain.pieces;

import domain.Color;
import domain.board.Board;
import domain.board.moves.Move;
import javafx.scene.chart.PieChart;

import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePos;
    protected final Color pieceColor;
    protected final boolean isFirstMove;
    private final int cachedHashCode;



    Piece(PieceType pieceType, int piecePos, Color pieceColor){
        this.pieceColor = pieceColor;
        this.piecePos = piecePos;
        this.pieceType = pieceType;

        this.isFirstMove = false;
        this.cachedHashCode = computeHashCode();
    }

    public abstract List<Move> legalMoves(Board board);

    public Color getPieceColor() {
        return this.pieceColor;
    }

    protected boolean isFirstMove() {
        return this.isFirstMove;
    }

    public int getPiecePosition() {
        return this.piecePos;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract Piece movePiece(Move move);

    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceColor.hashCode();
        result = 31 * result + piecePos;
        result = 31 * result + (isFirstMove? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece otherPiece = (Piece) obj;
        return piecePos == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() && pieceColor == otherPiece.getPieceColor() && isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return  this.cachedHashCode;
    }
}
