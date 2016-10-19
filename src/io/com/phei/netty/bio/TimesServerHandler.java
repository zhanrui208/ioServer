package io.com.phei.netty.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimesServerHandler implements Runnable{
	private Socket socket;
	
	public TimesServerHandler(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null ;
		PrintWriter out = null;
		try {
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				out = new PrintWriter(this.socket.getOutputStream(),true);
				String currentTime = null;
				String body = null;
				boolean flag = true;
				while(flag){
					body = in.readLine();
					if (body !=null){
						flag=false;
					}
					
				System.out.println("The time server receive order :" + body);
				currentTime = " QUERY TIME ORDER:".equals(body)?new java.util.Date(
				System.currentTimeMillis()).toString():"BAD ORDER";
				
				out.println(currentTime);
			}
			
		} catch (IOException e) {
			if (in !=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if (out !=null){
				out.close();
				out = null;
			}
			
			if(this.socket != null){
				try {
					this.socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
