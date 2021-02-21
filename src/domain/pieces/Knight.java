package domain.pieces;

import com.google.common.collect.ImmutableList;
import domain.Color;
import domain.board.Board;
import domain.board.BoardUtils;
import domain.board.moves.AttackMove;
import domain.board.moves.MajorMove;
import domain.board.moves.Move;
import domain.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private int[] POSSIBLE_MOVES_OPTIONS = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(Color color, int piecePos){
        super(PieceType.KNIGHT, piecePos, color);
    }

    @Override
    public List<Move> legalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for (int i = 0; i < POSSIBLE_MOVES_OPTIONS.length; i++){

            if (BoardUtils.isValidPosition(POSSIBLE_MOVES_OPTIONS[i] + this.piecePos)) {

                if (isFirstColumnBoundary(this.piecePos, POSSIBLE_MOVES_OPTIONS[i]) ||
                        isSecondColumnBoundary(this.piecePos, POSSIBLE_MOVES_OPTIONS[i]) ||
                        isSeventhColumnBoundary(this.piecePos, POSSIBLE_MOVES_OPTIONS[i]) ||
                        isEightColumnBoundary(this.piecePos, POSSIBLE_MOVES_OPTIONS[i]))  {
                    continue;
                }

                Tile possibleDestination = board.getTile(this.piecePos + POSSIBLE_MOVES_OPTIONS[i]);
                if (!possibleDestination.isOccupied()){
                    legalMoves.add(new MajorMove(board, this, this.piecePos + POSSIBLE_MOVES_OPTIONS[i]));
                } else {
                    Piece pieceAtDestination = possibleDestination.getPiece();
                    Color color = pieceAtDestination.getPieceColor();

                    if (this.pieceColor != color) {
                        legalMoves.add(new AttackMove(board, this, this.piecePos + POSSIBLE_MOVES_OPTIONS[i], pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Knight movePiece(Move move) {
        return new Knight(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    private boolean isFirstColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.FIRST_COLUMN[currentPos] && (possibleOffset == -17 || possibleOffset == -10 || possibleOffset == 6 || possibleOffset == 15);
    }

    private boolean isSecondColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.SECOND_COLUMN[currentPos] && (possibleOffset == -10 || possibleOffset == 6);
    }

    private boolean isSeventhColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPos] && (possibleOffset == 10 || possibleOffset == -6);
    }

    private boolean isEightColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.EIGHT_COLUMN[currentPos] && (possibleOffset == 17 || possibleOffset == -6 || possibleOffset == 10 || possibleOffset == -15);
    }

    @Override
    public String toString() {
        return "♘";
    }
}
