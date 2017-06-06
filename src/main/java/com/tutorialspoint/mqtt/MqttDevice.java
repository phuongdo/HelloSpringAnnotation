package com.tutorialspoint.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by phuongdv on 5/20/17.
 */
public class MqttDevice implements MqttCallback {



    String topic = "news";
    int qos = 0;
    String broker = "tcp://0.0.0.0:1883";
    String clientId = "paho-java-client-888888";
    MqttConnectOptions connOpts = new MqttConnectOptions();
    MqttClient sampleClient;


    public MqttDevice(){
        try {
            sampleClient= new MqttClient(broker, clientId, new MemoryPersistence());
            sampleClient.setCallback(this);
            connOpts.setCleanSession(true);
            connOpts.setWill("gone", "i'm die".getBytes(), 2, true);
            System.out.println("paho-client connecting to broker: " + broker);
            sampleClient.connect(connOpts);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
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

    public void publish() throws Exception {

        String content = "hello";
        MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            message.setRetained(true);



        while (true) {
            if(sampleClient.isConnected()) {
                sampleClient.publish("othertopic", message);
                System.out.println("paho-client message published");
            }else{
                System.out.println("trying to connect..");
            }
            Thread.sleep(2000);
        }

    }

    public void subscribe() throws Exception {
        // Creating a MQTT Client using Eclipse Paho
//        String topic = "news";
//        int qos = 0;
//        String broker = "tcp://0.0.0.0:1883";
//        String clientId = "paho-java-client-888888";




        sampleClient.subscribe(topic, qos);
        //  Thread.sleep(5000);
    }



    public void init(){
//        new Thread(() -> {
//            try {
//                this.subscribe();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        new Thread(() -> {
            try {
                this.publish();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

//        try {
//            sampleClient.disconnect();
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) throws Exception {

        MqttDevice mqttDevice = new MqttDevice();
        mqttDevice.init();

//
//        Thread reconnectThread = new Thread() {
//            public void run() {
//
//                try {
//                    mqttConsumer.connect();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        };
//        reconnectThread.setDaemon(true);
//        reconnectThread.start();
//



        //qttConsumer.subscribe();


    }
}
