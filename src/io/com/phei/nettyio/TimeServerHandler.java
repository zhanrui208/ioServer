package io.com.phei.nettyio;


import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
	
	private int counter;
	
//	@Override
//	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
//		ByteBuf buf  = (ByteBuf) msg;
//		byte[]  req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "UTF-8");
//		System.out.println("This time server receive order : " + body);
//		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString() :"BAD ORDER";
//		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//		ctx.write(resp);
// 	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
		ByteBuf buf  = (ByteBuf) msg;
		byte[]  req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"UTF-8").substring(0,req.length - System.getProperty("line.separator").length());
		System.out.println("This time server receive order : " + body + ";the counter iid :" +  ++counter);
		
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString() :"BAD ORDER";
		currentTime +=System.getProperty("line.separator");
		
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);
		
 	}
		
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		ctx.close();
	}
}
//String body = new String(req,"UTF-8").substring(0,req.length - System.getProperty("line.separator").length());
