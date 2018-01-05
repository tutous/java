package de.tutous.mqtt.eclipse.paho;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttSubscriberCallback {

	private static Logger log = LoggerFactory.getLogger(MqttSubscriberCallback.class);

	private List<String> messages;
	private final MqttCallback callback;

	public MqttSubscriberCallback() {
		messages = new ArrayList<>();
		callback = new MqttCallback() {

			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				log.info("message arrived: {}", message);
				messages.add(new String(message.getPayload()));
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {

			}

			@Override
			public void connectionLost(Throwable cause) {

			}

		};
	}

	protected MqttCallback getCallback() {
		return callback;
	}

	public List<String> getMessages() {
		return messages;
	}

}
