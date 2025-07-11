package com.estimp.breakify_service.mqtt;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.services.ConnectionService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {

    private IMqttClient mqttClient;

    private final String publisherId = "breakify_backend";
    private final String brokerUrl = "tcp://localhost:1883";

    private final ConnectionService connectionService;

    public MqttPublisherService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostConstruct
    public void init() throws MqttException {
        mqttClient = new MqttClient(brokerUrl, publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        mqttClient.connect(options);
    }

    private void publish(String topic, String payload) {
        if (mqttClient.isConnected() && connectionService.isMqttEnabled()) {
            try {
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(1);
                topic = "breakify/" + topic;
                mqttClient.publish(topic, message);
                System.out.println("Published notification to MQTT broker");
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void publishNotificationToMqtt(User user, App app, Notification notification) {
        if (mqttClient.isConnected() && connectionService.isMqttEnabled()) {
            System.out.println("Publishing notification to MQTT broker");
            String topic = "notifications/" + user.getUsername() + "/" + app.getPackageName();
            String message = "Nueva notificación de " + app.getName() + ": " + notification.getTitle() + ": " + notification.getText();
            publish(topic, message);
        }
    }

    @PreDestroy
    public void cleanup() throws MqttException {
        if (mqttClient != null && mqttClient.isConnected()) {
            mqttClient.disconnect();
        }
    }
}
