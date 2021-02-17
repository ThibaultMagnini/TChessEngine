package domain.player;

import domain.Color;
import domain.board.Board;
import domain.board.moves.Move;
import domain.pieces.Piece;

import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board, List<Move> whiteLegalMoves, List<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }
}
