package domain.board;

import domain.pieces.Piece;

public class EmptyTile extends Tile{

    EmptyTile(int tileNumber){
        super(tileNumber);
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
