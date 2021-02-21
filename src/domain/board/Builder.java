package domain.board;

import domain.Color;
import domain.pieces.Piece;

import java.lang.management.PlatformLoggingMXBean;
import java.util.HashMap;
import java.util.Map;

public class Builder {

    Map<Integer, Piece> boardConfig;
    Color nextUp;

    public Builder(){
        this.boardConfig = new HashMap<>();
    }

    public Builder setPiece(Piece piece){
        this.boardConfig.put(piece.getPiecePosition(), piece);
        return this;
    }

    public Builder setMoveMaker(Color color){
        this.nextUp = color;
        return this;
    }

    public Board build() {
        return new Board(this);
    }

}
