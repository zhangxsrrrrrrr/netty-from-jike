package com.dmm.client.dispatch.handler;

import com.dmm.client.dispatch.RequestPendingCenter;
import com.dmm.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhangxun
 * @create: 2023-02-23 22:24
 * @description: 监听通道的读事件，将读取到的数据设置到Future中
 **/
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<ResponseMessage> {
    private final RequestPendingCenter requestPendingCenter;

    public ResponseDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage) throws Exception {
        requestPendingCenter.set(responseMessage.getMessageHeader().getStreamId(), responseMessage.getMessageBody());
    }
}
