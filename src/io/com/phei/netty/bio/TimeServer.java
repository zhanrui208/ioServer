package io.com.phei.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String arg[]){
		int port = 8085;
		
		
		ServerSocket server = null;

		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port :"+ port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				new Thread(new TimesServerHandler(socket)).start();	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (server != null){
				System.out.println("The time server close");
				try {
					server.close();
					server =  null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
