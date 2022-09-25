package smart_room.distributed;

import io.vertx.core.Vertx;
import smart_room.distributed.control.EventDispatcher;
import smart_room.distributed.control.LightControl;
import smart_room.distributed.control.LuminositySensorControl;
import smart_room.distributed.control.PresenceControl;

public class Test {
    public static void main(final String... args) {
        final LuminositySensorSimulator luminositySensorSimulator = new LuminositySensorSimulator("lum");
        final PresDetectSensorSimulator presSimulator = new PresDetectSensorSimulator("pres");
        final LightDeviceSimulator lightDeviceSimulator = new LightDeviceSimulator("light");

        luminositySensorSimulator.init();
        presSimulator.init();
        lightDeviceSimulator.init();

        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new LuminositySensorControl());
        vertx.deployVerticle(new PresenceControl());
        vertx.deployVerticle(new LightControl(lightDeviceSimulator));

        final EventDispatcher eventDispatcher = new EventDispatcher(vertx);
        luminositySensorSimulator.register(eventDispatcher);
        presSimulator.register(eventDispatcher);

    }
}
