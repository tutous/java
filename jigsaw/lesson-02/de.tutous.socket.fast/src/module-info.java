module de.tutous.socket.fast {
	requires de.tutous.socket.spi;
	provides de.tutous.socket.spi.NetworkSocket with de.tutous.socket.fast.FastNetworkSocketProvider;
}