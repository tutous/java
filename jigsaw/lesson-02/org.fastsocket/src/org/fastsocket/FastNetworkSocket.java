package org.fastsocket;

import com.socket.NetworkSocket;

class FastNetworkSocket extends NetworkSocket {

	private String conf;

	FastNetworkSocket(String conf) {
		this.conf = conf;
	}

	public void close() {
	}
}
