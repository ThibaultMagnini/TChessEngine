package domain.player;

import domain.board.moves.Move;

public enum MoveStatus {

    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    },

    ILLEGAL_MOVE {
        @Override
        boolean isDone() {
            return false;
        }
    },

    LEAVES_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }
    };

    abstract boolean isDone();
}
