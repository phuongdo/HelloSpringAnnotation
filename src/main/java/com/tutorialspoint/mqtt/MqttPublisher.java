package com.tutorialspoint.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by phuongdv on 5/20/17.
 * The MQ Telemetry Transport Protocol (MQTT) is a lightweight publish/subscribe messaging protocol
 */
public class MqttPublisher {

    public static void main(String[] args) throws Exception {

        // Creating a MQTT Client using Eclipse Paho
        String topic = "news";
        String content = "Visit www.hascode.com! :D";
        int qos = 2;
        String broker = "tcp://0.0.0.0:1883";
        String clientId = "paho-java-client-#123123";

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
//            connOpts.setWill("gone", "i'm die".getBytes(), 2, false);

            System.out.println("paho-client connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("paho-client connected to broker");
            System.out.println("paho-client publishing message: " + content);

            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);

//            message.setRetained(true);


//            while (true) {
            sampleClient.publish(topic, message);
            System.out.println("paho-client message published");
//            Thread.sleep(2000);
//            }
            sampleClient.disconnect();
            System.out.println("paho-client disconnected");
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }
}
