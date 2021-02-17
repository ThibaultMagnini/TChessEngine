package domain.player;

import domain.Color;
import domain.board.Board;
import domain.board.moves.Move;
import domain.pieces.Piece;

import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, List<Move> whiteLegalMoves, List<Move> blackLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
}
