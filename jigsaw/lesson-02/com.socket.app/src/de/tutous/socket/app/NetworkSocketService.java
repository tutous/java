package de.tutous.socket.app;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import de.tutous.socket.spi.NetworkSocket;

public class NetworkSocketService {

	private NetworkSocketService() {
	}

	public static NetworkSocket open(String type) {
		return ServiceLoader.load(NetworkSocket.class)//
				.stream() //
				.filter(p -> p.type().getSimpleName().toUpperCase().startsWith(type))//
				.map(Provider::get).findFirst()//
				.get();
	}

}
