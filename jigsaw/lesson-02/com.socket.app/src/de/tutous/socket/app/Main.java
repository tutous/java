package de.tutous.socket.app;

import de.tutous.socket.spi.NetworkSocket;

public class Main {

	public static void main(String[] args) {

		NetworkSocket networkSocket = NetworkSocketService.open("FAST");
		System.out.println(networkSocket.getClass());

		networkSocket = NetworkSocketService.open("SLOW");
		System.out.println(networkSocket.getClass());

	}

}
