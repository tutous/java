module de.tutous.socket.slow {
	requires de.tutous.socket.spi;
	provides de.tutous.socket.spi.NetworkSocket with de.tutous.socket.slow.SlowNetworkSocketProvider;
}