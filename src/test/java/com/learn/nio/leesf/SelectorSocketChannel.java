package com.learn.nio.leesf;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * Created by LEESF on 2017/4/24.
 * 客户端1
 */
public class SelectorSocketChannel {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.connect(new InetSocketAddress("localhost",1234));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            selector.select();
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();
                if (key.isConnectable()) {
                    if (socketChannel.isConnectionPending()) {
                        if (socketChannel.finishConnect()) {
                            key.interestOps(SelectionKey.OP_READ);
                            sendMessage(socketChannel);
                        } else {
                            key.cancel();
                        }
                    }
                }
                if(key.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    while (true) {
                        byteBuffer.clear();
                        int count = socketChannel.read(byteBuffer);
                        if (count > 0) {
                            byteBuffer.flip();
                            System.out.println("client receive message: " + getString(byteBuffer));
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void sendMessage(SocketChannel socketChannel) throws Exception {
        String message = "client sending message " + System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byteBuffer.clear();
        System.out.println("client sending message: " + message);
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
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