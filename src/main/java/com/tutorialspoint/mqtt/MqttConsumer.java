package com.tutorialspoint.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by phuongdv on 5/20/17.
 */
public class MqttConsumer implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost!");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("| Topic:" + s);
        System.out.println("| Message: " + new String(mqttMessage.getPayload()));
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }


    public void subscribe()throws Exception{
        // Creating a MQTT Client using Eclipse Paho
        String topic = "news";
        int qos = 0;
        String broker = "tcp://0.0.0.0:1883";
        String clientId = "paho-java-client-consumer-123";

        MqttClient sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
        sampleClient.setCallback(this);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);
        System.out.println("paho-client connecting to broker: " + broker);
        sampleClient.connect(connOpts);

        sampleClient.subscribe(topic, qos);

      //  Thread.sleep(5000);
    }

    public static void main(String[] args) throws Exception{

        MqttConsumer mqttConsumer = new MqttConsumer();
        mqttConsumer.subscribe();



    }
}
