package com.learn.netty.oneToOne;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;

import java.util.Iterator;

/**
 * @description: 引导服务器只是完成了服务端的创建，如指定端口和处理器等，并未涉及到服务端的具体逻辑，
 * 其具体业务逻辑可以在处理器中完成，处理器需要继承ChannelInboundHandlerAdapter
 * @author: Huo
 * @create: 2020-06-20 10:50
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private EventLoopGroup group1;
    private EventLoopGroup group2;

    public EchoServerHandler(EventLoopGroup group1, EventLoopGroup group2) {
        this.group1 = group1;
        this.group2 = group2;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Iterator<EventExecutor> it1 = this.group1.iterator();
        System.out.println("it1");
        while (it1.hasNext()) {
            System.out.println(it1.next());
            System.out.println(it1.next() == ctx.channel().eventLoop());
        }
        System.out.println("it2");
        Iterator<EventExecutor> it2 = this.group2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
            System.out.println(it2.next() == ctx.channel().eventLoop());
        }
        ByteBuf bb = (ByteBuf) msg;
        bb.markReaderIndex();
        System.out.println("Server received: " + ByteBufUtil.hexDump(bb.readBytes(bb.readableBytes())));
        bb.resetReaderIndex();
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
