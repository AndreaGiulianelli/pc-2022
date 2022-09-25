package smart_room.distributed.control;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.messages.MqttConnAckMessage;
import smart_room.distributed.LightDeviceSimulator;

public class LightControl extends AbstractVerticle {
    private static final double LUMINOSITY_THREESHOLD = 0.3;
    private static final String SMART_ROOM_MQTT_TOPIC = "smartroom/+";
    private final LightDeviceSimulator light;
    private double currentLuminosity;
    private boolean currentPresence;

    public LightControl(final LightDeviceSimulator light) {
        this.light = light;
    }

    @Override
    public void start() {
        final MqttClient mqttClient = MqttClient.create(this.getVertx());
        final Future<MqttConnAckMessage> connection = mqttClient.connect(1884, "localhost");
        connection.onComplete(h -> {
            if (h.succeeded()) {
               mqttClient.publishHandler(s -> {
                   final String type = s.topicName().split("/")[1];
                   if(type.equals("luminosity")) {
                       this.currentLuminosity = Double.parseDouble(s.payload().toString());
                       this.checkLight();
                   } else if(type.equals("presence")) {
                       this.currentPresence = Boolean.parseBoolean(s.payload().toString());
                       this.checkLight();
                   }
               }).subscribe(SMART_ROOM_MQTT_TOPIC, 2);
            } else {
               System.out.println("MQTT connection failed");
            }
        });
    }

    private void checkLight() {
        if(currentPresence && currentLuminosity < LUMINOSITY_THREESHOLD) {
            this.light.on();
        } else {
            this.light.off();
        }
    }
}
