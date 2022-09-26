package smart_room.centralized.superloop;

import smart_room.centralized.SinglelBoardSimulator;

public class OffState implements FsmSpState{

    private final SinglelBoardSimulator board;
    private double currentLuminosity;
    private boolean currentPresence;
    private boolean nextCommand;

    public OffState(final SinglelBoardSimulator board) {
        this.board = board;
        this.board.off();
    }

    @Override
    public void sense() {
        this.currentPresence = this.board.presenceDetected();
        this.currentLuminosity = this.board.getLuminosity();
    }

    @Override
    public void decide() {
        this.nextCommand = currentPresence && currentLuminosity < SuperLoopController.LUMINOSITY_THREESHOLD;
    }

    @Override
    public FsmSpState act() {
        if(this.nextCommand) {
            return new OnState(this.board);
        }
        return this;
    }
}
