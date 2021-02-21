package domain.player;

import com.google.common.collect.ImmutableList;
import domain.Color;
import domain.board.Board;
import domain.board.moves.Move;
import domain.pieces.King;
import domain.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player
{

    protected final Board board;
    private final King playerKing;
    private final Collection<Move> legalMoves;
    private final boolean isInCheck;

    Player(Board board, Collection<Move> playerLegalMoves, Collection<Move> opponentPlayerLegalMoves) {
        this.board = board;
        this.playerKing = getKing();
        this.legalMoves = playerLegalMoves;
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentPlayerLegalMoves).isEmpty();
    }

    private static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> opponentPlayerLegalMoves) {
        final List<Move> attackMoves = new ArrayList<>();

        for (Move m: opponentPlayerLegalMoves) {
            if (piecePosition == m.getDestinationCoordinate()) {
                attackMoves.add(m);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    public King getPlayerKing() {
        return playerKing;
    }

    private King getKing() {
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

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }

    public boolean isICheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    private boolean hasEscapeMoves() {
        for (Move m : legalMoves) {
            MoveTransition transition = makeMove(m);
            if (transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    public boolean isCastled() {
        return false;
    }

    public MoveTransition makeMove(Move move){

        if (!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }

        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.getCurrentPlayer().getOpponent().getPlayerKing().getPiecePosition(), transitionBoard.getCurrentPlayer().getLegalMoves());

        if (!kingAttacks.isEmpty()){
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }

        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Color getColor();
    public abstract Player getOpponent();

}
