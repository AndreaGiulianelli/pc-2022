package smart_room.centralized.superloop;

import smart_room.centralized.SinglelBoardSimulator;

public class SuperLoopController extends Thread {
    public static final double LUMINOSITY_THREESHOLD = 0.3;
    private FsmSpState currentState;

    public SuperLoopController(final SinglelBoardSimulator board) {
        this.currentState = new OffState(board);
    }

    @Override
    public void run() {
        while(true) {
            this.currentState.sense();
            this.currentState.decide();
            this.currentState = this.currentState.act();
        }
    }
}
