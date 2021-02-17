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

    Piece(PieceType pieceType, int piecePos, Color pieceColor){
        this.pieceColor = pieceColor;
        this.piecePos = piecePos;
        this.pieceType = pieceType;

        this.isFirstMove = false;
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
}
