package com.dmm.client.codec;

import com.dmm.common.Operation;
import com.dmm.common.RequestMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: zhangxun
 * @create: 2023-02-23 22:07
 * @description:
 **/
public class OperationToRequestMwssageEncoder extends MessageToMessageEncoder<Operation> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> list) throws Exception {
        RequestMessage requestMessage = new RequestMessage(System.currentTimeMillis(), operation);
        list.add(requestMessage);
    }
}
