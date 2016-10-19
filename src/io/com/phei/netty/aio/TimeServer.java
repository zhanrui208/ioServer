package io.com.phei.netty.aio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8085;
		ServerSocket server = null;
		
		BufferedInputStream in = null;
		PrintWriter out = null ;
		
		try {
			server = new ServerSocket(port);
			System.out.println("The Time server is start in port:" + port);
			
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor  = new TimeServerHandlerExecutePool(50, 10000); 
			while(true){
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("end");
			if (server !=null){
				System.out.println("The Time server close!");
				try {
					server.close();
					server = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
		
		
		
	}

}
