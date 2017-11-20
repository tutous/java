package org.slowsocket;

import com.socket.NetworkSocket;

class SlowNetworkSocket extends NetworkSocket {

	private String conf;

	SlowNetworkSocket(String conf) {
		this.conf = conf;
	}

	public void close() {
	}
}
