package com.dmm.server.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author: zhangxun
 * @create: 2023-02-23 21:05
 * @description: 解决黏包和拆包
 **/
public class AuthFrameEncoder extends LengthFieldPrepender {
    public AuthFrameEncoder() {
        super(2);
    }
}
