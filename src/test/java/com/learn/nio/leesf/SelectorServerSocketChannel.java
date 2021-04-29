package com.learn.nio.leesf;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

/**
 * Created by LEESF on 2017/4/24.
 * 服务端1
 */
public class SelectorServerSocketChannel {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocketChannel.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress("localhost", 1234));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Connected: " + channel.socket().getRemoteSocketAddress());
                }
                if (key.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("server received message: " + getString(byteBuffer));
                    byteBuffer.clear();
                    String message = "server sending message " + System.currentTimeMillis();
                    System.out.println("server sending message: " + message);
                    byteBuffer.put(message.getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                }

                it.remove();
            }
        }
    }

    private static String getString(ByteBuffer buffer) {
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