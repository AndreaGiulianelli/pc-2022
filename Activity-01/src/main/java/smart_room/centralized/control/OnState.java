package smart_room.centralized.control;

import org.apache.commons.math3.analysis.function.Sin;
import smart_room.Event;
import smart_room.centralized.LightLevelChanged;
import smart_room.centralized.PresenceDetected;
import smart_room.centralized.PresenceNoMoreDetected;
import smart_room.centralized.SinglelBoardSimulator;

public class OnState implements FsmState {
    private double currentLuminosity;
    private boolean currentPresence;
    private SinglelBoardSimulator board;

    public OnState(final SinglelBoardSimulator board) {
        this.board = board;
        this.currentPresence = this.board.presenceDetected();
        this.currentLuminosity = this.board.getLuminosity();
        this.board.on(); // not optimal for the control flow.
    }

    @Override
    public FsmState dispatch(final Event event) {
        if(event instanceof PresenceDetected) {
            currentPresence = true;
        } else if(event instanceof PresenceNoMoreDetected) {
            currentPresence = false;
        } else if(event instanceof LightLevelChanged) {
            currentLuminosity = ((LightLevelChanged) event).getNewLevel();
        }
        return checkState();
    }

    private FsmState checkState() {
        if(!currentPresence || currentLuminosity >= ControllerImpl.LUMINOSITY_THREESHOLD) {
            return new OffState(this.board);
        }
        return this;
    }
}
