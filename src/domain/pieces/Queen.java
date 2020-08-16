package domain.pieces;

import com.google.common.collect.ImmutableList;
import domain.Color;
import domain.board.Board;
import domain.board.BoardUtils;
import domain.board.Tile;
import domain.board.moves.AttackMove;
import domain.board.moves.MajorMove;
import domain.board.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private int[] POSSIBLE_MOVES_OPTIONS = {-8, 1, -1, 8, -9, -7, 7, 9};

    public Queen(Color pieceColor, int piecePos) {
        super(piecePos, pieceColor);
    }

    @Override
    public List<Move> legalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for (int possiblePositionOffset : POSSIBLE_MOVES_OPTIONS){
            int possiblePieceDestination = this.piecePos;

            while(BoardUtils.isValidPosition(possiblePieceDestination)){

                if (isFirstColumnBoundary(possiblePieceDestination, possiblePositionOffset) || isEightColumnBoundary(possiblePieceDestination, possiblePositionOffset))
                    break;

                possiblePieceDestination += possiblePositionOffset;

                if (BoardUtils.isValidPosition(possiblePieceDestination)){
                    Tile possibleDestination = board.getTile(possiblePieceDestination);
                    if (!possibleDestination.isOccupied()){
                        legalMoves.add(new MajorMove(board, this, possiblePieceDestination));
                    } else {
                        Piece pieceAtDestination = possibleDestination.getPiece();
                        Color color = pieceAtDestination.getPieceColor();

                        if (this.pieceColor != color) {
                            legalMoves.add(new AttackMove(board, this, possiblePieceDestination, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }


    private boolean isFirstColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.FIRST_COLUMN[currentPos] && (possibleOffset == -9 || possibleOffset == 7 || possibleOffset == -1);
    }

    private boolean isEightColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.FIRST_COLUMN[currentPos] && (possibleOffset == 9 || possibleOffset == -7 || possibleOffset == 1);
    }

    @Override
    public String toString() {
        return "♕";
    }
}
