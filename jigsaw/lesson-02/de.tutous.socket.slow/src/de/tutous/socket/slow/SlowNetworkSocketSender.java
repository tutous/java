package de.tutous.socket.slow;

import java.util.concurrent.TimeUnit;

public class SlowNetworkSocketSender {

	public void send(String message) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("send: " + new String(message));
	}

}
