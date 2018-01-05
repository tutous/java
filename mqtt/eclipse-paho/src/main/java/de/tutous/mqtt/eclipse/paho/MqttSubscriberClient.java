package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscriberClient extends AbstractMqttClient implements MqttSubscriber {

	public MqttSubscriberClient(String topic, int qos, String broker) throws MqttException {
		super(topic, qos, broker);
		super.connect();
	}

	@Override
	public MqttSubscriberCallback subscribe() throws MqttException {
		MqttSubscriberCallback subscriberCallback = new MqttSubscriberCallback();
		super.subscribe(subscriberCallback.getCallback());
		return subscriberCallback;
	}
}
