package domain.board;

import com.google.common.collect.ImmutableMap;
import domain.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileNumber;

    private static final Map<Integer, EmptyTile> EMPTY_TILE_MAP = createAllEmptyTiles();


    private static Map<Integer, EmptyTile> createAllEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 64; i++ )
            emptyTileMap.put(i, new EmptyTile(i));

        return ImmutableMap.copyOf(emptyTileMap);

    }

    public static Tile createTile(int tileNumber, Piece piece){
        if (piece != null)
            return new OccupiedTile(tileNumber, piece);
        else
            return EMPTY_TILE_MAP.get(tileNumber);
    }

    Tile(int tileNumber){
        this.tileNumber = tileNumber;
    }


    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    @Override
    public String toString() {
        return "  - ";
    }
}
