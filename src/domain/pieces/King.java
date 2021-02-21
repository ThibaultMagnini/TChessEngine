package domain.pieces;

import com.google.common.collect.ImmutableList;
import domain.Color;
import domain.board.Board;
import domain.board.BoardUtils;
import domain.board.Tile;
import domain.board.moves.AttackMove;
import domain.board.moves.MajorMove;
import domain.board.moves.Move;

import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private int[] POSSIBLE_MOVES_OPTIONS = {-9, -7, 7, 9, 8, -8, -1, 1};

    public King(Color pieceColor, int piecePos) {
        super(PieceType.KING,piecePos, pieceColor);
    }

    @Override
    public List<Move> legalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();


        for (int possibleOffset : POSSIBLE_MOVES_OPTIONS) {
            int possiblePieceDestination;
            possiblePieceDestination = this.piecePos + possibleOffset;

            if (isFirstColumnBoundary(this.piecePos, possibleOffset) || isEightColumnBoundary(this.piecePos, possibleOffset)){
                continue;
            }

            if (BoardUtils.isValidPosition(possiblePieceDestination)){
                Tile possibleDesTile = board.getTile(possiblePieceDestination);

                if (!possibleDesTile.isOccupied()){
                    legalMoves.add(new MajorMove(board, this, possiblePieceDestination));
                } else {
                    Piece pieceAtDestination = possibleDesTile.getPiece();
                    Color color = pieceAtDestination.getPieceColor();

                    if (this.pieceColor != color) {
                        legalMoves.add(new AttackMove(board, this, possiblePieceDestination, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public King movePiece(Move move) {
        return new King(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    private boolean isFirstColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.FIRST_COLUMN[currentPos] && (possibleOffset == -9 || possibleOffset == -1 || possibleOffset == -7);
    }

    private boolean isEightColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.EIGHT_COLUMN[currentPos] && (possibleOffset == -7 || possibleOffset == 1 || possibleOffset == 9);
    }

    @Override
    public String toString() {
        return "♔";
    }
}
