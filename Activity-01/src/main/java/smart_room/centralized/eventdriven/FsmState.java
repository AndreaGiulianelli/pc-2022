package smart_room.centralized.eventdriven;

import smart_room.Event;

public interface FsmState {
    FsmState dispatch(Event event);
}
