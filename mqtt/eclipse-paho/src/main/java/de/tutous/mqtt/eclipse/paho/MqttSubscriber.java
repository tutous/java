package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface MqttSubscriber {

	public MqttSubscriberCallback subscribe() throws MqttException;

}