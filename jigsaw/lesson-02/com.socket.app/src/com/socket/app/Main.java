package com.socket.app;

import com.socket.NetworkSocket;

public class Main {

	public static void main(String[] args) {

		NetworkSocket networkSocket = NetworkSocket.open("FAST");
		System.out.println(networkSocket.getClass());

		networkSocket = NetworkSocket.open("SLOW");
		System.out.println(networkSocket.getClass());

	}

}
