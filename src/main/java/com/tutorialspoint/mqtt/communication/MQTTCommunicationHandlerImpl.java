package com.tutorialspoint.mqtt.communication;

/**
 * Created by phuongdv on 6/6/17.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MQTTCommunicationHandlerImpl extends MQTTCommunicationHandler {

    private static final Log log = LogFactory.getLog(MQTTCommunicationHandlerImpl.class);
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
    private ScheduledFuture<?> dataPushServiceHandler;

    public MQTTCommunicationHandlerImpl(String deviceOwner, String deviceType,
                                        String mqttBrokerEndPoint, String subscribeTopic) {
        super(deviceOwner, deviceType, mqttBrokerEndPoint, subscribeTopic);
    }

    public MQTTCommunicationHandlerImpl(String deviceOwner, String deviceType,
                                        String mqttBrokerEndPoint, String subscribeTopic,
                                        int intervalInMillis) {
        super(deviceOwner, deviceType, mqttBrokerEndPoint, subscribeTopic, intervalInMillis);
    }

    public ScheduledFuture<?> getDataPushServiceHandler() {
        return dataPushServiceHandler;
    }

    @Override
    public void connect() {
//        System.out.println("Connecting.....");
        Runnable connector = new Runnable() {
            public void run() {

                System.out.println("Run connector..." + isConnected());
                while (!isConnected()) {
                    try {

                        System.out.println("Connect to queue");
                        connectToQueue();
                        System.out.println("subscribeToQueue");
                        subscribeToQueue();
                        System.out.println("publishDeviceData");
                        publishDeviceData(5);

                    } catch (CommunicationHandlerException e) {
                        log.warn("Connection/Subscription to MQTT Broker at: " +
                                mqttBrokerEndPoint + " failed");

                        try {
                            Thread.sleep(timeoutInterval);
                        } catch (InterruptedException ex) {
                            log.error(
                                    "MQTT-Subscriber: Thread Sleep Interrupt Exception");
                        }
                    }
                }
            }
        };

        Thread connectorThread = new Thread(connector);
        connectorThread.setDaemon(true);
        connectorThread.start();
    }

    @Override
    public void processIncomingMessage(MqttMessage message) {
//        log.info(AgentConstants.LOG_APPENDER + "Message " + message.toString() + " was received");

        String deviceOwner = "phuong";
        String deviceID = "iphone";
        String replyMessage;
        log.info("Message " + message.toString() + " was received");

//        String[] controlSignal = message.toString().split(":");
//        // message- "<SIGNAL_TYPE>:<SIGNAL_MODE>" format.(ex: "BULB:ON", "TEMPERATURE", "HUMIDITY")
//
//        switch (controlSignal[0].toUpperCase()) {
//            case AgentConstants.BULB_CONTROL:
//                AgentCoreOperations.changeBulbStatus(controlSignal[1].equals(
//                        AgentConstants.CONTROL_ON));
//                log.info(AgentConstants.LOG_APPENDER + "Bulb was switched to state: '" +
//                        controlSignal[1] + "'");
//                break;
//
//            case AgentConstants.TEMPERATURE_CONTROL:
//                double currentTemperature = agentManager.getTemperature();
//
//                String replyTemperature =
//                        "Current temperature was read as: '" + currentTemperature + "C'";
//                log.info(AgentConstants.LOG_APPENDER + replyTemperature);
//
//                String tempPublishTopic = String.format(
//                        AgentConstants.MQTT_PUBLISH_TOPIC, deviceOwner, deviceID);
//                replyMessage = AgentConstants.TEMPERATURE_CONTROL + ":" + currentTemperature;
//
//                try {
//                    publishToQueue(tempPublishTopic, replyMessage);
//                } catch (CommunicationHandlerException e) {
//                    log.error(AgentConstants.LOG_APPENDER +
//                            "MQTT - Publishing, reply message to the MQTT Queue at: " +
//                            agentManager.getAgentConfigs().getMqttBrokerEndpoint() +
//                            "failed");
//                }
//                break;
//
//            case AgentConstants.HUMIDITY_CONTROL:
//                double currentHumidity = agentManager.getHumidity();
//
//                String replyHumidity =
//                        "Current humidity was read as: '" + currentHumidity + "%'";
//                log.info(AgentConstants.LOG_APPENDER + replyHumidity);
//
//                String humidPublishTopic = String.format(
//                        AgentConstants.MQTT_PUBLISH_TOPIC, deviceOwner, deviceID);
//                replyMessage = AgentConstants.HUMIDITY_CONTROL + ":" + currentHumidity;
//
//                try {
//                    publishToQueue(humidPublishTopic, replyMessage);
//                } catch (CommunicationHandlerException e) {
//                    log.error(AgentConstants.LOG_APPENDER +
//                            "MQTT - Publishing, reply message to the MQTT Queue at: " +
//                            agentManager.getAgentConfigs().getMqttBrokerEndpoint() +
//                            "failed");
//                }
//                break;
//
//            default:
//                log.warn(AgentConstants.LOG_APPENDER + "'" + controlSignal[0] +
//                        "' is invalid and not-supported for " + "this device-type");
//                break;
//        }
    }


    @Override
    public void publishDeviceData(int publishInterval) {
        System.out.println("RUN publish Device Data Main");
        Runnable pushDataRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("RUN>...PUB");
                double currentTemperature = 30;//agentManager.getTemperature();
                String payLoad =
                        "PUBLISHER:" + "TEMPERATURE_CONTROL" + ":" +
                                currentTemperature;

                MqttMessage pushMessage = new MqttMessage();
                pushMessage.setPayload(payLoad.getBytes(StandardCharsets.UTF_8));
                pushMessage.setQos(DEFAULT_MQTT_QUALITY_OF_SERVICE);
                pushMessage.setRetained(true);

//                String topic = String.format(AgentConstants.MQTT_PUBLISH_TOPIC,
//                        agentManager.getAgentConfigs().getDeviceOwner(),
//                        agentManager.getAgentConfigs().getDeviceId());
                String topic = "phuongiphone";

                try {
                    publishToQueue(topic, pushMessage);
                    log.info("Message: '" + pushMessage +
                            "' published to MQTT Queue at [" +
                            "] under topic [" + topic + "]");

                } catch (CommunicationHandlerException e) {
//                    log.warn(AgentConstants.LOG_APPENDER + "Data Publish attempt to topic - [" +
//                            AgentConstants.MQTT_PUBLISH_TOPIC + "] failed for payload [" +
//                            payLoad + "]");
                    e.printStackTrace();
                }
            }
        };


        dataPushServiceHandler = service.scheduleAtFixedRate(pushDataRunnable, publishInterval,
                publishInterval, TimeUnit.SECONDS);
    }


    @Override
    public void disconnect() {
        System.out.println("disconnect....");
        Runnable stopConnection = new Runnable() {
            public void run() {
                while (isConnected()) {
                    try {
                        dataPushServiceHandler.cancel(true);
                        closeConnection();

                    } catch (MqttException e) {
                        if (log.isDebugEnabled()) {
//                            log.warn(AgentConstants.LOG_APPENDER +
//                                    "Unable to 'STOP' MQTT connection at broker at: " +
//                                    mqttBrokerEndPoint);
                            e.printStackTrace();
                        }

                        try {
                            Thread.sleep(timeoutInterval);
                        } catch (InterruptedException e1) {
//                            log.error(AgentConstants.LOG_APPENDER +
//                                    "MQTT-Terminator: Thread Sleep Interrupt Exception");
                            e1.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread terminatorThread = new Thread(stopConnection);
        terminatorThread.setDaemon(true);
        terminatorThread.start();
    }

    @Override
    public void processIncomingMessage() {

    }

    public static void main(String[] args) {
        CommunicationHandler mqttCommunicator = new MQTTCommunicationHandlerImpl(
                "phuong", "iphone",
                "tcp://0.0.0.0:1883", "news");

        mqttCommunicator.connect();

//        Thread messageProcessorThread = new Thread() {
//            public void run() {
//
//                while (true) {
//                    System.out.println("running..");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        messageProcessorThread.setDaemon(true);
//        messageProcessorThread.start();
    }

}