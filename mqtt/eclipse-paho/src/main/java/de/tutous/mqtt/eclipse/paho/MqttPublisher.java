package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface MqttPublisher {

	public void publish(String content) throws MqttException;

}