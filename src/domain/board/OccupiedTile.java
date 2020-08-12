package domain.board;

import domain.pieces.Piece;

public class OccupiedTile extends Tile{

    private Piece piece;

    OccupiedTile(int tileNumber, Piece piece){
        super(tileNumber);
        this.piece = piece;
    }

    @Override
    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }
}
