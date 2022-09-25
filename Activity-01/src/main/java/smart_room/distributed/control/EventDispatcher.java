package smart_room.distributed.control;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import smart_room.Controller;
import smart_room.Event;
import smart_room.distributed.LightLevelChanged;
import smart_room.distributed.PresenceDetected;
import smart_room.distributed.PresenceNoMoreDetected;

public class EventDispatcher implements Controller {
    private final String LUMINOSITY_EV_ADDRESS = "luminosity";
    private final String PRESENCE_EV_ADDRESS = "presence";
    private final EventBus eventBus;

    public EventDispatcher(final Vertx vertx) {
        this.eventBus = vertx.eventBus();
    }

    @Override
    public void notifyEvent(Event ev) {
        if (ev instanceof PresenceDetected) {
            this.eventBus.publish(PRESENCE_EV_ADDRESS, String.valueOf(true));
        } else if (ev instanceof PresenceNoMoreDetected) {
            this.eventBus.publish(PRESENCE_EV_ADDRESS, String.valueOf(false));
        } else if (ev instanceof LightLevelChanged) {
            this.eventBus.publish(LUMINOSITY_EV_ADDRESS, String.valueOf(((LightLevelChanged) ev).getNewLevel()));
        }
    }
}