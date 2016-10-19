package io.com.phei.netty.aio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
	private Socket socket;
	public TimeServerHandler(Socket socket){
		this.socket = socket;
	}
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader( new InputStreamReader(this.socket.getInputStream()) );
			out =new PrintWriter(this.socket.getOutputStream(),true)  ;
			String currentTime = null;
			String body = null;
			boolean flag = true;
			while(flag){
				body = in.readLine();
				if (body !=null){
					flag=false;
				}
				System.out.println("The Time server receive order :"+ body);
				currentTime = "QUERY TIME ORDER".equals(body)? new Date(System.currentTimeMillis()).toString():"BAD ORDER";
				out.println(currentTime);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in = null;
			}
			
			if (out != null){
				out.close();
				out = null;
			}
			if (socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket = null;
			}
		}
		
		
	}
	
}
