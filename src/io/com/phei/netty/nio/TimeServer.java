package io.com.phei.netty.nio;

public class TimeServer {
	public static void main(String arg[]) {

		int port = 8085;
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
	}
}
