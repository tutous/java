package de.tutous.socket.slow;

import de.tutous.socket.spi.NetworkSocket;

public class SlowNetworkSocketProvider implements NetworkSocket {

	private SlowNetworkSocketSender sender;

	@Override
	public void openNetworkSocket() {
		sender = new SlowNetworkSocketSender();
	}

	@Override
	public void send(byte[] content) {
		sender.send(new String(content));
	}
}
