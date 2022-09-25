package smart_room.distributed.control;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.messages.MqttConnAckMessage;

public class LuminositySensorControl extends AbstractVerticle {
    public final String LUMINOSITY_MQTT_ADDRESS = "smartroom/luminosity";
    private final String LUMINOSITY_EV_ADDRESS = "luminosity";
    private EventBus eventBus;

    @Override
    public void start() {
        final MqttClient mqttClient = MqttClient.create(this.getVertx());
        this.eventBus = this.getVertx().eventBus();

        final Future<MqttConnAckMessage> connection = mqttClient.connect(1884, "localhost");
        connection.onComplete(h -> {
            if(h.succeeded()) {
                this.eventBus.consumer(LUMINOSITY_EV_ADDRESS, message ->
                    mqttClient.publish(LUMINOSITY_MQTT_ADDRESS,
                            Buffer.buffer(message.body().toString()),
                            MqttQoS.EXACTLY_ONCE,
                            false,
                            false)
                );
            } else {
                System.out.println("ERROR - mqtt failed to connect");
            }
        });
    }
}
