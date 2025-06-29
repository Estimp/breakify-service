package com.estimp.breakify_service.mqtt;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {

    private IMqttClient mqttClient;

    private final String publisherId = "breakify_backend";
    private final String brokerUrl = "tcp://localhost:1883";

    @PostConstruct
    public void init() throws MqttException {
        mqttClient = new MqttClient(brokerUrl, publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        mqttClient.connect(options);
    }

    public void publish(String topic, String payload) {
        if (mqttClient.isConnected()) {
            try {
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(1);
                topic = "breakify/" + topic;
                mqttClient.publish(topic, message);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    public void cleanup() throws MqttException {
        if (mqttClient != null && mqttClient.isConnected()) {
            mqttClient.disconnect();
        }
    }
}
