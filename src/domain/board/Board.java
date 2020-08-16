package domain.board;

import com.google.common.collect.ImmutableList;
import domain.Color;
import domain.board.moves.Move;
import domain.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Tile> board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public Board(Builder builder){
        this.board = createBoard(builder);
        this.whitePieces = calculateActivePieces(this.board, Color.WHITE);
        this.blackPieces = calculateActivePieces(this.board, Color.BLACK);

        List<Move> whiteLegalMoves = calculateLegalMoves(this.whitePieces);
        List<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);
    }

    private List<Move> calculateLegalMoves(List<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        for (Piece piece : pieces){
            moves.addAll(piece.legalMoves(this));
        }
        return moves;
    }

    private List<Piece> calculateActivePieces(List<Tile> board, Color color) {
        List<Piece> active = new ArrayList<>();

        for (Tile tile : board){
            if (tile.isOccupied()){
                Piece piece = tile.getPiece();
                if (piece.getPieceColor() == color){
                    active.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(active);
    }

    private List<Tile> createBoard(Builder builder) {
        Tile[] tiles = new Tile[64];
        for (int i = 0; i < 64; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board setUpBoard(){
        final Builder builder = new Builder();

        builder.setPiece(new Rook(Color.BLACK, 0));
        builder.setPiece(new Knight(Color.BLACK, 1));
        builder.setPiece(new Bishop(Color.BLACK, 2));
        builder.setPiece(new Queen(Color.BLACK, 3));
        builder.setPiece(new King(Color.BLACK, 4));
        builder.setPiece(new Bishop(Color.BLACK, 5));
        builder.setPiece(new Knight(Color.BLACK, 6));
        builder.setPiece(new Rook(Color.BLACK, 7));
        builder.setPiece(new Pawn(Color.BLACK, 8));
        builder.setPiece(new Pawn(Color.BLACK, 9));
        builder.setPiece(new Pawn(Color.BLACK, 10));
        builder.setPiece(new Pawn(Color.BLACK, 11));
        builder.setPiece(new Pawn(Color.BLACK, 12));
        builder.setPiece(new Pawn(Color.BLACK, 13));
        builder.setPiece(new Pawn(Color.BLACK, 14));
        builder.setPiece(new Pawn(Color.BLACK, 15));

        builder.setPiece(new Pawn(Color.WHITE, 48));
        builder.setPiece(new Pawn(Color.WHITE, 49));
        builder.setPiece(new Pawn(Color.WHITE, 50));
        builder.setPiece(new Pawn(Color.WHITE, 51));
        builder.setPiece(new Pawn(Color.WHITE, 52));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        builder.setPiece(new Pawn(Color.WHITE, 54));
        builder.setPiece(new Pawn(Color.WHITE, 55));
        builder.setPiece(new Rook(Color.WHITE, 56));
        builder.setPiece(new Knight(Color.WHITE, 57));
        builder.setPiece(new Bishop(Color.WHITE, 58));
        builder.setPiece(new Queen(Color.WHITE, 59));
        builder.setPiece(new King(Color.WHITE, 60));
        builder.setPiece(new Bishop(Color.WHITE, 61));
        builder.setPiece(new Knight(Color.WHITE, 62));
        builder.setPiece(new Rook(Color.WHITE, 63));

        builder.setMoveMaker(Color.WHITE);

        return builder.build();
    }

    public Tile getTile(int tilePos){
        return board.get(tilePos);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            String tileText = print(this.board.get(i));
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % 8 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String print(Tile tile) {
        if (tile.isOccupied()) {
            return tile.getPiece().getPieceColor().isBlack() ? tile.getPiece().toString() : tile.getPiece().toString();
        } else {
            return tile.toString();
        }
    }

}
