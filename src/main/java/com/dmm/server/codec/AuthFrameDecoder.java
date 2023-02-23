package com.dmm.server.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author: zhangxun
 * @create: 2023-02-23 21:05
 * @description: 解决黏包和拆包
 **/
public class AuthFrameDecoder extends LengthFieldBasedFrameDecoder {
    public AuthFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2,0,2);
    }
}
