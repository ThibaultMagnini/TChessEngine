package domain.pieces;

import com.google.common.collect.ImmutableList;
import domain.Color;
import domain.board.Board;
import domain.board.BoardUtils;
import domain.board.moves.AttackMove;
import domain.board.moves.MajorMove;
import domain.board.moves.Move;
import domain.board.moves.PawnMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private int[] POSSIBLE_MOVES_OPTIONS = {8, 16, 7, 9};

    public Pawn(Color pieceColor, int piecePos) {
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
                    ((BoardUtils.SECOND_ROW[this.piecePos] && this.pieceColor.isBlack()) ||
                            (BoardUtils.SEVENTH_ROW[this.piecePos] && this.pieceColor.isWhite())))
            {
                int behindDestination = this.piecePos + (this.pieceColor.getDirection() * 8);
                if (board.getTile(behindDestination).isOccupied() && !board.getTile(possibleDestination).isOccupied()){
                    legalMoves.add(new MajorMove(board, this, possibleDestination));
                }
            }
            else if (possibleOffset == 7 && !((BoardUtils.EIGHT_COLUMN[this.piecePos] && this.pieceColor.isWhite())
                        || (BoardUtils.FIRST_COLUMN[this.piecePos] && this.pieceColor.isBlack())))
            {
                if (board.getTile(possibleDestination).isOccupied()){
                    Piece pieceOnDes = board.getTile(possibleDestination).getPiece();
                    if (pieceOnDes.getPieceColor() != this.pieceColor){
                        legalMoves.add(new AttackMove(board, this, possibleDestination, pieceOnDes));
                    }
                }
            }
            else if (possibleOffset == 9 && !((BoardUtils.EIGHT_COLUMN[this.piecePos] && this.pieceColor.isBlack())
                        || BoardUtils.FIRST_COLUMN[this.piecePos] && this.pieceColor.isWhite()))
            {
                if (board.getTile(possibleDestination).isOccupied()){
                    Piece pieceOnDes = board.getTile(possibleDestination).getPiece();
                    if (pieceOnDes.getPieceColor() != this.pieceColor){
                        legalMoves.add(new AttackMove(board, this, possibleDestination, pieceOnDes));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return "♙";
    }
}
