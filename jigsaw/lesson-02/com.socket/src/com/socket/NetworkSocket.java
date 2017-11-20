package com.socket;

import java.io.Closeable;
import java.util.Iterator;
import java.util.ServiceLoader;

import com.socket.spi.NetworkSocketProvider;

public abstract class NetworkSocket implements Closeable {

	protected NetworkSocket() {
	}

	public static NetworkSocket open(String type) {
		ServiceLoader<NetworkSocketProvider> sl = ServiceLoader.load(NetworkSocketProvider.class);
		Iterator<NetworkSocketProvider> iterator = sl.iterator();
		while (iterator.hasNext()) {
			NetworkSocketProvider provider = iterator.next();
			if (provider.getClass().getSimpleName().toUpperCase().startsWith(type)) {
				return provider.openNetworkSocket();
			}
		}
		throw new RuntimeException("No service providers found!");
	}

}
