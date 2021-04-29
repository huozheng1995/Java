package com.learn.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @description: LogEventDecoder负责将传入的DatagramPackets解码为LogEvent消息
 * @author: Huo
 * @create: 2020-07-03 16:54
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
        ByteBuf data = datagramPacket.content();
        int i = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);
        String filename = data.slice(0, i).toString(CharsetUtil.UTF_8);
        String logMsg = data.slice(i + 1, data.readableBytes()).toString(CharsetUtil.UTF_8);

        LogEvent event = new LogEvent(datagramPacket.recipient(), System.currentTimeMillis(), filename, logMsg);
        out.add(event);
    }
}
