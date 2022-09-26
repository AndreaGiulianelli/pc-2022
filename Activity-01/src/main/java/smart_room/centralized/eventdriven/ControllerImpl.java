package smart_room.centralized.eventdriven;

import smart_room.Controller;
import smart_room.Event;
import smart_room.centralized.SinglelBoardSimulator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ControllerImpl extends Thread implements Controller {
    public static final double LUMINOSITY_THREESHOLD = 0.3;
    private final BlockingQueue<Event> queue;
    private FsmState currentState;

    public ControllerImpl(final SinglelBoardSimulator board) {
        this.queue = new LinkedBlockingQueue<>();
        // initialize the state
        this.currentState = new OffState(board);
    }

    @Override
    public void run() {
        //model event-loop
        while(true) {
            try {
                // wait event
                final Event event = this.queue.take();
                // give the event to the state
                this.currentState = this.currentState.dispatch(event);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void notifyEvent(Event ev) {
        this.queue.add(ev);
    }
}
