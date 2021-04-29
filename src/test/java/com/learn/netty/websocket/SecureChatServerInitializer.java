package com.learn.netty.websocket;

import io.netty.channel.*;
import io.netty.channel.group.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
/**
 * @description: 上述代码中可正常进行通信，但是并未加密，首先需要添加SecureChatServerInitializer，其代码如下
 * @author: Huo
 * @create: 2020-07-02 17:22
 */
public class SecureChatServerInitializer extends ChatServerInitializer {
    private final SslContext context;
    public SecureChatServerInitializer(ChannelGroup group, SslContext context) {
        super(group);
        this.context = context;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        SSLEngine engine = context.newEngine(ch.alloc());
        ch.pipeline().addFirst(new SslHandler(engine));
    }
}
