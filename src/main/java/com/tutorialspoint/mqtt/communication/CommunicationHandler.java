package com.tutorialspoint.mqtt.communication;

/**
 * Created by phuongdv on 6/6/17.
 */
public interface CommunicationHandler<T> {
    int DEFAULT_TIMEOUT_INTERVAL = 5000;      // millis ~ 10 sec

    void connect();

    boolean isConnected();

    void processIncomingMessage(T message);

    void processIncomingMessage();

    void publishDeviceData(int publishInterval);

    void disconnect();
}