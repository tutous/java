package de.tutous.socket.spi;

public interface NetworkSocket {

	public void openNetworkSocket();
	
	public void send(byte[] content);
	
}
