package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class MqttClientHiveMQTest {

	private static final String BROKER = "tcp://broker.hivemq.com:1883";

	private static final String TOPIC_WEATHER = "tutous/topic/weather";
	private static final int QoS2 = 2;

	private static final String WEATHER_MESSAGE_ONE = "location one rain";
	private static final String WEATHER_MESSAGE_TWO = "location two sunny";

	@Test
	public void testOnePublisherAndTwoSubscriber() throws MqttException, InterruptedException {

		MqttSubscriber subscriberOne = new MqttSubscriberClient(TOPIC_WEATHER, QoS2, BROKER);
		MqttSubscriber subscriberTwo = new MqttSubscriberClient(TOPIC_WEATHER, QoS2, BROKER);
		MqttPublisher publisher = new MqttPublisherClient(TOPIC_WEATHER, QoS2, BROKER);

		MqttSubscriberCallback callbackOne = subscriberOne.subscribe();
		MqttSubscriberCallback callbackTwo = subscriberTwo.subscribe();

		publisher.publish(WEATHER_MESSAGE_ONE);
		publisher.publish(WEATHER_MESSAGE_TWO);
		
		// wait here 500 mills
		Thread.sleep(500);

		Assert.assertThat(callbackOne.getMessages().get(0), Matchers.equalTo(WEATHER_MESSAGE_ONE));
		Assert.assertThat(callbackOne.getMessages().get(1), Matchers.equalTo(WEATHER_MESSAGE_TWO));

		Assert.assertThat(callbackTwo.getMessages().get(0), Matchers.equalTo(WEATHER_MESSAGE_ONE));
		Assert.assertThat(callbackTwo.getMessages().get(1), Matchers.equalTo(WEATHER_MESSAGE_TWO));
	}
	
	@Test
	public void testTwoPublisherAndOneSubscriber() throws MqttException, InterruptedException {
		
		MqttSubscriber subscriber = new MqttSubscriberClient(TOPIC_WEATHER, QoS2, BROKER);
		MqttPublisher publisherOne = new MqttPublisherClient(TOPIC_WEATHER, QoS2, BROKER);
		MqttPublisher publisherTwo = new MqttPublisherClient(TOPIC_WEATHER, QoS2, BROKER);

		MqttSubscriberCallback callback = subscriber.subscribe();

		publisherOne.publish(WEATHER_MESSAGE_ONE);
		publisherTwo.publish(WEATHER_MESSAGE_TWO);
		
		// wait here 500 mills
		Thread.sleep(500);

		Assert.assertThat(callback.getMessages().get(0), Matchers.equalTo(WEATHER_MESSAGE_ONE));
		Assert.assertThat(callback.getMessages().get(1), Matchers.equalTo(WEATHER_MESSAGE_TWO));

	}

}
