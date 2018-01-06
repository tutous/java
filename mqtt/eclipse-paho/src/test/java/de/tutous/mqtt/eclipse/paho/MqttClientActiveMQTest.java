package de.tutous.mqtt.eclipse.paho;

import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MqttClientActiveMQTest extends MqttClientBaseTest {

	private static final String BROKER = "tcp://localhost:1883";

	private static BrokerService broker;

	public MqttClientActiveMQTest() {
		super(BROKER);
	}

	@BeforeClass
	public static void setUp() throws Exception {
		broker = new BrokerService();
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("mqtt://localhost:1883"));
		broker.addConnector(connector);
		broker.start();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		broker.stop();
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
