package domain.pieces;

import domain.Color;
import domain.board.Board;
import domain.board.BoardUtils;
import domain.board.moves.Move;
import domain.board.moves.PawnMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private int[] POSSIBLE_MOVES_OPTIONS = {8, 16};

    Pawn(int piecePos, Color pieceColor) {
        super(piecePos, pieceColor);
    }

    @Override
    public List<Move> legalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for (int possibleOffset: POSSIBLE_MOVES_OPTIONS){
            int possibleDestination = this.piecePos + (possibleOffset * this.getPieceColor().getDirection());

            if (!BoardUtils.isValidPosition(possibleDestination)){
                continue;
            }

            if (possibleOffset == 8 && !board.getTile(possibleDestination).isOccupied()){
                legalMoves.add(new PawnMove(board, this, possibleDestination));
            }
            else if (possibleOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePos] && this.getPieceColor().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePos] && this.getPieceColor().isWhite())) {
                int behindDestination = this.piecePos + (this.pieceColor.getDirection() * 8);
                if (!BoardUtils.isValidPosition(behindDestination)){

                }
            }
        }

        return null;
    }
}
