package io.com.phei.netty.bio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
	public static void main(String arg[]){
		int port = 8085;
		Socket socket =null;
		BufferedReader in  = null;
		PrintWriter out = null;
		
			try {
				socket = new Socket("127.0.0.1",port);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(),true);
				out.println("QUERY TIME ORDER");
				System.out.println("Send order 3 server succeed.");
				String resp = in.readLine();
				System.out.println("Now is :" + resp);
		
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				if (out!=null){
					out.close();
					out = null ;
				}
				
				if (in !=null){
					try {
						in.close();
						in = null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (socket != null){
					try {
						socket.close();
						socket = null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
				
		
	}
}
