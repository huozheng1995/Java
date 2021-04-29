package com.learn.netty.oneToOne;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @description: 本部分完成用户实际的业务逻辑，本例中的EchoClientHandler继承SimpleChannelInboundHandler，需要重写如下三个函数
 * · channelActive函数，在建立了与服务端的连接后该函数被调用
 * · channelRead0函数，当接收到服务端发送来的消息后被调用
 * · exceptionCaught函数，当处理发生异常时被调用
 * @author: Huo
 * @create: 2020-06-20 10:53
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Client received: " + ByteBufUtil
                .hexDump(in.readBytes(in.readableBytes())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
