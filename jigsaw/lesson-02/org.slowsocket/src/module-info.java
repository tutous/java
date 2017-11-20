module org.slowsocket {
	requires com.socket;
	provides com.socket.spi.NetworkSocketProvider with org.slowsocket.SlowNetworkSocketProvider;
}