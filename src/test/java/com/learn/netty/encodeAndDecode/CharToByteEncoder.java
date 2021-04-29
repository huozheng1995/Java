package com.learn.netty.encodeAndDecode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description: 如下代码中，CharToByteEncoder则将Char转化为字节类型并写入ByteBuf中
 * @author: Huo
 * @create: 2020-07-01 11:14
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character> {
    @Override
    public void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        out.writeChar(msg);
    }
}
