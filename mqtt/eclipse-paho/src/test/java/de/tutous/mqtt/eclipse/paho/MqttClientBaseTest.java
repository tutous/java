package de.tutous.mqtt.eclipse.paho;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class MqttClientBaseTest {

	private final String broker;

	private static final String TOPIC_WEATHER = "tutous/topic/weather";
	private static final int QoS2 = 2;

	private static final String WEATHER_MESSAGE_ONE = "location one rain";
	private static final String WEATHER_MESSAGE_TWO = "location two sunny";

	public MqttClientBaseTest(String broker) {
		this.broker = broker;
	}

	protected void doTestOnePublisherAndTwoSubscriber() throws MqttException, InterruptedException, AssertionError {

		MqttSubscriber subscriberOne = new MqttSubscriberClient(TOPIC_WEATHER, QoS2, broker);
		MqttSubscriber subscriberTwo = new MqttSubscriberClient(TOPIC_WEATHER, QoS2, broker);
		MqttPublisher publisher = new MqttPublisherClient(TOPIC_WEATHER, QoS2, broker);

		MqttSubscriberCallback callbackOne = subscriberOne.subscribe();
		MqttSubscriberCallback callbackTwo = subscriberTwo.subscribe();

		publisher.publish(WEATHER_MESSAGE_ONE);
		// wait here 500 mills
		Thread.sleep(500);

		publisher.publish(WEATHER_MESSAGE_TWO);
		// wait here 500 mills
		Thread.sleep(500);

		Assert.assertThat(callbackOne.getMessages().get(0), Matchers.equalTo(WEATHER_MESSAGE_ONE));
		Assert.assertThat(callbackOne.getMessages().get(1), Matchers.equalTo(WEATHER_MESSAGE_TWO));

		Assert.assertThat(callbackTwo.getMessages().get(0), Matchers.equalTo(WEATHER_MESSAGE_ONE));
		Assert.assertThat(callbackTwo.getMessages().get(1), Matchers.equalTo(WEATHER_MESSAGE_TWO));
	}

	protected void doTestTwoPublisherAndOneSubscriber() throws MqttException, InterruptedException, AssertionError {

		MqttSubscriber subscriber = new MqttSubscriberClient(TOPIC_WEATHER, QoS2, broker);
		MqttPublisher publisherOne = new MqttPublisherClient(TOPIC_WEATHER, QoS2, broker);
		MqttPublisher publisherTwo = new MqttPublisherClient(TOPIC_WEATHER, QoS2, broker);

		MqttSubscriberCallback callback = subscriber.subscribe();

		publisherOne.publish(WEATHER_MESSAGE_ONE);
		// wait here 500 mills
		Thread.sleep(500);

		publisherTwo.publish(WEATHER_MESSAGE_TWO);
		// wait here 500 mills
		Thread.sleep(500);

		Assert.assertThat(callback.getMessages().get(0), Matchers.equalTo(WEATHER_MESSAGE_ONE));
		Assert.assertThat(callback.getMessages().get(1), Matchers.equalTo(WEATHER_MESSAGE_TWO));

	}

}
