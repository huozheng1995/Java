package com.learn.nio.leesf;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
/**
 * @description: NIO之服务端
 * @author: Huo
 * @create: 2020-04-21 15:50
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocketChannel.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress("localhost", 1234));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                socketChannel.read(byteBuffer);
                byteBuffer.flip();
                System.out.println("server received message: " + getString(byteBuffer));
                byteBuffer.clear();
                String message = "server sending message " + System.currentTimeMillis();
                System.out.println("server sends message: " + message);
                byteBuffer.put(message.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                break;
            }
        }
        try {
            serverSocketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString(ByteBuffer buffer) {
        Charset charset;
        CharsetDecoder decoder;
        CharBuffer charBuffer;
        try {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
