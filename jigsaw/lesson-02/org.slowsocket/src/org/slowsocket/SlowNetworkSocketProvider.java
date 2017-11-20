package org.slowsocket;

import com.socket.NetworkSocket;
import com.socket.spi.NetworkSocketProvider;

public class SlowNetworkSocketProvider extends NetworkSocketProvider {
	public SlowNetworkSocketProvider() {
	}

	@Override
	public NetworkSocket openNetworkSocket() {
		return new SlowNetworkSocket("be.slow");
	}
}
