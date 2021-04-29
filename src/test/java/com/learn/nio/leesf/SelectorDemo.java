package com.learn.nio.leesf;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.util.Iterator;
/**
 * @description: selector的基本写法
 * @author: Huo
 * @create: 2020-04-21 19:56
 */
public class SelectorDemo {
    public static int PORT_NUMBER = 1234;
    private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] argv) throws Exception {
        new SelectorDemo().go(argv);
    }


    public void go(String[] argv) throws Exception {
        int port = PORT_NUMBER;
        if (argv.length > 0) {
            port = Integer.parseInt(argv[0]);
        }
        System.out.println("Listening on port " + port);

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                    buffer.clear();
                    buffer.put("Hi there!\r\n".getBytes());
                    buffer.flip();
                    channel.write(buffer);
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    int count;
                    buffer.clear();
                    while ((count = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            socketChannel.write(buffer);
                        }
                        buffer.clear();
                    }
                    if (count < 0) {
                        socketChannel.close();
                    }
                }
                it.remove();
            }
        }
    }

}
