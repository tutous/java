package de.tutous.mqtt.eclipse.paho;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMqttClient {

	private static Logger log = LoggerFactory.getLogger(AbstractMqttClient.class);

	private final String topic;
	private final int qos;
	private final String broker;
	private final String clientId = UUID.randomUUID().toString();
	private final MemoryPersistence persistence = new MemoryPersistence();
	private MqttClient mqttClient;

	public AbstractMqttClient(String topic, int qos, String broker) {
		this.topic = topic;
		this.qos = qos;
		this.broker = broker;
	}

	protected void connect() throws MqttException {
		mqttClient = new MqttClient(broker, clientId, persistence);
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);

		log.info("Connecting to broker: " + broker);
		mqttClient.connect(connOpts);
		log.info("Connected");

	}

	protected void subscribe(MqttCallback callback) throws MqttException {

		log.info("subscribe messages by callback to topic: " + topic);
		mqttClient.subscribe(topic, qos);
		mqttClient.setCallback(callback);
	}

	protected void publish(byte[] content) throws MqttException {

		log.info("Publishing message: " + content);
		MqttMessage message = new MqttMessage(content);
		message.setQos(qos);
		mqttClient.publish(topic, message);
		log.info("Message published");

	}

}
