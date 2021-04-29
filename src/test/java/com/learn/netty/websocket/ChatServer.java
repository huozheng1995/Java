package com.learn.netty.websocket;

import io.netty.bootstrap.*;
import io.netty.channel.*;
import io.netty.channel.group.*;
import io.netty.channel.nio.*;
import io.netty.channel.socket.nio.*;
import io.netty.util.concurrent.*;

import java.net.InetSocketAddress;

/**
 * @description: ChatServer类用于启动服务器并且安装ChatServerInitializer，其代码如下
 * @author: Huo
 * @create: 2020-07-02 17:22
 */
public class ChatServer {
    //每次加入一个连接，产生一个channel，在TextWebSocketFrameHandler中将该channel加入到channelGroup，
    //有新消息到达时，遍历channelGroup并向所有channel发送该消息
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress address) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group).channel(NioServerSocketChannel.class).childHandler(createInitializer(channelGroup));
        ChannelFuture future = bootstrap.bind(address);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;
    }

    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
        return new ChatServerInitializer(group);
    }

    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Please give port as argument");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        final ChatServer endpoint = new ChatServer();
        ChannelFuture future = endpoint.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                endpoint.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
