package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

public class MqttClientHiveMQTest extends MqttClientBaseTest {

	private static final String BROKER = "tcp://broker.hivemq.com:1883";

	public MqttClientHiveMQTest() {
		super(BROKER);
	}

	@Test
	public void testOnePublisherAndTwoSubscriber() throws MqttException, InterruptedException {
		doTestOnePublisherAndTwoSubscriber();
	}

	@Test
	public void testTwoPublisherAndOneSubscriber() throws MqttException, InterruptedException {
		doTestTwoPublisherAndOneSubscriber();
	}

}
