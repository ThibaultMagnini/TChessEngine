package domain.player;

import domain.board.Board;
import domain.board.moves.Move;
import javafx.scene.shape.MoveTo;

public class MoveTransition {

    private Board transitionBoard;
    private Move move;
    private MoveStatus moveStatus;

    public MoveTransition(Board transitionBoard, Move move, MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }
}
