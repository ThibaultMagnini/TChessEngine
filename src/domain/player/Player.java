package domain.player;

import domain.Color;
import domain.board.Board;
import domain.board.moves.Move;
import domain.pieces.King;
import domain.pieces.Piece;

import java.util.Collection;

public abstract class Player
{

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    public Player(Board board, Collection<Move> playerLegalMoves, Collection<Move> opponentPlayerLegalMoves) {
        this.board = board;
        this.playerKing = getKingPosition();
        this.legalMoves = playerLegalMoves;
    }

    private King getKingPosition() {
        for (Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("No king on the board! Board not valid");
    }

    public boolean isMoveLegal(Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return false;
    }

    public boolean isICheckMate() {
        return false;
    }

    public boolean isInStaleMate() {
        return false;
    }

    public boolean isCastled() {
        return false;
    }

    public MoveTransition makeMove(Move move){
        return null;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Color getColor();
    public abstract Player getOpponent();

}
