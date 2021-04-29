package com.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestBlockingNIO2 {
	
	//客户端
	@Test
	public void client() throws IOException{
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		FileChannel inChannel = FileChannel.open(Paths.get("resource/test.txt"), StandardOpenOption.READ);
		
		ByteBuffer buf = ByteBuffer.allocate(2);
		while(inChannel.read(buf) != -1){
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		sChannel.shutdownOutput();

		//接收服务端的反馈
		int len = 0;
		while((len = sChannel.read(buf)) != -1){
			buf.flip();
			System.out.println(new String(buf.array(), 0, len));
			buf.clear();
		}
		
		inChannel.close();
		sChannel.close();
	}
	
	//服务端
	@Test
	public void server() throws IOException{
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		FileChannel outChannel = FileChannel.open(Paths.get("resource/test2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		ssChannel.bind(new InetSocketAddress(9898));
		SocketChannel sChannel = ssChannel.accept();

		ByteBuffer buf = ByteBuffer.allocate(2);
		int len = 0;
		while( (len = sChannel.read(buf)) != -1){
			buf.flip();
			System.out.println("接收到消息：" + getDecoderString(buf));
			System.out.println("接收到消息2：" + new String(buf.array(), 0, len));
			outChannel.write(buf);
			buf.clear();
		}

		//发送反馈给客户端
		buf.put("服务端接收数据成功".getBytes());
		buf.flip();
		sChannel.write(buf);

		sChannel.close();
		outChannel.close();
		ssChannel.close();
	}

	public String getDecoderString(ByteBuffer buffer) throws IOException {
		Charset charset = Charset.forName("utf-8");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = decoder.decode(buffer);
		return charBuffer.toString();
	}
}
