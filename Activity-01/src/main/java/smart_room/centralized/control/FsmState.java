package smart_room.centralized.control;

import smart_room.Event;

public interface FsmState {
    FsmState dispatch(Event event);
}
