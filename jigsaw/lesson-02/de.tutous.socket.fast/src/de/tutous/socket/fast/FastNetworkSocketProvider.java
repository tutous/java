package de.tutous.socket.fast;

import de.tutous.socket.spi.NetworkSocket;

public class FastNetworkSocketProvider implements NetworkSocket {

	private FastNetworkSocketSender sender;

	@Override
	public void openNetworkSocket() {
		sender = new FastNetworkSocketSender();
	}

	@Override
	public void send(byte[] content) {
		sender.send(new String(content));
	}
}
