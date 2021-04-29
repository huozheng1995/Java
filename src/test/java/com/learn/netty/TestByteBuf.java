package com.learn.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @description: ByteBuf的使用
 * @author: Huo
 * @create: 2020-06-30 14:59
 */
public class TestByteBuf {
    public static void  main(String[] args) {
        //Unpooled.copiedBuffer新建ByteBuf，slice获得新的ByteBuf实例
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 14);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte)'J');
        assert buf.getByte(0) == sliced.getByte(0);

        //copy获得新的ByteBuf实例
        utf8 = Charset.forName("UTF-8");
        buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 14);
        System.out.println(copy.toString(utf8));
        buf.setByte(0, (byte)'J');
        assert buf.getByte(0) == copy.getByte(0);
    }
}
