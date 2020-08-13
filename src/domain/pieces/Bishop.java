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

public class Bishop extends Piece{

    private int[] POSSIBLE_MOVES_OPTIONS = {-9, -7, 7, 9};

    Bishop(int piecePos, Color color) { super(piecePos, color); }

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
        return BoardUtils.FIRST_COLUMN[currentPos] && (possibleOffset == -9 || possibleOffset == 7);
    }

    private boolean isEightColumnBoundary(int currentPos, int possibleOffset) {
        return BoardUtils.FIRST_COLUMN[currentPos] && (possibleOffset == 9 || possibleOffset == -7);
    }
}