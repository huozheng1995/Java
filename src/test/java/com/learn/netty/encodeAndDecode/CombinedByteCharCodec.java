package com.learn.netty.encodeAndDecode;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @description: 直接使用codec来实现上述代码示例，假设已经有了ByteToCharDecoder和CharToByteEncoder，其代码如下所示
 * @author: Huo
 * @create: 2020-07-01 11:15
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
