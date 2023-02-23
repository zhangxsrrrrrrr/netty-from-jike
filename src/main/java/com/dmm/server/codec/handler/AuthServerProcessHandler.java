package com.dmm.server.codec.handler;

import com.dmm.common.Operation;
import com.dmm.common.OperationResult;
import com.dmm.common.RequestMessage;
import com.dmm.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

/**
 * @author: zhangxun
 * @create: 2023-02-23 21:11
 * @description:
 **/
@Log
public class AuthServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
        Operation messageBody = requestMessage.getMessageBody();
        OperationResult execute = messageBody.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(execute);
        log.info(requestMessage.getMessageHeader().getStreamId() + "");
//        log.info("xixixi"+requestMessage.toString());
        channelHandlerContext.writeAndFlush(responseMessage);
    }
}
