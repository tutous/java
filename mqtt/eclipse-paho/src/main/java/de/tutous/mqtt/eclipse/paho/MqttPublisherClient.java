package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPublisherClient extends AbstractMqttClient implements MqttPublisher {

	public MqttPublisherClient(String topic, int qos, String broker) throws MqttException {
		super(topic, qos, broker);
		super.connect();
	}

	@Override
	public void publish(String content) throws MqttException {
		super.publish(content.getBytes());
	}

}
